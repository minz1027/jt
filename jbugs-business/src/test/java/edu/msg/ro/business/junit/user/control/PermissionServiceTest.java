package edu.msg.ro.business.junit.user.control;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.control.PermissionService;
import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.business.user.dto.mapper.PermissionDTOMapper;

@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceTest {
	@InjectMocks
	PermissionService permissionService;

	@Mock
	PermissionDAO permissionDAO;

	@Mock
	PermissionDTOMapper permissionDTOMapper;

	@Test
	public void testGetAllPermissions() {
		permissionService.getAllPermissions();
		verify(permissionDAO, times(1)).getAll();
		verify(permissionDTOMapper, times(1)).mapToDTOs(permissionDAO.getAll());

	}
}
