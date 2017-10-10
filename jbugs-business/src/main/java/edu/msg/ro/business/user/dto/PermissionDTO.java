package edu.msg.ro.business.user.dto;

import java.io.Serializable;
import java.util.List;

import edu.msg.ro.business.common.dto.AbstractDTO;
import edu.msg.ro.persistence.user.entity.Permission;

/**
 * DTO for {@link Permission} for update
 * 
 * @author varadp
 *
 */
public class PermissionDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8505326630336446137L;

	private String name;

	private String detail;

	private List<RoleDTO> role;

	/**
	 * Get fot name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set for name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * get for roles.
	 * 
	 * @return
	 */
	public List<RoleDTO> getRole() {
		return role;
	}

	/**
	 * Set for roles.
	 * 
	 * @param role
	 */
	public void setRole(List<RoleDTO> role) {
		this.role = role;
	}

	/**
	 * ToString for {@link PermissionDTO}.
	 */
	@Override
	public String toString() {
		return "PermissionDTO [getId()=" + getId() + ", role=" + role + "]";
	}

	/**
	 * Equals for {@link PermissionDTO}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissionDTO other = (PermissionDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
