package edu.msg.ro.bean.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.common.exception.TechnicalExeption;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Update {@link User} bean.
 * 
 * @author balinc
 *
 */
@ManagedBean
@ViewScoped
public class UserUpdateBean extends AbstractUserBean {

	private List<RoleDTO> selectedRoles;

	/**
	 * {@link UserDTO}
	 */
	private UserDTO updatedUser = new UserDTO();

	/**
	 * {@link UserDTO}
	 */
	private UserDTO oldUser;

	/**
	 * Get user to update.
	 *
	 * @return
	 */
	public UserDTO getUpdatedUser() {
		return updatedUser;
	}

	/**
	 * Set user update.
	 * 
	 * @param userDTO
	 */
	public void setUpdatedUser(UserDTO userDTO) {
		rebuildRoleService();
		this.updatedUser = userDTO;
		this.oldUser = userFacade.getUserByUsername(userDTO.getUsername());
		setSelectedRoles(userDTO.getRoles());
	}

	/**
	 * Method for saving edited {@link User}.
	 * 
	 * @return
	 * @throws TechnicalExeption
	 */
	public void editUser() {
		try {
			UserDTO persistedDTO = userFacade.updateUser(updatedUser);
			addI18nMessage(I18N_SAVED, new Object[] { updatedUser.getUsername() });
			rebuildRoleService();
			log(persistedDTO, oldUser);
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
	}

	/**
	 * Method for reseting the password
	 * 
	 * @throws TechnicalExeption
	 */
	public void resetPassword() throws BusinessException {
		try {
			userFacade.resetPassword(updatedUser);
			addI18nMessage(I18N_RESET);
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
	}

	/**
	 * Return selected roles.
	 * 
	 * @return
	 */
	public List<RoleDTO> getSelectedRoles() {
		return selectedRoles;
	}

	/**
	 * Set the selected roles.
	 * 
	 * @param persistedRoles
	 */
	public void setSelectedRoles(List<RoleDTO> persistedRoles) {
		if (!persistedRoles.isEmpty()) {
			selectedRoles = new ArrayList<RoleDTO>();
			Map<Long, RoleDTO> mappedItems = service.getRoleItemMap();
			for (RoleDTO role : persistedRoles) {
				selectedRoles.add(mappedItems.get(role.getId()));
			}
			this.updatedUser.setRoles(selectedRoles);
		}
	}

	/**
	 * Notification after user update.
	 * 
	 * @param user
	 */
	public void log(UserDTO newUser, UserDTO oldUser) {
		Map<String, String> arguments = new HashMap<String, String>();

		arguments.put("username", oldUser.getUsername());

		arguments.put("old_firstname", oldUser.getFirstname());
		arguments.put("old_lastname", oldUser.getLastname());
		arguments.put("old_email", oldUser.getEmail());
		arguments.put("old_phonenumber", oldUser.getPhoneNumber());

		List<RoleDTO> old_roles = oldUser.getRoles();
		if (roles != null) {
			arguments.put("old_roles", old_roles.toString());
		} else {
			arguments.put("old_roles", null);
		}

		arguments.put("new_firstname", newUser.getFirstname());
		arguments.put("new_lastname", newUser.getLastname());
		arguments.put("new_email", newUser.getEmail());
		arguments.put("new_phonenumber", newUser.getPhoneNumber());
		arguments.put("new_roles", newUser.getRoles().toString());

		List<RoleDTO> new_roles = newUser.getRoles();
		if (roles != null) {
			arguments.put("new_roles", new_roles.toString());
		} else {
			arguments.put("new_roles", null);
		}

		List<UserDTO> users = new ArrayList<UserDTO>();
		users.add(newUser);
		users.add(getLoggedUser());

		noService.newNotification(NotificationType.USER_UPDATED, users, arguments);
	}

}