package edu.msg.ro.business.integration.user.control;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.util.TestHelper;

/*****
 * Test for{**
 * 
 * @link UserService}**@author balinc
 **/

public class UserServiceTest extends AbstractIntegrationTest {

	@EJB
	private UserService uService;

	@EJB
	private TestHelper th;

	/**
	 * Test if email not containing the required format.
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void createUser_EmailValidationFail() throws BusinessException {
		try {
			// Invalid email, needts to be @msggroup.com
			UserDTO testUser = th.initializUser("Magry", "Jandge", "unique@mail.com", "asd", "0756748395");
			UserDTO createdUser = uService.createUser(testUser);
			// TransactionRolledbackLocalException
		} catch (EJBTransactionRolledbackException e) {
			Assert.assertEquals("Exception thrown from bean", e.getCause().getMessage());
			return;
		}

		Assert.fail("Email validation should fail!");
	}

	/**
	 * Test if fail the user insert with same email address.
	 *
	 * @throws BusinessException
	 */
	@Test
	public void createUser_UniqueEmailFail() throws BusinessException {
		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO testUser1 = th.initializUser("Mafry", "Jfgane", "asd@msggroup.com", "ahsd", "0756748395");

		try {
			UserDTO persistUser = uService.createUser(testUser);
			UserDTO persistUser1 = uService.createUser(testUser1);
		} catch (BusinessException e) {
			Assert.assertEquals("users.email.exists", e.getMessage());
			return;
		}
		Assert.fail("Email validation should fail!");
	}

}