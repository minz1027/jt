package edu.msg.ro.business.user.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.User;

/**
 * DAO for {@link Permission} entity for select all
 * 
 * @author varadp
 *
 */
@Stateless
public class PermissionDAO extends AbstractDao<Permission> {

	/**
	 * Method for getting the class.
	 */
	@Override
	public Class<Permission> getEntityClass() {
		return Permission.class;
	}

	/**
	 * Method for getting back all the {@link Permission}s.
	 * 
	 * @return
	 */
	public List<Permission> getAll() {
		TypedQuery<Permission> query = this.em.createNamedQuery(Permission.FIND_ALL, Permission.class);
		return query.getResultList();
	}

	/**
	 * Get all user witch have the permission.
	 * 
	 * @param permission_id
	 * @return
	 */
	public List<User> getAllUsersByPermission(PermissionEnum permission) {
		Query query = this.em.createNativeQuery(
				"SELECT u.id FROM permission as p JOIN role_permission as rp JOIN role AS r JOIN user_role AS ur JOIN user as u WHERE p.ID = "
						+ permission.getId().intValue()
						+ " AND rp.idPermission = p.id AND rp.idRole = r.ID AND ur.idRole = r.ID AND u.ID = ur.idUser",
				User.class);
		return query.getResultList();
	}
}
