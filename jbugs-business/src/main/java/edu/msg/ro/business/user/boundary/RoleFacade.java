package edu.msg.ro.business.user.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.common.exception.TechnicalExeption;
import edu.msg.ro.business.user.control.RoleService;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.persistence.user.entity.Role;

/**
 * Boundary for role component.
 * 
 * @author balinc
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RoleFacade {

	@EJB
	private RoleService roleService;

	public List<RoleDTO> getAllRoles() {
		return roleService.getAllRoles();
	}

	/**
	 * Method for updating an {@link Role}.
	 * 
	 * @param roleDTO
	 *            - the DTO for the {@link Role}.
	 * @return updated {@link Role}.
	 * @throws TechnicalExeption
	 */
	public RoleDTO update(RoleDTO roleDTO) {
		return roleService.update(roleDTO);
	}

	/**
	 * Find role by id.
	 * 
	 * @param id
	 * @return
	 */
	public RoleDTO find(Long id) {
		return roleService.find(id);
	}
}
