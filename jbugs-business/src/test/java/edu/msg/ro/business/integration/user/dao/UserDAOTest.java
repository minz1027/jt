package edu.msg.ro.business.integration.user.dao;

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
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.business.util.TestHelper;
import edu.msg.ro.persistence.user.entity.User;

/****
 * 
 * @author nagya
 **/
public class UserDAOTest extends AbstractIntegrationTest {

	@EJB
	private UserDAO dao;

	@EJB
	private UserFacade uf;

	@EJB
	private BugFacade bf;

	@EJB
	private TestHelper th;

	@EJB
	private UserDTOMapper udm;

	/**
	 * Check if list is returned for roles.
	 *
	 * @throws BusinessException
	 */
	@Test
	public void getUsers_succesfull() {

		Assert.assertNotEquals("getUsers is not working", dao.getAll(), null);
	}

	/**
	 * Check if user is returned for an username.
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void findUserByUsername_succesfull() throws BusinessException, TechnicalExeption {
		UserDTO user = th.initializUser("Mary", "Jane", "asd@msggroup.com", "asd", "0756748395");
		uf.createUser(user);

		Assert.assertEquals("Should have an user with JanosF username",
				dao.findUserByUsername(user.getUsername()).getUsername(), user.getUsername());

	}

	/**
	 * Check if user is returned for an email.
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void findUserByEmail_succesfull() throws TechnicalExeption, BusinessException {
		UserDTO user = th.initializUser("Fulop", "Lajos", "lajoska2@msggroup.com", "asd", "0756748395");
		uf.createUser(user);
		Assert.assertEquals("Should have an user with lajoska2@msggroup.com email",
				dao.findUserByEmail(user.getEmail()).getEmail(), user.getEmail());
	}

	/**
	 * Check if the user exist.
	 *
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void verifyUserExist_succesfull() throws BusinessException {
		UserDTO user = th.initializUser("Fulop", "Gabor", "gabika@msggroup.com", "asd", "0756748395");
		uf.createUser(user);
		Assert.assertEquals("User should exist ", true, dao.verifyUserExist(user.getUsername(), user.getPassword()));
	}

	/**
	 * Checks if user does not have bugs assigned
	 *
	 * @throws BusinessException
	 */
	@Test
	public void checkIfUserHasNoAssignedBugs() throws BusinessException {
		UserDTO user = th.initializUser("Denis", "SeBastian", "denis@msggroup.com", "123456", "0040743189869");
		UserDTO userDTO = uf.createUser(user);
		User userEntity = new User();
		udm.mapToEntity(userDTO, userEntity);
		boolean hasAssignedBug = dao.checkIfUserHasAssignedBugs(userEntity);
		Assert.assertEquals("User should not have assigned bug(s)!", false, hasAssignedBug);
	}

	/**
	 * Check if user has assigned bug
	 * 
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void checkIfUserHasAssignedBugs() throws BusinessException, TechnicalExeption {
		UserDTO user = th.initializUser("Denis", "Viorel", "dennnisV@msggroup.com", "123456", "00400743188876");
		UserDTO userDTO = uf.createUser(user);

		User userEntity = new User();
		BugDTO bug = th.initializingBug("Title", "Description", BugSeverity.LOW, "v1", "fixed", StatusEnum.INFONEEDED,
				userDTO);
		BugDTO bugDTO = bf.createBug(bug);
		udm.mapToEntity(userDTO, userEntity);
		boolean hasAssignedBug = dao.checkIfUserHasAssignedBugs(userEntity);
		Assert.assertEquals("User should have assigned bug(s)!", false, hasAssignedBug);
	}

	/**
	 * assigned user for bug
	 * 
	 * @throws BusinessException
	 * @throws TechnicalExeption
	 */
	@Test
	public void checkIfUserHasAssignedBugsDatabase() throws BusinessException, TechnicalExeption {

		UserDTO user = th.initializUser("Denis", "Viorel", "denisV@msggroup.com", "123456", "00400743188876");
		UserDTO userDTO = uf.createUser(user);

		BugDTO bug = th.initializingBug("Title", "Description", BugSeverity.LOW, "v1", "fixed", StatusEnum.INFONEEDED,
				userDTO);
		BugDTO bugDTO = bf.createBug(bug);

		Assert.assertEquals("User should have assigned bug(s)!", bugDTO.getAssigned().getUsername(),
				userDTO.getUsername());
	}
}
