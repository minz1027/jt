package edu.msg.ro.business.junit.user.control;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.business.user.util.UserGenerator;
import edu.msg.ro.business.user.validation.UserValidator;
import edu.msg.ro.business.util.TestHelper;
import edu.msg.ro.persistence.user.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserDAO userDAO;

	@Mock
	UserDTOMapper userDTOMapper;

	@Mock
	UserValidator userValidator;

	@Mock
	UserGenerator userGenerator;

	@Mock
	User userEntity;

	TestHelper th = new TestHelper();

	/**
	 * test for user create
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void createUserTest() throws BusinessException {
		userService.createUser(new UserDTO());
		verify(userDTOMapper, times(1)).mapToEntity(any(UserDTO.class), any(User.class));
		verify(userDAO, times(1)).persistEntity(any(User.class));
		verify(userDAO, times(1)).findEntity(any(Long.class));
		verify(userDTOMapper, times(1)).mapToDTO(any(User.class));
	}

	/**
	 * test for user update
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void updateUsetTest() throws BusinessException {
		userService.updateUser(new UserDTO());

		verify(userDAO, times(1)).findEntity(any(Long.class));
		verify(userDTOMapper, times(1)).mapToEntity(any(UserDTO.class), any(User.class));
		verify(userDTOMapper, times(1)).mapToDTO(any(User.class));
	}

	/**
	 * test for reset password for user
	 * 
	 */
	@Test
	public void resetPasswordTest() {
		userService.resetPassword(new UserDTO());
		verify(userDAO, times(1)).findEntity(any(Long.class));
		verify(userDTOMapper, times(1)).mapToEntity(any(UserDTO.class), any(User.class));
		verify(userDTOMapper, times(1)).mapToDTO(any(User.class));
	}

	/**
	 * Test delete users.
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void deleteUserTest() throws BusinessException {
		UserDTO userDto = th.initializUser(1L, "firstname", "lastname", "email", "password", "0751788565");
		userDTOMapper.mapToEntity(userDto, userEntity);
		when(userDAO.findUserByUsername(userDto.getUsername())).thenReturn(userEntity);
		when(userValidator.checkIfUserHasActiveTasks(userEntity)).thenReturn(false);
		userService.deleteUser(userDto);

		try {
			when(userValidator.checkIfUserHasActiveTasks(userEntity)).thenReturn(true);
			userService.deleteUser(userDto);
		} catch (BusinessException e) {
			// TODO: handle exception
		}
	}

	/**
	 * test deleteUserNoCheck
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void deleteUserNoCheckTest() throws BusinessException {
		UserDTO userDto = th.initializUser(1L, "firstname", "lastname", "email", "password", "0751788565");
		userDTOMapper.mapToEntity(userDto, userEntity);
		when(userDAO.findUserByUsername(userDto.getUsername())).thenReturn(userEntity);
		userService.deleteUserNoCheck(userDto);

	}

	/**
	 * test findUserExists
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void findUserExistsTest() throws BusinessException {
		userService.findUserExists("a", "b");
	}

	/**
	 * test get all users
	 */
	@Test
	public void getAllUsersTest() {
		userService.getAllUsers();
		verify(userDAO, times(1)).getAll();
		verify(userDTOMapper, times(1)).mapToDTOs((List<User>) any(List.class));

	}

	/**
	 * test findUserByUsername
	 */
	@Test
	public void findUserByUsernameTest() {
		userService.findUserByUsername("a");
	}

	/**
	 * test getAllUserByQuery
	 */
	@Test
	public void getAllUserByQueryTest() {
		userService.getAllUserByQuery("a");
	}
}
