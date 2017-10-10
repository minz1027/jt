package edu.msg.ro.business.history.dto;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.common.dto.AbstractDTO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.history.entity.History;

/**
 * Mapper for {@link History} and {@link HistoryDTO}.
 *
 * @author balinc
 *
 */
public class HistoryDTO extends AbstractDTO {

	private BugDTO bug;

	private Byte attribut;

	private String oldValue;

	private String newValue;

	private UserDTO modifiedBy;

	public BugDTO getBug() {
		return bug;
	}

	public void setBug(BugDTO bug) {
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

	public UserDTO getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(UserDTO modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
