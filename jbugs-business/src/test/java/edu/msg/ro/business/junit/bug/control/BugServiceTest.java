package edu.msg.ro.business.junit.bug.control;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.bug.control.BugService;
import edu.msg.ro.business.bug.dao.BugDAO;
import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.dto.mapper.BugDTOMapper;
import edu.msg.ro.business.util.TestHelper;
import edu.msg.ro.persistence.bug.entity.Bug;

@RunWith(MockitoJUnitRunner.class)
public class BugServiceTest {

	@InjectMocks
	BugService bugService;

	@Mock
	BugDAO bugDAO;

	@Mock
	BugDTOMapper bugDTOMapper;

	TestHelper th = new TestHelper();

	@Test
	public void testCreateBug() {
		bugService.createBug(new BugDTO());
		verify(bugDTOMapper, times(1)).mapToEntity(any(BugDTO.class), any(Bug.class));
		verify(bugDAO, times(1)).persistEntity(any(Bug.class));
		verify(bugDAO, times(1)).findEntity(any(Long.class));
		verify(bugDTOMapper, times(1)).mapToDTO(any(Bug.class));
	}

	@Test
	public void testUpdateBug() {
		bugService.updateBug(new BugDTO());
		verify(bugDTOMapper, times(1)).mapToEntity(any(BugDTO.class), any(Bug.class));
		verify(bugDTOMapper, times(1)).mapToDTO(any(Bug.class));
	}

	@Test
	public void testDeleteBug() {
		bugService.deleteBug(new BugDTO());
		verify(bugDAO, times(1)).getBug(any(Long.class));
		verify(bugDTOMapper, times(1)).mapToDTO(any(Bug.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllBugs() {
		bugService.getAllBugs();
		verify(bugDAO, times(1)).getAll();
		verify(bugDTOMapper, times(1)).mapToDTOs((java.util.List<Bug>) any(List.class));
	}

	@Test
	public void testDeleteAttachemtn() {
		bugService.deleteAttachment(any(Long.class));
		verify(bugDAO, times(1)).deleteAttachemtn(any(Long.class));
	}
}
