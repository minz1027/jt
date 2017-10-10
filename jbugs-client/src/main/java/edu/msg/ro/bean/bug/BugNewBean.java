package edu.msg.ro.bean.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.notification.control.NotificationService;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.bug.entity.Bug;

/**
 * New {@link Bug} bean.
 * 
 * @author balinc
 */
@ManagedBean
@ViewScoped
public class BugNewBean extends AbstractBugBean {

	private BugDTO newBug = new BugDTO();

	private UserDTO assignedUser = new UserDTO();

	@EJB
	private NotificationService noService;

	/**
	 * Get for newBug.
	 * 
	 * @return
	 */
	public BugDTO getNewBug() {
		return newBug;
	}

	/**
	 * Set for newBug.
	 * 
	 * @param bug
	 */
	public void setNewBug(BugDTO bug) {
		this.newBug = bug;
	}

	/**
	 * Get user to assign the {@link Bug}.
	 * 
	 * @return
	 */
	public UserDTO getAssignedUser() {
		return assignedUser;
	}

	/**
	 * Set user to assign the {@link Bug}.
	 * 
	 * @param user
	 */
	public void setAssignedUser(UserDTO user) {
		this.assignedUser = user;
	}

	/**
	 * Method for upload file to database
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		byte[] file = new byte[event.getFile().getContents().length];
		System.arraycopy(event.getFile().getContents(), 0, file, 0, event.getFile().getContents().length);

		newBug.setAttachment(file);
		newBug.setAttachmentName(event.getFile().getFileName());

	}

	/**
	 * make null value for newBug
	 */
	public void refreshNewBug() {
		newBug = new BugDTO();
	}

	/**
	 * Just create a bug without return.
	 */
	public String createNewBug() {
		newBug.setAssigned(assignedUser);
		newBug.setAuthor(getLoggedUser());
		newBug.setStatus(StatusEnum.OPEN);
		bugFacade.createBug(newBug);
		addI18nMessage(I18N_BUG_SAVED, new Object[] { newBug.getTitle() });
		log(newBug);
		newBug = new BugDTO();
		return "bugManagment";
	}

	/**
	 * Notification after user deleted.
	 * 
	 * @param user
	 */
	public void log(BugDTO bug) {
		Map<String, String> arguments = new HashMap<String, String>();

		arguments.put("title", bug.getTitle());
		arguments.put("description", bug.getDescription());
		arguments.put("owner", getLoggedUser().getUsername());
		arguments.put("date", bug.getFixedIn());
		arguments.put("severity", bug.getSeverity().name());
		arguments.put("status", bug.getStatus().name());
		arguments.put("version", bug.getVersion());
		arguments.put("fixedIn", bug.getFixedIn());

		arguments.put("isNew", "${notification.template.yes}");

		List<UserDTO> users = new ArrayList<UserDTO>(2);
		users.add(getLoggedUser());

		if (bug.getAssigned() != null) {
			arguments.put("assigned", bug.getAssigned().getUsername());
			users.add(bug.getAssigned());
		} else {
			arguments.put("assigned", null);
		}

		noService.newNotification(NotificationType.BUG_UPDATED, users, arguments);
	}
}
