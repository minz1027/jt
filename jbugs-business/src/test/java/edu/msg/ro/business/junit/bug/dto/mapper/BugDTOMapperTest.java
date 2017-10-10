package edu.msg.ro.business.junit.bug.dto.mapper;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.dto.mapper.BugDTOMapper;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.persistence.bug.entity.Bug;

@RunWith(MockitoJUnitRunner.class)
public class BugDTOMapperTest {

	@InjectMocks
	BugDTOMapper bugDTOMapper;

	@Mock
	UserDTOMapper userDtoMapper;

	@Mock
	UserDAO UserDAO;

	/**
	 * test for entity to DTO field for Bug
	 */

	@Test
	public void entityToDtoFieldTest() {

		Bug entity = new Bug();
		entity.setId(1L);
		entity.setTitle("title");
		entity.setDescription("description");
		entity.setFixedIn("fixedIn");
		entity.setVersion("version");
		entity.setAttachmentName("attachmentName");
		entity.setTargetDate(new Date());
		entity.setStatus(1);
		entity.setSeverity(2);
		entity.setAssigned(null);
		entity.setAuthor(null);
		entity.setAttachment(null);

		BugDTO bugDTO = bugDTOMapper.mapToDTO(entity);
		bugDTOMapper.mapEntityToDTOFields(entity, bugDTO);

		bugDTO.toString();

		Assert.assertEquals("Id mapping failed", entity.getId(), bugDTO.getId());
		Assert.assertEquals("Title mapping failed", entity.getTitle(), bugDTO.getTitle());
		Assert.assertEquals("Description mapping failed", entity.getDescription(), bugDTO.getDescription());
		Assert.assertEquals("Fixedin mapping failed", entity.getFixedIn(), bugDTO.getFixedIn());
		Assert.assertEquals("Version mapping failed", entity.getVersion(), bugDTO.getVersion());
		Assert.assertEquals("AttachmentName mapping failed", entity.getAttachmentName(), bugDTO.getAttachmentName());
		Assert.assertEquals("TagerDate mapping failed", entity.getTargetDate(), bugDTO.getTargetDate());
		Assert.assertEquals("Status mapping failed", entity.getStatus(), bugDTO.getStatus().key);
		Assert.assertEquals("Severity mapping failed", entity.getSeverity(), bugDTO.getSeverity().key);
		Assert.assertEquals("Assigned mapping failed", entity.getAssigned(), bugDTO.getAssigned());
		Assert.assertEquals("Author mapping failed", entity.getAuthor(), bugDTO.getAuthor());
		Assert.assertEquals("Attachment mapping failed", entity.getAttachment(), bugDTO.getAttachment());

	}

	@Test
	public void mapDTOToEntityFieldsTest() {

		BugDTO bugDto = new BugDTO();
		UserDTO userDto = new UserDTO();
		userDto.setId(1L);

		bugDto.setAssigned(userDto);
		bugDto.setAttachment(null);
		bugDto.setAttachmentName("name");
		bugDto.setAuthor(userDto);
		bugDto.setDescription("desc");
		bugDto.setFixedIn("v1");
		bugDto.setId(1L);
		bugDto.setLockVersion(1L);
		bugDto.setSeverity(BugSeverity.CRITICAL);
		bugDto.setStatus(StatusEnum.CLOSE);
		bugDto.setTargetDate(new Date());
		bugDto.setTitle("title");
		bugDto.setVersion("v2");

		bugDTOMapper.mapToEntity(bugDto, new Bug());

		bugDto.setAssigned(null);

		bugDTOMapper.mapToEntity(bugDto, new Bug());
	}

}