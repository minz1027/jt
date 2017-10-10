package edu.msg.ro.business.notification.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.ejb.Stateless;

import edu.msg.ro.business.common.dto.AbstractDTO;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.notification.entity.Notification;

/**
 * DTO for {@link Notification} entity.
 *
 * @author balinc
 *
 */
@Stateless
public class NotificationDTO extends AbstractDTO {

	private NotificationType type;

	private UserDTO target;

	private Map<String, String> variables;

	private boolean active;

	private long created;

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public UserDTO getTarget() {
		return target;
	}

	public void setTarget(UserDTO user) {
		this.target = user;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreated() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date((long) created * 1000));
	}

	public void setCreated(long created) {
		this.created = created;
	}

}