package edu.msg.ro.business.notification.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.notification.dao.NotificationDAO;
import edu.msg.ro.business.notification.dto.NotificationDTO;
import edu.msg.ro.business.notification.dto.mapper.NotificationDTOMapper;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.notification.entity.Notification;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Controller for {@link Notification} component.
 * 
 * @author balinc
 *
 */
@Stateless
public class NotificationService {

	@EJB
	private NotificationDTOMapper noDTOMapper;

	@EJB
	private UserDAO userDAO;

	@EJB
	private NotificationDAO noDAO;

	@EJB
	private NotificationDTO noDTO;

	/**
	 * Updating the notification.
	 * 
	 * @return
	 */
	public NotificationDTO update(NotificationDTO noDTO) {
		Notification persistedNO = noDAO.findEntity(noDTO.getId());
		noDTOMapper.mapToEntity(noDTO, persistedNO);
		return noDTOMapper.mapToDTO(persistedNO);
	}

	/**
	 * Get notification number for user.
	 * 
	 * @return
	 * 
	 */
	public Long getTotalActiveByUser(UserDTO user) {
		return noDAO.getTotalActiveByUser(userDAO.findEntity(user.getId()));
	}

	/**
	 * Get notification list for user.
	 * 
	 * @param user
	 * @return
	 */
	public List<NotificationDTO> getAllByUser(UserDTO user) {
		return noDTOMapper.mapToDTOs(noDAO.getAllByUser(userDAO.findEntity(user.getId())));
	}

	/**
	 * Insert new {@link Notification} for all {@link User} from the list.
	 * 
	 * @param type
	 * @param userDTO
	 * @throws IOException
	 */
	public void newNotification(NotificationType type, List<UserDTO> users, Map<String, String> variables) {
		for (UserDTO user : users) {
			newNotification(type, user, variables);
		}
	}

	/**
	 * Insert new {@link Notification} for {@link User}.
	 * 
	 * @param type
	 * @param userDTO
	 * @throws IOException
	 */
	public void newNotification(NotificationType type, UserDTO user, Map<String, String> variables) {
		NotificationDTO noDTO = new NotificationDTO();
		noDTO.setTarget(user);
		noDTO.setType(type);
		noDTO.setVariables(variables);
		noDTO.setActive(true);
		Notification no = new Notification();
		noDTOMapper.mapToEntity(noDTO, no);
		noDAO.persistEntity(no);
	}

	/**
	 * Return the translated notification message by type.
	 * 
	 * @param type
	 * @param arguments
	 * @return
	 * @throws BusinessException
	 */
	public String getMessage(NotificationType type, Map<String, String> arguments) throws BusinessException {
		try {
			return translateMessage(StrSubstitutor.replace(getNotificationTemplate(type), arguments));
		} catch (IOException e) {
			throw new BusinessException("notification.template.error");
		}
	}

	/**
	 * Translate the dynamic part of the template.
	 * 
	 * @param message
	 * @return
	 */
	public String translateMessage(String message) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		ResourceBundle resource = ResourceBundle.getBundle("i18n.templates", locale, loader);
		return StrSubstitutor.replace(message, convertResourceBundleToMap(resource));
	}

	/**
	 * Return Notification template.
	 * 
	 * @param type
	 * @throws IOException
	 */
	public String getNotificationTemplate(NotificationType type) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Locale local = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		InputStream inputStream = classLoader
				.getResourceAsStream("notification/" + local + "/" + type.toString().toLowerCase() + ".txt");
		return IOUtils.toString(inputStream, "UTF-8");
	}

	/**
	 * Delete all notification older then given date.
	 * 
	 * @param date
	 */
	public void deleteOldNotifications(Long date) {
		noDAO.deleteOldNotifications(date);
	}

	/**
	 * Convert the resource bundle to MAP to use with replace in template.
	 * 
	 * @param resource
	 * @return
	 */
	public Map<String, String> convertResourceBundleToMap(ResourceBundle resource) {
		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> keys = resource.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, resource.getString(key));
		}

		return map;
	}

}