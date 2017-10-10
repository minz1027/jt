package edu.msg.ro.business.user.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.user.control.PermissionService;
import edu.msg.ro.business.user.dto.PermissionDTO;
import edu.msg.ro.persistence.user.entity.Permission;

/**
 * Boundary for permission component.
 * 
 * @author varadp
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PermissionFacade {

	@EJB
	private PermissionService permissionService;

	/**
	 * Method for getting back all {@link Permission}s.
	 * 
	 * @return
	 */
	public List<PermissionDTO> getAll() {
		return permissionService.getAllPermissions();
	}
}
