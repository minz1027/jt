package edu.msg.ro.bean.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import edu.msg.ro.bean.AbstractBean;
import edu.msg.ro.bean.UserRoleService;
import edu.msg.ro.business.notification.control.NotificationService;
import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dto.RoleDTO;

/**
 * User bean abstract functionality.
 * 
 * @author balinc
 *
 */
abstract class AbstractUserBean extends AbstractBean {

	@EJB
	protected UserFacade userFacade;

	@EJB
	protected NotificationService noService;

	@ManagedProperty("#{userRoleService}")
	protected UserRoleService service;

	protected List<RoleDTO> roles;

	/**
	 * Notification message key.
	 */
	public static final String I18N_SAVED = "user.crud.save.success";
	public static final String I18N_RESET = "user.resetedPassword";

	/**
	 * Init function.
	 */
	@PostConstruct
	public void init() {
		roles = service.getRoles();
	}

	/**
	 * Get for roles.
	 * 
	 * @return
	 */
	public List<RoleDTO> getRoles() {
		return roles;
	}

	/**
	 * Set User Role Service.
	 * 
	 * @param service
	 */
	public void setService(UserRoleService service) {
		this.service = service;
	}

	/**
	 * Rebuild role list.
	 */
	protected void rebuildRoleService() {
		service.init();
		this.init();
	}
}
