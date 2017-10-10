package edu.msg.ro.business.junit.user.validator;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.validation.UserValidator;
import edu.msg.ro.persistence.user.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

	@InjectMocks
	UserValidator userValidator;

	@Mock
	UserDAO userDAO;

	@Mock
	private User user;

	@Mock
	private User existingUserWithSameEmail = new User();

	/**
	 * check if User has active tasks
	 */
	@Test
	public void checkIfUserHasActiveTasksTest() {
		userValidator.checkIfUserHasActiveTasks(new User());
		verify(userDAO, times(1)).checkIfUserHasAssignedBugs(any(User.class));
	}

	/**
	 * check if User has active tasks
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void validateUserDataTest() throws BusinessException {
		userValidator.validateUserData(new UserDTO());
	}

	/**
	 * check if User has active tasks
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void validateEmailTest() throws BusinessException {
		when(userDAO.findUserByEmail(user.getEmail())).thenReturn(existingUserWithSameEmail);

		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);

		try {
			userValidator.validateEmail(userDTO);
		} catch (BusinessException e) {
		}

		when(userDAO.findUserByEmail(user.getEmail())).thenReturn(null);
		userValidator.validateEmail(userDTO);
	}
}
