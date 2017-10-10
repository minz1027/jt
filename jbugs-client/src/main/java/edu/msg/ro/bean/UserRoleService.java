package edu.msg.ro.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.msg.ro.business.user.dao.RoleDAO;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.business.user.dto.mapper.RoleDTOMapper;

/**
 * Controller for Role component.
 * 
 * @author balinc
 *
 */
@ManagedBean(name = "userRoleService")
@ViewScoped
public class UserRoleService {

	@EJB
	private RoleDAO roleDAO;

	@EJB
	private RoleDTOMapper roleDTOMapper;

	private Map<Long, RoleDTO> itemMap = new HashMap<Long, RoleDTO>();

	private List<RoleDTO> roleItems;

	public List<RoleDTO> getAllRoles() {
		roleItems = roleDTOMapper.mapToDTOs(roleDAO.getAll());
		return roleItems;
	}

	public List<RoleDTO> getRoles() {
		return roleItems;
	}

	public Map<Long, RoleDTO> getRoleItemMap() {
		return itemMap;
	}

	@PostConstruct
	public void init() {
		for (RoleDTO dto : getAllRoles()) {
			itemMap.put(dto.getId(), dto);
		}
	}
}
