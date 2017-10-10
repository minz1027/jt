package edu.msg.ro.business.junit.user.dto.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.dto.PermissionDTO;
import edu.msg.ro.business.user.dto.mapper.PermissionDTOMapper;
import edu.msg.ro.persistence.user.entity.Permission;

@RunWith(MockitoJUnitRunner.class)
public class PermissionDTOMapperTest {

	@InjectMocks
	PermissionDTOMapper permMapper;

	@Test
	public void mapToDTO_validEntity() {

		Permission entity = new Permission();
		entity.setDetail("detail");
		entity.setName("name");

		PermissionDTO perDTO = permMapper.mapToDTO(entity);
		permMapper.mapEntityToDTOFields(entity, perDTO);

		// To complete the entity Junit test.
		@SuppressWarnings("unused")
		String stringEntity = entity.toString();

		Assert.assertEquals("Detail mapping failed", entity.getDetail(), perDTO.getDetail());
		Assert.assertEquals("Lock Version name mapping failed", entity.getLockVersion(), perDTO.getLockVersion());
		Assert.assertEquals("Name mapping failed", entity.getName(), perDTO.getName());
	}

	/**
	 * Check for null entity.
	 */
	@Test
	public void mapToDTO_NullEntity() {
		PermissionDTO perDTO = permMapper.mapToDTO(null);
		Assert.assertNull("Return value of an NULL input should be also NULL", perDTO);
	}

}
