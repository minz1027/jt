package edu.msg.ro.business.junit.user.control;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.control.RoleService;
import edu.msg.ro.business.user.dao.RoleDAO;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.mapper.RoleDTOMapper;
import edu.msg.ro.persistence.user.entity.Role;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

	@InjectMocks
	RoleService roleService;

	@Mock
	RoleDAO roleDAO;

	@Mock
	RoleDTOMapper roleDTOMapper;

	/**
	 * Check getAllRoles call
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllRoles() {
		roleService.getAllRoles();
		verify(roleDAO, times(1)).getAll();
		verify(roleDTOMapper, times(1)).mapToDTOs(any(List.class));
	}

	/**
	 * Check update role calls
	 */
	@Test
	public void testUpdateRoles() {
		RoleDTO roleDTO = new RoleDTO();
		Role role = new Role();
		roleService.update(roleDTO);
		verify(roleDAO, times(1)).findEntity(role.getId());
		verify(roleDTOMapper, times(1)).mapToDTO(any(Role.class));
		verify(roleDTOMapper, times(1)).mapToEntity(any(RoleDTO.class), any(Role.class));
	}

}
