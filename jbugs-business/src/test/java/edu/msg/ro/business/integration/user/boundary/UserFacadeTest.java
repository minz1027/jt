package edu.msg.ro.business.integration.user.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.business.user.dao.RoleDAO;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.RoleDTOMapper;
import edu.msg.ro.business.user.security.PermissionChecker;
import edu.msg.ro.business.user.security.PermissionConstants;
import edu.msg.ro.business.util.TestHelper;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;

/***
 * Test for{
 * 
 * @link UserFacade} facede.
 *
 * @author balinc
 *
 */
public class UserFacadeTest extends AbstractIntegrationTest {

	@EJB
	private UserFacade sut;

	@EJB
	private PermissionChecker permCheck;

	@EJB
	private TestHelper th;

	@EJB
	private PermissionDAO permDAO;

	@EJB
	private RoleDAO roleDAO;

	@EJB
	private RoleDTOMapper roleDTOmapper;

	/**
	 * Check if user insert is working.
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUser_succesfull() throws BusinessException {

		UserDTO testUser = th.initializUser("Mard", "Yoe", "aqwd@msggroup.com", "asd", "0756748395");
		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertNotNull("The newly persisted user should have an id!", createdUser.getId());
	}

	/**
	 *
	 * Check if the user is active by default.
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUser_ActiveByDefault() throws BusinessException {

		UserDTO testUser = th.initializUser("Mary", "Jane", "asrtd@msggroup.com", "asd", "0756748395");
		UserDTO createdUser = sut.createUser(testUser);
		Assert.assertTrue("The newly persisted user should be active!", createdUser.isActive());
	}

	/**
	 * Test if username is not NULL
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUserWithUsername() throws BusinessException {
		UserDTO testUser = th.initializUser("First", "Last", "aqazsd@msggroup.com", "asd", "0756748395");
		UserDTO createdUser = sut.createUser(testUser);
		Assert.assertNotNull("The created user should have username!", createdUser.getUsername());
	}

	/**
	 * Check if username is correct
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUserWithCorrectUsername() throws BusinessException {
		UserDTO testUser = th.initializUser("Nemeth", "Attila", "asacvcvsd@msggroup.com", "asd", "0756748395");
		UserDTO createdUser = sut.createUser(testUser);
		Assert.assertEquals("The created username should match the exrpesssion !", "AttilN", createdUser.getUsername());
	}

	/**
	 * Check if username generator works correctly when username already exists
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUserWithExistingUsername() throws BusinessException {
		UserDTO testUser = th.initializUser("Fulop", "Szabi", "asd@msggroup.com", "asd", "0756748395");
		UserDTO testUser1 = th.initializUser("Fulop", "Szabi", "adfsd@msggroup.com", "assd", "0756748395");
		UserDTO createdUser = sut.createUser(testUser);
		UserDTO createdUser2 = sut.createUser(testUser1);
		Assert.assertEquals("The created username should match the exrpesssion !", "SzabiFu",
				createdUser2.getUsername());
	}

	/**
	 * check if user has given permission
	 *
	 * @throws BusinessException
	 */
	@Test
	public void checkPermission() throws BusinessException {
		List<Role> roles = new ArrayList<Role>();
		List<Permission> permissions = new ArrayList<Permission>();

		Permission managementPerm = permDAO.findEntity(1L);
		permissions.add(managementPerm);
		Permission bugClosePerm = permDAO.findEntity(4L);
		permissions.add(bugClosePerm);

		Role adminRole = roleDAO.findEntity(1L);
		roles.add(adminRole);

		List<RoleDTO> rolesDTO = roleDTOmapper.mapToDTOs(roles);

		UserDTO user = th.initializUser("Test", "Name", "ad23sd@msggroup.com", "adsd", "0756748495");
		user.setRoles(rolesDTO);
		UserDTO createdUser = sut.createUser(user);
		List<Long> list = new ArrayList<>();

		list.add((long) PermissionConstants.PM);
		boolean hasManagementPermission = permCheck.canAccess(createdUser, list);
		list.clear();

		list.add((long) PermissionConstants.BM);
		boolean hasBugManagementPermission = permCheck.canAccess(createdUser, list);
		list.clear();

		list.add((long) PermissionConstants.BC);
		boolean hasBugClosePermission = permCheck.canAccess(createdUser, list);
		list.clear();

		Assert.assertEquals("User should have management permission: ", true, hasManagementPermission);
		Assert.assertEquals("User should have bug close permission ", true, hasBugClosePermission);
		Assert.assertEquals("User should have bug management permission ", true, hasBugManagementPermission);
	}

}
