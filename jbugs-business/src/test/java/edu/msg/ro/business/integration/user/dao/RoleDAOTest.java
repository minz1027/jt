package edu.msg.ro.business.integration.user.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.dao.RoleDAO;

/**
 * Role DAO {@link RoleDAO} test.
 *
 * @author balinc
 *
 */
public class RoleDAOTest extends AbstractIntegrationTest {

	@EJB
	private RoleDAO dao;

	/**
	 * Check if list is returned for roles.
	 *
	 * @throws BusinessException
	 */
	@Test
	public void getRole_succesfull() {
		Assert.assertNotEquals("RoleDAO is not working", dao.getAll(), null);

	}
}
