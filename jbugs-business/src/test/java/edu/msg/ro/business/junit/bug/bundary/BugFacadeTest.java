package edu.msg.ro.business.junit.bug.bundary;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.control.BugService;
import edu.msg.ro.business.bug.dto.BugDTO;

@RunWith(MockitoJUnitRunner.class)
public class BugFacadeTest {

	@InjectMocks
	BugFacade bugFacede;

	@Mock
	BugService bugService;

	/**
	 * check create bug
	 */

	@Test
	public void testCreateBug() {
		bugFacede.createBug(any(BugDTO.class));
		verify(bugService, times(1)).createBug(any(BugDTO.class));
	}

	/**
	 * check bug update
	 */
	@Test
	public void testUpdateBug() {
		bugFacede.updateBug(any(BugDTO.class));
		verify(bugService, times(1)).updateBug(any(BugDTO.class));
	}

	/**
	 * test get bug list
	 */
	@Test
	public void testGetAllBug() {
		bugFacede.getAllbugs();
		verify(bugService, times(1)).getAllBugs();
	}

	/**
	 * test delete bug
	 */
	@Test
	public void testDeleteBug() {
		bugFacede.deleteBug(any(BugDTO.class));
		verify(bugService, times(1)).deleteBug(any(BugDTO.class));
	}
}
