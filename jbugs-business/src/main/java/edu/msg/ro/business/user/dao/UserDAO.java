package edu.msg.ro.business.user.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

/**
 * DAO for {@link User} entity.
 * 
 * @author balinc
 *
 */
@Stateless
public class UserDAO extends AbstractDao<User> {

	/**
	 * Method for getting the class.
	 */
	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	/**
	 * Method for finding {@link User} by given username.
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Method for finding {@link User} by given email.
	 * 
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);
		return getSingleResult(query);
	}

	/**
	 * Method for verifying that an {@link User} exist with the given username
	 * and password and is active.
	 * 
	 * @param username
	 * @param password
	 * @return {@link Boolean}
	 * @throws BusinessException
	 */
	public boolean verifyUserExist(String username, String password) throws BusinessException {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_USERNAME_PASS, User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		List<User> userList = query.getResultList();
		if (userList.isEmpty() == false) {
			if (userList.get(0).isActive() == false) {
				throw new BusinessException("login.wrongpassword");
			}
			return userList.get(0).isActive();
		}

		return false;
	}

	/**
	 * Method for getting back all the {@link User}s.
	 * 
	 * @return
	 */
	public List<User> getAll() {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_ALL, User.class);
		return query.getResultList();
	}

	/**
	 * Method for getting back all the {@link User}s starts with a text.
	 * 
	 * @return
	 */
	public List<User> getAllUsernameStartsWith(String queryText) {
		Query query = this.em.createQuery("SELECT u FROM User u WHERE u.username LIKE :queryText");
		query.setParameter("queryText", queryText + "%");
		return query.getResultList();
	}

	/**
	 * Checks if user has assigned bug(s) that are not closed.
	 * 
	 * @param id
	 * @return
	 */
	public boolean checkIfUserHasAssignedBugs(User user) {
		Query query = this.em.createQuery("SELECT b FROM Bug b WHERE b.assigned = :User AND b.status <> :status");
		query.setParameter("User", user);
		query.setParameter("status", StatusEnum.CLOSE.key);
		List<User> result = query.getResultList();
		return !result.isEmpty();
	}

	/**
	 * Return all user with role.
	 * 
	 * @param id
	 * @return
	 */
	public List<User> findAllByRole(Role role) {
		Query query = this.em.createNamedQuery(User.FIND_ALL_BY_ROLE, User.class);
		query.setParameter("Role", role);
		return query.getResultList();
	}

}
