package edu.msg.ro.bean.bug;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.msg.ro.bean.AbstractBean;
import edu.msg.ro.business.bug.boundary.BugFacade;
import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.security.PermissionChecker;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.persistence.bug.entity.Bug;

/****
 * Bug Bean.****
 * 
 * @author fulops
 *
 */
@ManagedBean
@ViewScoped
public class BugBean extends AbstractBean {

	public static final String I18N_BUG_SAVED = "bug.crud.save.success";

	@EJB
	private BugFacade bugFacade;

	@EJB
	private PermissionChecker permissionChecker;

	@EJB
	private UserService userService;

	private BugDTO newBug = new BugDTO();

	private BugDTO selectedBug = new BugDTO();

	private List<BugDTO> buglist;

	private List<BugDTO> filteredBugList;

	private int severities;

	private int stasuses;

	private UserDTO assignedUser = new UserDTO();

	private StreamedContent downloadAttachment;

	public UserDTO getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserDTO user) {
		this.assignedUser = user;
	}

	/**
	 * Init method.
	 */
	@PostConstruct
	public void init() {
		UserDTO curentUser = null;
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");

		for (UserDTO userDTO : userService.getAllUsers()) {
			if (userDTO.getUsername().equals(username)) {
				curentUser = userDTO;
				break;
			}
		}

		buglist = bugFacade.getAllbugs();

		List<PermissionEnum> permissionList = new ArrayList<>();
		permissionList.add(PermissionEnum.BUG_CLOSE);
		if (!permissionChecker.canAccess(permissionList, curentUser)) {
			buglist.removeIf(e -> e.getStatus().equals(StatusEnum.CLOSE));
		}
	}

	/**
	 * Method for getting all {@link Bug}s.
	 * 
	 * @return
	 */
	public List<BugDTO> getBugList() {
		return buglist;
	}

	/**
	 * Get for filteredBugList.
	 * 
	 * @return
	 */
	public List<BugDTO> getFilteredBugList() {
		return filteredBugList;
	}

	/**
	 * Set for filteredBugList.
	 * 
	 * @param filteredBugList
	 */
	public void setFilteredBugList(List<BugDTO> filteredBugList) {
		this.filteredBugList = filteredBugList;
	}

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
	}

	/**
	 * + Method for getting back all {@link Bug}s.
	 * 
	 * @return
	 */
	public List<BugDTO> getAllBugs() {
		return bugFacade.getAllbugs();
	}

	/**
	 * get download attachment
	 * 
	 * @return
	 */
	public StreamedContent getDownloadAttachment() {
		return downloadAttachment;
	}

	/**
	 * return download attachment
	 * 
	 * @param downloadAttachment
	 */
	public void setDownloadAttachment(StreamedContent downloadAttachment) {
		this.downloadAttachment = downloadAttachment;
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
		newBug = new BugDTO();
		return "bugManagment";
	}

	/**
	 * Method for verifying if needed to render element.
	 * 
	 * @param bug
	 * @return
	 */
	public boolean verifyBugRendere(BugDTO bug) {
		return selectedBug != null && bug.getId().equals(selectedBug.getId());
	}

	/**
	 * Method for editing {@link Bug}.
	 * 
	 * @return
	 */
	public String editBug() {
		bugFacade.updateBug(selectedBug);
		addI18nMessage(I18N_BUG_SAVED, new Object[] { selectedBug.getTitle() });
		selectedBug = new BugDTO();
		return "bugManagment";
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
	 * Method for get all {@link BugStatus}
	 * 
	 * @return
	 */

	public StatusEnum[] getStatusListFilter() {
		return StatusEnum.values();
	}

	/**
	 * Method for get all {@link BugSeverity}.
	 * 
	 * @return
	 */
	public BugSeverity[] getSeverityList() {
		return BugSeverity.values();
	}

	/**
	 * Get for severities.
	 * 
	 * @return
	 */
	public int getSeverities() {
		return severities;
	}

	/**
	 * Set for severities.
	 * 
	 * @param severities
	 */
	public void setSeverities(int severities) {
		this.severities = severities;
	}

	/**
	 * Get for statuses
	 * 
	 * @return
	 */
	public int getStasuses() {
		return stasuses;
	}

	/**
	 * Set for statuses
	 * 
	 * @param stasuses
	 */
	public void setStasuses(int stasuses) {
		this.stasuses = stasuses;
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
	 * Method for upload file to database
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
	 * Downloading attachment from database.
	 * 
	 * @param bug
	 */
	public void fileDownload(BugDTO bug) {
		byte[] convertToInputStream = bug.getAttachment();
		InputStream myInputStream = new ByteArrayInputStream(convertToInputStream);
		downloadAttachment = new DefaultStreamedContent(myInputStream, bug.getAttachmentName(),
				bug.getAttachmentName());
	}

	/**
	 * Delete attachment form database.
	 */
	public void deleteAttachment() {
		selectedBug.setAttachment(null);
		selectedBug.setAttachmentName(null);

	}
}
