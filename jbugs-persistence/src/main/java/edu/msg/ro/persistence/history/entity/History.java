package edu.msg.ro.persistence.history.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import edu.msg.ro.persistence.bug.entity.Bug;
import edu.msg.ro.persistence.common.entity.AbstractEntity;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Entity for the History.
 * 
 * @author balinc
 *
 */
@Entity
public class History extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Bug bug;

	@Column
	private Byte attribut;

	@Lob
	@Column(name = "old")
	private String oldValue;

	@Lob
	@Column(name = "new")
	private String newValue;

	@Column
	private long date;

	@OneToOne
	private User modifiedBy;

	@PrePersist
	protected void onCreate() {
		date = System.currentTimeMillis() / 1000L;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public Byte getAttribut() {
		return attribut;
	}

	public void setAttribut(Byte attribut) {
		this.attribut = attribut;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
