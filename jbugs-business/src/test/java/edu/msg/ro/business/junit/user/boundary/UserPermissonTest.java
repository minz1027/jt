package edu.msg.ro.business.junit.user.boundary;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.boundary.PermissionFacade;
import edu.msg.ro.business.user.control.PermissionService;
import edu.msg.ro.business.util.TestHelper;

@RunWith(MockitoJUnitRunner.class)
public class UserPermissonTest {

	@InjectMocks
	PermissionFacade permissionFacede;

	@Mock
	PermissionService permissionService;

	TestHelper th = new TestHelper();

	@Test
	public void userPermissonTest() {
		permissionFacede.getAll();
		verify(permissionService, times(1)).getAllPermissions();
	}
}
