package edu.msg.ro.persistence.user.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import edu.msg.ro.persistence.common.entity.AbstractEntity;

/**
 * Entity for Permission
 * 
 * @author varadp
 *
 */
@NamedQuery(name = Permission.FIND_ALL, query = "SELECT p FROM Permission p")
@Entity
public class Permission extends AbstractEntity {

	public static final String FIND_ALL = "Permission.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String detail;

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + "]";
	}

}
