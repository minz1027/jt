package edu.msg.ro.persistence.user.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import edu.msg.ro.persistence.common.entity.AbstractEntity;

/**
 * 
 * Entity for Role.
 * 
 * @author balinc
 *
 */
@NamedQuery(name = Role.FIND_ALL_ROLES, query = "SELECT r from Role r")
@Entity
public class Role extends AbstractEntity {

	public static final String FIND_ALL_ROLES = "Role.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "idRole"), inverseJoinColumns = {
			@JoinColumn(name = "idPermission") })
	private List<Permission> permissions;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
}
