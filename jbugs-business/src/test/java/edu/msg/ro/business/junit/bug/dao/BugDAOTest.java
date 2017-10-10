package edu.msg.ro.business.junit.bug.dao;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.bug.dao.BugDAO;
import edu.msg.ro.persistence.bug.entity.Bug;

@RunWith(MockitoJUnitRunner.class)
public class BugDAOTest {

	@InjectMocks
	BugDAO bugDAO;

	@Mock
	@PersistenceContext(unitName = "jbugs-persistence")
	protected EntityManager em;

	@Mock
	private EntityTransaction transaction;

	@Mock
	private TypedQuery<Bug> query;

	@Test
	public void testGetAll() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(Bug.FIND_ALL, Bug.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Bug>());
		bugDAO.getAll();
	}

	@Test
	public void testDeleteAttachment() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(Bug.DELETE_ATTACHMENT, Bug.class)).thenReturn(query);
		bugDAO.deleteAttachemtn(1L);
	}

	@Test
	public void testGetBug() {
		bugDAO.getBug(1L);
	}
}
