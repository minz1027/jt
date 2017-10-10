package edu.msg.ro.business.bug.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.dto.AbstractDTO;
import edu.msg.ro.business.common.util.SkipFields;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.bug.entity.Bug;

/**
 * Mapper for {@link Bug} and {@link BugDTO}.
 *
 * @author balinc
 *
 */
public class BugDTO extends AbstractDTO implements SkipFields {

	private String title;

	private String description;

	private Date targetDate;

	private BugSeverity severity;

	private UserDTO author;

	private StatusEnum status;

	private UserDTO assigned;

	private String version;

	private String fixedIn;

	private byte[] attachment;

	private String attachmentName;

	/**
	 * Get for title.
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set for title.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get for description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set for description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get for targetdate.
	 * 
	 * @return
	 */
	public Date getTargetDate() {
		return targetDate;
	}

	/**
	 * Set for targetdate.
	 * 
	 * @param targetDate
	 */
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	/**
	 * Get for severity.
	 * 
	 * @return
	 */
	public BugSeverity getSeverity() {
		return severity;
	}

	/**
	 * Set for severity.
	 * 
	 * @param severity
	 */
	public void setSeverity(BugSeverity severity) {
		this.severity = severity;
	}

	/**
	 * Get for author.
	 * 
	 * @return
	 */
	public UserDTO getAuthor() {
		return author;
	}

	/**
	 * Set for author.
	 * 
	 * @param author
	 */
	public void setAuthor(UserDTO author) {
		this.author = author;
	}

	/**
	 * Get for Status.
	 * 
	 * @return
	 */
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * Set for status.
	 * 
	 * @param statusEnum
	 */
	public void setStatus(StatusEnum statusEnum) {
		this.status = statusEnum;
	}

	/**
	 * get for AssignedUser.
	 * 
	 * @return
	 */
	public UserDTO getAssigned() {
		return assigned;
	}

	/**
	 * Set for assignedUser.
	 * 
	 * @param assigned
	 */
	public void setAssigned(UserDTO assigned) {
		this.assigned = assigned;
	}

	/**
	 * Get for version.
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set for version.
	 * 
	 * @param version2
	 */
	public void setVersion(String version2) {
		this.version = version2;
	}

	/**
	 * Get for Fixedin.
	 * 
	 * @return
	 */
	public String getFixedIn() {
		return fixedIn;
	}

	/**
	 * Set for fixedin.
	 * 
	 * @param fixedIn
	 */
	public void setFixedIn(String fixedIn) {
		this.fixedIn = fixedIn;
	}

	/**
	 * get for attachment.
	 * 
	 * @return
	 */
	public byte[] getAttachment() {
		return attachment;
	}

	/**
	 * Set for attachment.
	 * 
	 * @param attachment
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	/**
	 * getter for attachment name
	 * 
	 * @return
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * setter for attachemtn name
	 * 
	 * @param attachmentName
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * toString.
	 * 
	 * @param attachment
	 */
	@Override
	public String toString() {
		return "BugDTO [title=" + title + ", description=" + description + ", targetDate=" + targetDate + ", severity="
				+ severity + ", author=" + author + ", status=" + status + ", assigned=" + assigned + ", version="
				+ version + ", fixedIn=" + fixedIn + ", attachment=" + Arrays.toString(attachment) + "]";
	}

	@Override
	public List<String> skipFields() {
		ArrayList<String> list = new ArrayList<String>(1);
		list.add("attachment");
		return list;
	}
}
