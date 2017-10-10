package edu.msg.ro.business.junit.user.boundary;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.boundary.LoginFacade;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.util.UserGenerator;
import edu.msg.ro.business.util.TestHelper;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginTest {

	@InjectMocks
	LoginFacade loginFacade;

	@Mock
	UserGenerator userPass;

	@Mock
	UserService userService;

	TestHelper th = new TestHelper();

	@Test
	public void testIsValidUser() throws BusinessException {
		UserDTO toTest = th.initializUser("firstname", "lastname", "email@msggroup.com", "password", "0748102601");
		loginFacade.isValidUser(toTest);
		verify(userPass, times(1)).encryptPassword(toTest);
		verify(userService, times(1)).findUserExists(eq(toTest.getUsername()), any(String.class));
	}

}
