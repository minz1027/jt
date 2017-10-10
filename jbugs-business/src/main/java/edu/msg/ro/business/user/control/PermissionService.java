package edu.msg.ro.business.user.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.business.user.dto.PermissionDTO;
import edu.msg.ro.business.user.dto.mapper.PermissionDTOMapper;
import edu.msg.ro.persistence.user.entity.Permission;

/**
 * Controller for Permission component.
 * 
 * @author varadp
 *
 */
@Stateless
public class PermissionService {

	@EJB
	private PermissionDAO permissionDAO;

	@EJB
	private PermissionDTOMapper permissionDTOMapper;

	/**
	 * Method for getting back all {@link Permission}s.
	 * 
	 * @return
	 */
	public List<PermissionDTO> getAllPermissions() {
		return permissionDTOMapper.mapToDTOs(permissionDAO.getAll());
	}

}
