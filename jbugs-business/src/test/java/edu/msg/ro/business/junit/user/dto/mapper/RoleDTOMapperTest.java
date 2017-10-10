package edu.msg.ro.business.junit.user.dto.mapper;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.mapper.PermissionDTOMapper;
import edu.msg.ro.business.user.dto.mapper.RoleDTOMapper;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;

@RunWith(MockitoJUnitRunner.class)
public class RoleDTOMapperTest {

	@InjectMocks
	RoleDTOMapper roleMapper;

	@Mock
	PermissionDTOMapper perMapper;

	@Test
	public void mapToDTO_validEntity() {

		Role entity = new Role();

		entity.setId(1L);
		entity.setName("name");
		entity.setPermissions(new ArrayList<Permission>());
		entity.setUsers(null);

		RoleDTO roleDTO = roleMapper.mapToDTO(entity);
		roleMapper.mapEntityToDTOFields(entity, roleDTO);

		// To complete the entity Junit test.
		@SuppressWarnings("unused")
		String stringEntity = entity.toString();

		Assert.assertEquals("Id mapping failed", entity.getId(), roleDTO.getId());
		Assert.assertEquals("Lock Version mapping failed", entity.getLockVersion(), roleDTO.getLockVersion());
		Assert.assertEquals("Permissions mapping failed", entity.getPermissions(), roleDTO.getPermissions());
		Assert.assertEquals("Users mapping failed", entity.getUsers(), roleDTO.getUsers());
		Assert.assertEquals("Users name mapping failed", entity.getName(), roleDTO.getName());

	}

	/**
	 * Check for null entity.
	 */
	@Test
	public void mapToDTO_NullEntity() {
		RoleDTO roleDTO = roleMapper.mapToDTO(null);
		Assert.assertNull("Return value of an NULL input should be also NULL", roleDTO);
	}

}
