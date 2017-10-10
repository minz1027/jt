package edu.msg.ro.persistence.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Pattern;

import edu.msg.ro.persistence.common.entity.AbstractEntity;

/**
 * Entity for the User.
 * 
 * @author balinc
 *
 */

@NamedQueries({ @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u from User u WHERE u.email = :email"),
		@NamedQuery(name = User.FIND_USER_BY_USERNAME_PASS, query = "SELECT u from User u WHERE u.username = :username AND u.password =:password"),
		@NamedQuery(name = User.FIND_ALL, query = "SELECT u from User u"),
		@NamedQuery(name = User.FIND_ALL_BY_ROLE, query = "SELECT u from User u WHERE u.roles = :Role") })
@Entity
public class User extends AbstractEntity {

	public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";
	public static final String FIND_USER_BY_USERNAME_PASS = "User.findUserByUsernameAndPass";
	public static final String FIND_ALL = "User.findAllActive";
	public static final String FIND_ALL_BY_ROLE = "User.findAllByRole";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = {
			@JoinColumn(name = "idRole") })
	private List<Role> roles;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@+[msggroup.com]{12}$")
	private String email;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String phoneNumber;

	@Column
	private boolean active;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

}