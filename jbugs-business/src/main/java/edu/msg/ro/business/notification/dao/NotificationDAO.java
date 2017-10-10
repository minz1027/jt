package edu.msg.ro.business.notification.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import edu.msg.ro.business.common.dao.AbstractDao;
import edu.msg.ro.persistence.notification.entity.Notification;
import edu.msg.ro.persistence.user.entity.User;

@Stateless
public class NotificationDAO extends AbstractDao<Notification> {

	@Override
	public Class<Notification> getEntityClass() {
		return Notification.class;
	}

	/**
	 * Method for getting back the number of active {@link Notification}s of the
	 * logged {@link User}.
	 * 
	 * @return
	 */
	public Long getTotalActiveByUser(User user) {
		TypedQuery<Long> query = this.em.createNamedQuery(Notification.TOTAL_ACTIVE_BY_USER, Long.class);
		query.setParameter("user_id", user);
		return query.getSingleResult();
	}

	/**
	 * Method for getting back the list {@link Notification}s of the logged
	 * {@link User}.
	 * 
	 * @return
	 */
	public List<Notification> getAllByUser(User user) {
		TypedQuery<Notification> query = this.em.createNamedQuery(Notification.BY_USER, Notification.class);
		query.setParameter("user_id", user);
		return query.getResultList();
	}

	/**
	 * Delete all notification older then date.
	 * 
	 * @param date
	 */
	public void deleteOldNotifications(Long date) {
		TypedQuery<Notification> query = this.em.createNamedQuery(Notification.DELETE_OLD, Notification.class);
		query.setParameter("time", date);
		query.executeUpdate();
	}

}