package edu.msg.ro.business.user.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.persistence.user.entity.Role;

/**
 * DAO for {@link Role} entity.
 * 
 * @author balinc
 *
 */
@Stateless
public class RoleDAO extends AbstractDao<Role> {

	/**
	 * Method for getting the class.
	 */
	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}

	/**
	 * Method for getting back all the {@link Role}s.
	 * 
	 * @return
	 */
	public List<Role> getAll() {
		TypedQuery<Role> query = this.em.createNamedQuery(Role.FIND_ALL_ROLES, Role.class);
		return query.getResultList();
	}
}
