package edu.msg.ro.bean.notification;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.msg.ro.bean.AbstractBean;
import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.notification.boundary.NotificationFacade;
import edu.msg.ro.business.notification.dto.NotificationDTO;

@ManagedBean
@RequestScoped
public class NotificationBean extends AbstractBean {

	@EJB
	private NotificationFacade notFacade;

	private String activeNotifications = null;

	private List<NotificationDTO> notifications = null;

	private NotificationDTO selectedNotification;

	@PostConstruct
	public void updateActiveNotifications() {
		activeNotifications = notFacade.getTotalActiveByUser(getLoggedUser()).toString();
		notifications = notFacade.getAllByUser(getLoggedUser());
	}

	/**
	 * Get number of active notification for logged user.
	 * 
	 * @return
	 */
	public String getActiveNotifications() {
		return activeNotifications;
	}

	/**
	 * Get list of all notification for logged in user.
	 * 
	 * @return
	 */
	public List<NotificationDTO> getNotifications() {
		return notifications;
	}

	/**
	 * Return the selected notification for view.
	 * 
	 * @return
	 */
	public NotificationDTO getSelectedNotification() {
		return selectedNotification;
	}

	/**
	 * Set the selected notification.
	 * 
	 * @param selectedNotification
	 */
	public void setSelectedNotification(NotificationDTO selectedNotification) {
		this.selectedNotification = selectedNotification;
	}

	/**
	 * Show notification message.
	 * 
	 * @return
	 */
	public String showMessage() {
		if (selectedNotification == null) {
			return null;
		}
		try {
			selectedNotification.setActive(false);
			notFacade.update(selectedNotification);
			return notFacade.getMessage(selectedNotification.getType(), selectedNotification.getVariables());
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
		return null;
	}

}
