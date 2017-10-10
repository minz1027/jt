package edu.msg.ro.business.junit.user.boundary;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.boundary.RoleFacade;
import edu.msg.ro.business.user.control.RoleService;
import edu.msg.ro.business.user.dto.RoleDTO;

@RunWith(MockitoJUnitRunner.class)
public class RoleFacadeTest {

	@InjectMocks
	RoleFacade roleFacade;

	@Mock
	RoleService roleService;

	/**
	 * Check getAllRoles call
	 */
	@Test
	public void testGetAllRoles() {
		roleFacade.getAllRoles();
		verify(roleService, times(1)).getAllRoles();
	}

	/**
	 * Check update role calls
	 */
	@Test
	public void testUpdateRoles() {
		roleFacade.update(any(RoleDTO.class));
		verify(roleService, times(1)).update(any(RoleDTO.class));
	}
}
