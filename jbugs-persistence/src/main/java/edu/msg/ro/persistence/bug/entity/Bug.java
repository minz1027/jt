package edu.msg.ro.persistence.bug.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.msg.ro.persistence.common.entity.AbstractEntity;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Entity for the Bug.
 * 
 * @author balinc
 *
 */
@NamedQueries({ @NamedQuery(name = Bug.FIND_ALL, query = "SELECT b from Bug b"),
		@NamedQuery(name = Bug.DELETE_ATTACHMENT, query = "Update Bug b SET b.attachment = NULL, b.attachmentName = NULL where b.id =:id") })
@Entity
public class Bug extends AbstractEntity {

	public static final String FIND_ALL = "Bug.FIND_ALL";
	public static final String DELETE_ATTACHMENT = "Bug.DELETE_ATTACHMENT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Lob
	@Column
	private String description;

	@Temporal(TemporalType.DATE)
	private Date targetDate;

	@Column
	private int severity;

	@OneToOne
	private User author;

	@Column
	private int status;

	@ManyToOne
	private User assigned;

	@Column
	private String version;

	@Column
	private String fixedIn;

	@Lob
	@Column
	private byte[] attachment;

	@Column
	private String attachmentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public User getAssigned() {
		return assigned;
	}

	public void setAssigned(User assigned) {
		this.assigned = assigned;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFixedIn() {
		return fixedIn;
	}

	public void setFixedIn(String fixedIn) {
		this.fixedIn = fixedIn;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}
