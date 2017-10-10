package edu.msg.ro.persistence.notification.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import edu.msg.ro.persistence.common.entity.AbstractEntity;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Notification entity.
 * 
 * @author balinc
 *
 */
@NamedQueries({
		@NamedQuery(name = Notification.TOTAL_ACTIVE_BY_USER, query = "SELECT count(n.id) from Notification n WHERE n.target =:user_id AND n.active = true"),
		@NamedQuery(name = Notification.BY_USER, query = "SELECT n from Notification n WHERE n.target =:user_id Order By n.active DESC, n.created DESC"),
		@NamedQuery(name = Notification.DELETE_OLD, query = "DELETE FROM Notification n WHERE n.created < :time") })
@Entity
public class Notification extends AbstractEntity implements Serializable {

	public static final String TOTAL_ACTIVE_BY_USER = "Notification.TOTAL_ACTIVE_BY_USER";
	public static final String BY_USER = "Notification.BY_USER";
	public static final String DELETE_OLD = "Notification.DELETE_OLD";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7383547195632087610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int type;

	@OneToOne
	private User target;

	private Map<String, String> variables;

	private boolean active;

	@Column(name = "created", nullable = false)
	private long created;

	@PrePersist
	protected void onCreate() {
		created = System.currentTimeMillis() / 1000L;
	}

	@Override
	public Long getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> object) {
		this.variables = object;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}