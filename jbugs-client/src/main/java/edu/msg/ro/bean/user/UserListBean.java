package edu.msg.ro.bean.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.persistence.user.entity.User;

@ManagedBean
@RequestScoped
public class UserListBean extends AbstractUserBean {

	private UserDTO userDTO = new UserDTO();

	@EJB
	private UserService userService;

	/**
	 * Notification message key.
	 */
	public static final String I18N_DELETE = "user.crud.delete.success";

	/**
	 * Notification message key.
	 */
	public static final String I18N_ACTIVATE = "user.crud.activate.success";

	/**
	 * Method for verifying if element needed to render.
	 * 
	 * @param user
	 * @return
	 */
	public boolean verifyUserRendere(UserDTO user) {
		return userDTO != null && user.getId().equals(userDTO.getId());
	}

	/**
	 * get for User.
	 * 
	 * @return
	 */
	public UserDTO geUser() {
		return userDTO;
	}

	/**
	 * Method for get all {@link User}s.
	 * 
	 * @return
	 */
	public List<UserDTO> getAllUsers() {
		return userFacade.getAllUsers();
	}

	/**
	 * Method for deleting(deactivating) {@link User}.
	 * 
	 * @param user
	 * @return
	 */
	public void deleteUser(UserDTO user) {
		try {
			userFacade.deleteUser(user);
			addI18nMessage(I18N_DELETE, new Object[] { user.getUsername() });
			log(user);
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
	}

	/**
	 * Method for activating user {@link User}.
	 * 
	 * @param user
	 */
	public void activateUser(UserDTO user) {
		user.setActive(true);
		try {
			userFacade.updateUser(user);
			addI18nMessage(I18N_ACTIVATE, new Object[] { user.getUsername() });
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
	}

	/**
	 * Notification after user deleted.
	 * 
	 * @param user
	 */
	public void log(UserDTO user) {
		Map<String, String> arguments = new HashMap<String, String>();

		arguments.put("username", user.getUsername());
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

		noService.newNotification(NotificationType.USER_DELETED,
				userService.findAllByPermission(PermissionEnum.USER_MANAGEMENT), arguments);
	}

}
