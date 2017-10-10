package edu.msg.ro.bean.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Add new {@link User} bean.
 * 
 * @author balinc
 *
 */
@ManagedBean
@RequestScoped
public class UserNewBean extends AbstractUserBean {

	/**
	 * {@link UserDTO}
	 */
	private UserDTO newUser = new UserDTO();

	/**
	 * Get for newUser.
	 * 
	 * @return
	 */
	public UserDTO getNewUser() {
		return newUser;
	}

	/**
	 * Set for newUser.
	 * 
	 * @param user
	 */
	public void clearUser() {
		this.newUser = new UserDTO();
		rebuildRoleService();
	}

	/**
	 * Method for creating new {@link User}.
	 */
	public void createNewUser() {
		try {
			UserDTO persistedDTO = userFacade.createUser(newUser);
			addI18nMessage(I18N_SAVED, new Object[] { newUser.getUsername() });
			clearUser();
			log(persistedDTO);
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
	}

	/**
	 * Notification after user insert.
	 * 
	 * @param user
	 */
	public void log(UserDTO user) {
		Map<String, String> arguments = new HashMap<String, String>();

		arguments.put("firstname", user.getFirstname());
		arguments.put("lastname", user.getLastname());
		arguments.put("email", user.getEmail());
		arguments.put("phonenumber", user.getPhoneNumber());

		List<RoleDTO> roles = user.getRoles();
		if (roles != null) {
			arguments.put("roles", roles.toString());
		} else {
			arguments.put("roles", null);
		}

		noService.newNotification(NotificationType.WELCOME_NEW_USER, user, arguments);
	}
}
