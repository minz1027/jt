package edu.msg.ro.business.user.dto.mapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.common.dto.mapper.AbstractDTOMapper;
import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.business.user.dto.RoleDTO;
import edu.msg.ro.persistence.user.entity.Role;

/**
 * Mapper for {@link Role} and {@link RoleDTO}
 * 
 * @author varadp
 *
 */
@Stateless
public class RoleDTOMapper extends AbstractDTOMapper<Role, RoleDTO> {

	@EJB
	private PermissionDTOMapper pdm;

	@EJB
	private PermissionDAO pd;

	/**
	 * Method for instaciating an {@link Role}.
	 */
	@Override
	public RoleDTO getDTOInstance() {
		return new RoleDTO();
	}

	/**
	 * Method for filling up the {@link RoleDTO}.
	 */
	@Override
	public void mapEntityToDTOFields(Role entity, RoleDTO dto) {

		entity.getPermissions();

		dto.setPermission(pdm.mapToDTOs(entity.getPermissions()));
		dto.setName(entity.getName());
		dto.setUsers(entity.getUsers());

	}

	/**
	 * Method for filling up the {@link Role}.
	 */
	@Override
	protected void mapDTOToEntityFields(RoleDTO dto, Role entity) {
		entity.setPermissions(pdm.mapToEntities(dto.getPermissions(), pd));
		entity.setName(dto.getName());
		entity.setUsers(dto.getUsers());
	}
}
