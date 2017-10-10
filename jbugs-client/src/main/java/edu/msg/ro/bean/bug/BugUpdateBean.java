package edu.msg.ro.bean.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.common.util.EntityDiferences;
import edu.msg.ro.business.history.control.HistoryService;
import edu.msg.ro.business.notification.control.NotificationService;
import edu.msg.ro.business.notification.util.NotificationType;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.security.PermissionChecker;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.persistence.bug.entity.Bug;

/**
 * Update {@link Bug} bean.
 * 
 * @author balinc
 *
 */
@ManagedBean
@ViewScoped
public class BugUpdateBean extends AbstractBugBean {

	@EJB
	private PermissionChecker permissionChecker;

	@EJB
	private NotificationService noService;

	@EJB
	private HistoryService history;

	private BugDTO selectedBug = new BugDTO();

	private BugDTO oldBug;

	/**
	 * Get for selectedBug.
	 * 
	 * @return
	 */
	public BugDTO getSelectedBug() {
		return selectedBug;
	}

	/**
	 * Set for selectedBug.
	 * 
	 * @param selectedBug
	 */
	public void setSelectedBug(BugDTO selectedBug) {
		this.selectedBug = selectedBug;
		this.oldBug = bugFacade.getBug(selectedBug.getId());
	}

	/**
	 * Method for gett all {@link BugStatus}.
	 * 
	 * @return
	 */
	public ArrayList<StatusEnum> getStatusList() {
		ArrayList<StatusEnum> response = new ArrayList<StatusEnum>();
		if (selectedBug.getId() == null) {
			return response;
		}
		StatusEnum selected = getSelectedBug().getStatus();
		response.add(selected);
		response.addAll(selected.neighbors);

		List<PermissionEnum> permissionList = new ArrayList<>();
		permissionList.add(PermissionEnum.BUG_CLOSE);

		UserDTO curentUser = getLoggedUser();

		if (!selectedBug.getStatus().equals(StatusEnum.CLOSE)
				&& !permissionChecker.canAccess(permissionList, curentUser)) {
			response.removeIf(e -> e.equals(StatusEnum.CLOSE));
		}

		return response;
	}

	/**
	 * Method for upload file to database.
	 * 
	 * @param event
	 */
	public void handleFileEdit(FileUploadEvent event) {

		byte[] file = new byte[event.getFile().getContents().length];
		System.arraycopy(event.getFile().getContents(), 0, file, 0, event.getFile().getContents().length);

		selectedBug.setAttachment(file);
		selectedBug.setAttachmentName(event.getFile().getFileName());
	}

	/**
	 * Delete attachment form database.
	 */
	public void deleteAttachment() {
		selectedBug.setAttachment(null);
		selectedBug.setAttachmentName(null);
	}

	/**
	 * Method for editing {@link Bug}.
	 * 
	 * @return
	 */
	public String editBug() {
		bugFacade.updateBug(selectedBug);
		addI18nMessage(I18N_BUG_SAVED, new Object[] { selectedBug.getTitle() });
		log(selectedBug);
		selectedBug = new BugDTO();
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

		arguments.put("isNew", "${notification.template.no}");

		List<UserDTO> users = new ArrayList<UserDTO>(2);
		users.add(getLoggedUser());

		if (bug.getAssigned() != null) {
			arguments.put("assigned", bug.getAssigned().getUsername());
			users.add(bug.getAssigned());
		} else {
			arguments.put("assigned", null);
		}

		NotificationType type = NotificationType.BUG_UPDATED;
		if (!bug.getStatus().equals(oldBug.getStatus())) {
			type = NotificationType.BUG_STATUS_UPDATED;
			arguments.put("old_status", oldBug.getStatus().name());
		}
		if (bug.getStatus().equals(StatusEnum.CLOSE)) {
			type = NotificationType.BUG_CLOSED;
		}

		EntityDiferences ed = new EntityDiferences();
		try {
			ArrayList<TreeMap<String, ArrayList<String>>> histories = ed.checkEntitiesDiferences(oldBug, bug);
			history.createHistory(histories, bug, getLoggedUser());
		} catch (BusinessException e) {
			// @Todo - Log this error.
		}
		noService.newNotification(type, users, arguments);
	}
}
