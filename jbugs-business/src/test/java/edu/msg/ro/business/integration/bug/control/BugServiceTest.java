package edu.msg.ro.business.integration.bug.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.control.BugService;
import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.common.exception.TechnicalExeption;
import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.business.util.TestHelper;

/******
 * Test for BugService********
 * 
 * @author fulops
 *
 */
public class BugServiceTest extends AbstractIntegrationTest {

	@EJB
	private BugService bs;

	@EJB
	private UserService us;

	@EJB
	private UserDTOMapper userDTOMapper;

	@EJB
	private TestHelper th;

	@EJB
	private BugFacade bf;

	/**
	 * bug service test: create bug id
	 * 
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void createBug_CreatedID() throws BusinessException, TechnicalExeption {

		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO persistUser = us.createUser(testUser);

		BugDTO testBug = th.initializingBug("Bug title", "Description", BugSeverity.LOW, "v2.2", "bug",
				StatusEnum.INFONEEDED, persistUser);
		BugDTO persistBug = bs.createBug(testBug);

		Assert.assertNotNull("Shold have id: ", persistBug.getId());
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
		UserDTO persistUser = us.createUser(testUser);

		BugDTO testBug = th.initializingBug("Bug title", "Description", BugSeverity.MEDIUM, "v2.2", "bug",
				StatusEnum.INFONEEDED, persistUser);
		BugDTO persistBug = bs.createBug(testBug);
		String createdBugTitle = persistBug.getTitle();

		persistBug.setTitle("Updated title!");
		BugDTO updatedBug = bf.updateBug(persistBug);

		Assert.assertNotEquals("Update bug issue!", createdBugTitle, updatedBug.getTitle());
	}

	/**
	 * If deleted bug is not null
	 *
	 * @throws TechnicalExeption
	 * @throws BusinessException
	 */
	@Test
	public void deleteBug_deleteBugTest() throws BusinessException, TechnicalExeption {

		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO persistUser = us.createUser(testUser);

		BugDTO testBug = th.initializingBug("Bug title", "Description", BugSeverity.HIGH, "v2.2", "bug",
				StatusEnum.INFONEEDED, persistUser);
		BugDTO persistBug = bf.createBug(testBug);

		BugDTO deletedBug = bs.deleteBug(persistBug);
		Assert.assertNull("Delete bug issue!", deletedBug);
	}

	/**
	 * if bugs null
	 *
	 * @throws TechnicalExeption
	 * @throws BusinessException
	 */
	@Test
	public void getAllBug_test() throws BusinessException, TechnicalExeption {
		Assert.assertNotNull("Get all bugs issue:", bs.getAllBugs());
	}

}
