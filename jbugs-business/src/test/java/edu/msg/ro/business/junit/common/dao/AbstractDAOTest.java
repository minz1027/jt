package edu.msg.ro.business.junit.common.dao;

import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.persistence.user.entity.Permission;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDAOTest {

	@InjectMocks
	PermissionDAO perDAO;

	@Mock
	@PersistenceContext(unitName = "jbugs-persistence")
	protected EntityManager em;

	@Mock
	private EntityTransaction transaction;

	@Mock
	private TypedQuery<Permission> query;

	@Test
	public void abstractFunctionTest() {
		perDAO.persistEntity(new Permission());
		perDAO.deleteEntity(new Permission());
		perDAO.findEntity(1L);

		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(Permission.FIND_ALL, Permission.class)).thenReturn(query);
		perDAO.getSingleResult(query);

		when(query.getSingleResult()).thenThrow(new NoResultException());
		perDAO.getSingleResult(query);
	}
}
