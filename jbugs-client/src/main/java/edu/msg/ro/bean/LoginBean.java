package edu.msg.ro.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.notification.control.NotificationService;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.boundary.LoginFacade;
import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dao.RoleDAO;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.util.RoleEnum;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Login Bean class.
 * 
 * @author balinc
 */
@ManagedBean
@RequestScoped
public class LoginBean extends AbstractBean implements Serializable {

	private static final String FAILEDATTEMPSC = "FAILEDATTEMPS";
	private static final String LOGIN = "login";
	private transient HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

	private static final long serialVersionUID = -2617767540112561117L;

	private UserDTO user = new UserDTO();

	@EJB
	private transient LoginFacade loginFacade;

	@EJB
	private transient UserFacade userFacade;

	@EJB
	protected NotificationService noService;

	@EJB
	private RoleDAO roleDAO;

	@EJB
	private UserDAO userDAO;

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * The user to set
	 * 
	 * @param user
	 */
	public void setUser(UserDTO userDTO) {
		this.user = userDTO;
	}

	/**
	 * If the password is wrong five times consecutively the user is deactivated
	 */
	private void toManyFailedPassword() {
		String loggingUser = user.getUsername();
		if (loggingUser.equals(session.getAttribute("OLDUSERNAME"))) {
			int failedlogins = Integer.parseInt(session.getAttribute(FAILEDATTEMPSC).toString());
			failedlogins++;
			session.setAttribute(FAILEDATTEMPSC, failedlogins);
			if (failedlogins == 4) {
				userFacade.deleteUserNoCheck(user);
				log(user);
			}
		} else {
			session.setAttribute(FAILEDATTEMPSC, 0);
			session.setAttribute("OLDUSERNAME", user.getUsername());
		}
		if ((Integer.parseInt(session.getAttribute(FAILEDATTEMPSC).toString()) < 4)) {
			addI18nMessage("loginForm:username", "login.error");
		} else {
			addI18nMessage("loginForm:username", "login.wrongpassword");
		}
	}

	/**
	 * Loggin for the User.
	 *
	 * @return
	 */
	public String processLogin() {
		try {
			if (loginFacade.isValidUser(user)) {
				UserDTO persistedUser = userFacade.getUserByUsername(user.getUsername());
				session.setAttribute("username", persistedUser.getUsername());
				session.setAttribute("loggedUser", persistedUser);
				addI18nMessage("login.welcome");
				session.setAttribute(FAILEDATTEMPSC, 0);
				return "bugManagment";
			} else {
				toManyFailedPassword();
			}
		} catch (JBugsExeption e) {
			handleExeptionI18n(e);
		}
		return LOGIN;

	}

	/**
	 * Log out the User.
	 *
	 * @return
	 */
	public String processLogout() {
		session.invalidate();
		return LOGIN;
	}

	/**
	 * Notification after user deactivated.
	 * 
	 * @param user
	 */
	public void log(UserDTO userDTO) {
		User user = userDAO.findUserByUsername(userDTO.getUsername());
		if (user != null) {
			Map<String, String> arguments = new HashMap<String, String>();

			arguments.put("username", user.getUsername());
			arguments.put("firstname", user.getFirstname());
			arguments.put("lastname", user.getLastname());
			arguments.put("email", user.getEmail());
			arguments.put("phonenumber", user.getPhoneNumber());

			List<Role> roles = user.getRoles();
			if (roles != null) {
				arguments.put("roles", user.getRoles().toString());
			} else {
				arguments.put("roles", null);
			}

			Role role = roleDAO.findEntity(RoleEnum.ADMIN.getId());
			noService.newNotification(NotificationType.USER_DEACTIVATED, userFacade.getUsersByRole(role), arguments);
		}
	}

}