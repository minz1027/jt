package edu.msg.ro.business.notification.boundary;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.notification.control.NotificationService;
import edu.msg.ro.business.notification.dto.NotificationDTO;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.UserDTO;

/**
 * Boundary for bug component.
 *
 * @author balinc
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NotificationFacade {

	@EJB
	private NotificationService noService;

	/**
	 * Method for updating the notification.
	 * 
	 * @param noDTO
	 * @return
	 */
	public NotificationDTO update(NotificationDTO noDTO) {
		return noService.update(noDTO);
	}

	/**
	 * Get notification number for user.
	 * 
	 * @param user
	 * @return
	 */
	public Long getTotalActiveByUser(UserDTO user) {
		return noService.getTotalActiveByUser(user);
	}

	/**
	 * Get notification list for user.
	 * 
	 * @param user
	 * @return
	 */
	public List<NotificationDTO> getAllByUser(UserDTO user) {
		return noService.getAllByUser(user);
	}

	/**
	 * Return the notification translated message by type.
	 * 
	 * @param type
	 * @param arguments
	 * @return
	 * @throws BusinessException
	 */
	public String getMessage(NotificationType type, Map<String, String> arguments) throws BusinessException {
		return noService.getMessage(type, arguments);
	}

	/**
	 * Delete notification older then 30 days.
	 */
	@Schedule(second = "0", minute = "0", hour = "*/1")
	public void deleteOldNotifications() {
		LocalDate date = LocalDate.now().minusDays(30L);
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = date.atStartOfDay(zoneId).toEpochSecond();
		noService.deleteOldNotifications(epoch);
	}
}
