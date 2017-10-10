package edu.msg.ro.business.integration.bug.boundary;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.common.exception.TechnicalExeption;
import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.util.TestHelper;

/****
 * 
 * @author nagya
 *
 */

public class BugFacadeTest extends AbstractIntegrationTest {

	@EJB
	private BugFacade sut;

	@EJB
	private TestHelper th;

	@EJB
	private UserFacade uf;

	/**
	 * Check if bug create is working.
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void createBug_succesfull() throws BusinessException, TechnicalExeption {

		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO persistedUser = uf.createUser(testUser);

		BugDTO createdBug = sut.createBug(th.initializingBug("Bug title", "Description", BugSeverity.MEDIUM, "v2.2",
				"v23.2", StatusEnum.INFONEEDED, persistedUser));
		BugDTO persistedBug = sut.createBug(createdBug);

		Assert.assertNotNull("Bug should have an id", persistedBug.getId());

	}

	/**
	 * if update is not working
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void updateBug_UpdatedTestBug() throws BusinessException, TechnicalExeption {

		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO persistUser = uf.createUser(testUser);

		BugDTO testBug = th.initializingBug("Bug title", "Description", BugSeverity.MEDIUM, "v2.2", "bug",
				StatusEnum.INFONEEDED, persistUser);
		BugDTO persistBug = sut.createBug(testBug);
		String createdBugTitle = persistBug.getTitle();

		persistBug.setTitle("Updated title!");
		BugDTO updatedBug = sut.updateBug(persistBug);

		Assert.assertNotEquals("Update bug issue!", createdBugTitle, updatedBug.getTitle());
	}

	/**
	 * Check if delete working
	 * 
	 * @throws TechnicalExeption
	 * @throws BusinessException
	 */
	@Test
	public void deleteBug_deleteBugTest() throws TechnicalExeption, BusinessException {

		UserDTO createdUser = uf.createUser(th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395"));
		BugDTO createdBug = sut.createBug(th.initializingBug("Bug title", "Description", BugSeverity.HIGH, "v2.2",
				"v2.3", StatusEnum.INFONEEDED, createdUser));
		BugDTO deletedBug = sut.deleteBug(createdBug);

		Assert.assertNull("Bug should be null", deletedBug);

	}

	/**
	 * check if bugs not null
	 */
	@Test
	public void getAllbugs_succesfull() {
		Assert.assertNotNull("getAllbugs not working", sut.getAllbugs());
	}

}
