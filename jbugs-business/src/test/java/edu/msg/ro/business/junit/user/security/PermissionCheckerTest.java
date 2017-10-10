package edu.msg.ro.business.junit.user.security;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.PermissionDTO;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.business.user.security.PermissionChecker;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.business.util.TestHelper;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class PermissionCheckerTest {
	@InjectMocks
	PermissionChecker permissionChecker;

	@Mock
	UserDAO userDAO;

	UserDTOMapper map = new UserDTOMapper();
	TestHelper th = new TestHelper();

	public UserDTO getUser() throws BusinessException {
		return th.initializUser("firstname", "lastname", "email@msggroup.com", "password", "0748102601");
	}

	public UserDTO getUserFull() throws BusinessException {
		UserDTO user = th.initializUser("firstname", "lastname", "email@msggroup.com", "password", "0748102601");
		user.setLockVersion(1L);

		PermissionDTO permission = new PermissionDTO();
		permission.setDetail("detail");
		permission.setLockVersion(1L);
		permission.setName("name");
		permission.setId(1L);

		ArrayList<PermissionDTO> permissions = new ArrayList<>();
		permissions.add(permission);

		RoleDTO role = new RoleDTO();
		role.setId(1L);
		role.setLockVersion(1L);
		role.setName("name");
		role.setPermission(permissions);

		ArrayList<RoleDTO> roles = new ArrayList<>();
		roles.add(role);

		user.setRoles(roles);
		user.setUsername("username");
		user.setId(1L);

		return user;
	}

	private User getUserEnity()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		User user = new User();
		user.setActive(true);
		user.setEmail("email@msggroup.com");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user.setLockVersion(1L);
		user.setPassword("password");
		user.setPhoneNumber("0123456789");

		Permission permission = new Permission();
		permission.setDetail("detail");
		permission.setLockVersion(1L);
		permission.setName("name");
		Field ff = permission.getClass().getDeclaredField("id");
		ff.setAccessible(true);
		ff.set(permission, 1L);

		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(permission);

		Role role = new Role();
		role.setId(1L);
		role.setLockVersion(1L);
		role.setName("name");
		role.setPermissions(permissions);

		ArrayList<User> users = new ArrayList<>();
		users.add(user);
		role.setUsers(users);

		ArrayList<Role> roles = new ArrayList<>();
		roles.add(role);

		user.setRoles(roles);
		user.setUsername("username");
		Field f = user.getClass().getDeclaredField("id");
		f.setAccessible(true);
		f.set(user, 1L);
		return user;
	}

	@Test
	public void testCheckPermission() throws BusinessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UserDTO user = getUser();

		User entity = getUserEnity();

		Mockito.doReturn(entity).when(userDAO).findUserByEmail(any(String.class));
		permissionChecker.checkPermission(user, 1);
		permissionChecker.checkPermission(user, 2);

		verify(userDAO, times(2)).findUserByEmail(any(String.class));
	}

	@Test
	public void testCanAccess1() throws BusinessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UserDTO user = getUser();
		user.setRoles(new ArrayList<>());
		Field f = user.getClass().getSuperclass().getDeclaredField("id");
		f.setAccessible(true);
		f.set(user, 1L);

		permissionChecker.canAccess(user, new ArrayList<>());

		f.set(user, 2L);

		permissionChecker.canAccess(user, new ArrayList<>());
		permissionChecker.canAccess(null, new ArrayList<>());

		ArrayList<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);

		Mockito.doReturn(getUserEnity()).when(userDAO).findUserByEmail(any(String.class));
		permissionChecker.canAccess(user, list);
	}

	@Test
	public void testCanAccess2() throws BusinessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UserDTO user = getUser();
		user.setRoles(new ArrayList<>());
		Field f = user.getClass().getSuperclass().getDeclaredField("id");
		f.setAccessible(true);
		f.set(user, 1L);

		permissionChecker.canAccess(new ArrayList<>(), user);

		f.set(user, 2L);

		permissionChecker.canAccess(new ArrayList<>(), user);
		permissionChecker.canAccess(new ArrayList<>(), null);

		ArrayList<PermissionEnum> list = new ArrayList<>();
		list.add(PermissionEnum.BUG_MANAGEMENT);
		list.add(PermissionEnum.BUG_CLOSE);

		Mockito.doReturn(getUserEnity()).when(userDAO).findUserByEmail(any(String.class));
		permissionChecker.canAccess(list, user);
	}
}
