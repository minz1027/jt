package edu.msg.ro.business.junit.user.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.persistence.user.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

	@InjectMocks
	UserDAO userDAO;

	@Mock
	@PersistenceContext(unitName = "jbugs-persistence")
	protected EntityManager em;

	@Mock
	private EntityTransaction transaction;

	@Mock
	private TypedQuery<User> query;

	@Test
	public void getEntityClassTest() {
		userDAO.getEntityClass();
	}

	@Test
	public void findUserByUsernameTest() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createQuery("SELECT u FROM User u WHERE u.username = :username")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(any(User.class));
		userDAO.findUserByUsername("");
		when(this.em.createQuery("SELECT u FROM User u WHERE u.username = :username")).thenReturn(query);
		when(query.getSingleResult()).thenThrow(new NoResultException());
		userDAO.findUserByUsername("");
	}

	@Test
	public void findUserByEmailTest() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		userDAO.findUserByEmail(null);
	}

	@Test
	public void verifyUserExistTest() throws BusinessException {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(User.FIND_USER_BY_USERNAME_PASS, User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		userDAO.verifyUserExist("", "");
		when(query.getResultList()).thenReturn(getNewUserList());
		userDAO.verifyUserExist("", "");
	}

	@Test
	public void getAllTest() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createNamedQuery(User.FIND_ALL, User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		userDAO.getAll();
	}

	@Test
	public void getAllUsernameStartsWithTest() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createQuery("SELECT u FROM User u WHERE u.username LIKE :queryText")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		userDAO.getAllUsernameStartsWith("");
	}

	@Test
	public void checkIfUserHasAssignedBugsTest() {
		when(em.getTransaction()).thenReturn(transaction);
		when(this.em.createQuery("SELECT b FROM Bug b WHERE b.assigned = :User AND b.status <> :status"))
				.thenReturn(query);
		when(query.getResultList()).thenReturn(getNewUserList());
		userDAO.checkIfUserHasAssignedBugs(any(User.class));
		when(query.getResultList()).thenReturn(new ArrayList<User>());
		userDAO.checkIfUserHasAssignedBugs(any(User.class));
	}

	public List<User> getNewUserList() {
		User user = new User();
		user.setActive(true);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		return userList;
	}
}
