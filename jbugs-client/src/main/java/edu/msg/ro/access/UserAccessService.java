package edu.msg.ro.access;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.security.PermissionChecker;
import edu.msg.ro.business.user.security.PermissionEnum;

@ManagedBean(name = "userAccessService")
@ApplicationScoped
public class UserAccessService {

	@EJB
	private PermissionChecker permissionChecker;

	@EJB
	private UserFacade userFacade;

	public boolean canAccess(List<Long> permissionIds) {
		if (permissionIds == null) {
			return true;
		}

		UserDTO curentUser = userFacade.getUserByUsername(
				(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));

		return permissionChecker.canAccess(curentUser, permissionIds);
	}

	private boolean canAccessEnum(List<PermissionEnum> permissionIds) {
		if (permissionIds == null) {
			return true;
		}

		UserDTO curentUser = userFacade.getUserByUsername(
				(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));

		return permissionChecker.canAccess(permissionIds, curentUser);
	}

	public boolean canAccessE(PermissionEnum e1) {
		List<PermissionEnum> enumList = new ArrayList<>();
		enumList.add(e1);
		return canAccessEnum(enumList);
	}

	public boolean canAccessE(PermissionEnum e1, PermissionEnum e2) {
		List<PermissionEnum> enumList = new ArrayList<>();
		enumList.add(e1);
		enumList.add(e2);
		return canAccessEnum(enumList);
	}

	public boolean canAccessE(PermissionEnum e1, PermissionEnum e2, PermissionEnum e3) {
		List<PermissionEnum> enumList = new ArrayList<>();
		enumList.add(e1);
		enumList.add(e2);
		enumList.add(e3);
		return canAccessEnum(enumList);
	}

	public boolean canAccessE(PermissionEnum e1, PermissionEnum e2, PermissionEnum e3, PermissionEnum e4) {
		List<PermissionEnum> enumList = new ArrayList<>();
		enumList.add(e1);
		enumList.add(e2);
		enumList.add(e3);
		enumList.add(e4);
		return canAccessEnum(enumList);
	}

}
