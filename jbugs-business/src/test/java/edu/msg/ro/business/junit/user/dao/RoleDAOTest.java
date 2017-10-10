package edu.msg.ro.business.junit.user.dao;

import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.msg.ro.business.user.dao.RoleDAO;
import edu.msg.ro.persistence.user.entity.Role;

@RunWith(MockitoJUnitRunner.class)
public class RoleDAOTest {

	@InjectMocks
	RoleDAO roleDAO;

	@Mock
	@PersistenceContext(unitName = "jbugs-persistence")
	protected EntityManager em;

	@Mock
	private EntityTransaction transaction;

	@Mock
	private TypedQuery<Role> query;

	@Test
	public void testGetAll() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(Role.FIND_ALL_ROLES, Role.class)).thenReturn(query);
		roleDAO.getAll();
		roleDAO.getEntityClass();
	}
}
