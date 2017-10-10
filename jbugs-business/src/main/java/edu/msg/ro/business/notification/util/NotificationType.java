package edu.msg.ro.business.notification.util;

import edu.msg.ro.persistence.notification.entity.Notification;

/**
 * Notification types for {@link Notification}
 * 
 * @author balinc
 *
 */
public enum NotificationType {
	WELCOME_NEW_USER, USER_UPDATED, USER_DELETED, USER_DEACTIVATED, BUG_UPDATED, BUG_CLOSED, BUG_STATUS_UPDATED;

	public int key;

	/**
	 * Constructor.
	 * 
	 * @param
	 */
	private NotificationType() {
		this.key = ordinal();
	}
}