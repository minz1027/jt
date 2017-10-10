package edu.msg.ro.business.integration.user.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.business.integration.AbstractIntegrationTest;
import edu.msg.ro.business.user.control.RoleService;

public class RoleServiceTest extends AbstractIntegrationTest {

	@EJB
	private RoleService sut;

	/**
	 * Check if getAllRoles not null
	 */
	@Test
	public void getAllRolesTest() {
		Assert.assertNotNull("RoleService is not working", sut.getAllRoles());
	}

}
