package edu.msg.ro.business.integration.bug.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.dao.BugDAO;
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

public class BugDAOTest extends AbstractIntegrationTest {

	@EJB
	private BugDAO bdao;

	@EJB
	private TestHelper th;

	@EJB
	private UserFacade uf;

	@EJB
	private BugFacade bf;

	/**
	 * Check if list is returned for bugs.
	 */
	@Test
	public void getallBug_succesfull() {
		Assert.assertNotNull("BugDAO is not working", bdao.getAll());

	}

	/**
	 * Check if a bug is returned by the id.
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void getBugbyId_succesfull() throws BusinessException, TechnicalExeption {
		UserDTO testUser = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		UserDTO persistUser = uf.createUser(testUser);

		BugDTO testBug = th.initializingBug("Bug title", "Description", BugSeverity.HIGH, "Open", "bug",
				StatusEnum.INPROGRESS, persistUser);
		BugDTO persistBug = bf.createBug(testBug);
		Long generateddId = persistBug.getId();

		Assert.assertNotNull("GetBug by Id is not working", bdao.getBug(generateddId));

	}

}
