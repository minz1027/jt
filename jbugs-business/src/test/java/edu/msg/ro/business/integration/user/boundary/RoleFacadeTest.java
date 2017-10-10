package edu.msg.ro.business.integration.user.boundary;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.boundary.RoleFacade;

/***
 * Test for{
 * 
 * @link RoleFacade}
 *
 * @author balinc
 *
 */
public class RoleFacadeTest extends AbstractIntegrationTest {

	@EJB
	private RoleFacade srt;

	/**
	 * Check if role LIST is returned from the database.
	 */
	@Test
	public void getRole_succesfull() {
		Assert.assertNotNull("RoleFacade is not working", srt.getAllRoles());
	}

}
