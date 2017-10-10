package edu.msg.ro.bean.bug;

import java.util.List;

import javax.ejb.EJB;

import edu.msg.ro.bean.AbstractBean;
import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.user.boundary.UserFacade;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.bug.entity.Bug;

/**
 * Abstract {@link Bug} bean.
 * 
 * @author balinc
 *
 */
public class AbstractBugBean extends AbstractBean {

	@EJB
	BugFacade bugFacade;

	@EJB
	UserFacade userFacade;

	public static final String I18N_BUG_SAVED = "bug.crud.save.success";

	protected BugSeverity[] severityList = BugSeverity.values();

	/**
	 * Method for get all {@link BugSeverity}.
	 * 
	 * @return
	 */
	public BugSeverity[] getSeverityList() {
		return severityList;
	}

	/**
	 * Set for severityList.
	 * 
	 * @param severityList
	 */
	public void setSeverityList(BugSeverity[] severityList) {
		this.severityList = severityList;
	}

	/**
	 * User autocomplete by username.
	 * 
	 * @param query
	 * @return
	 */
	public List<UserDTO> complete(String query) {
		return userFacade.getAllUserByQuery(query);
	}
}
