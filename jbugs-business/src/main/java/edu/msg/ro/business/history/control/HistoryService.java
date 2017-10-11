package edu.msg.ro.business.history.control;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.bug.dao.BugDAO;
import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.history.dao.HistoryDAO;
import edu.msg.ro.business.history.dto.HistoryDTO;
import edu.msg.ro.business.history.dto.mapper.HistoryDTOMapper;
import edu.msg.ro.business.history.enums.BugFields;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.bug.entity.Bug;
import edu.msg.ro.persistence.history.entity.History;

/**
 * Controller for {@link History} component.
 * 
 * @author balinc
 *
 */
@Stateless
public class HistoryService {

	@EJB
	HistoryDTOMapper hdm;

	@EJB
	HistoryDAO historyDAO;

	@EJB
	HistoryDAO hDAO;

	@EJB
	BugDAO bDAO;

	/**
	 * Create history values after a bug saved.
	 * 
	 * @param histories
	 * @param bug
	 * @param user
	 */
	public void createHistory(ArrayList<TreeMap<String, ArrayList<String>>> histories, BugDTO bug, UserDTO user) {
		for (TreeMap<String, ArrayList<String>> map : histories) {
			HistoryDTO dto = new HistoryDTO();
			dto.setAttribut(BugFields.valueOf(map.firstKey()));
			dto.setBug(bug);
			dto.setModifiedBy(user);
			dto.setOldValue(map.get(map.firstKey()).get(0));
			dto.setNewValue(map.get(map.firstKey()).get(1));

			saveHistory(dto);
		}
	}

	/**
	 * Save new history entity.
	 * 
	 * @param dto
	 * @return
	 */
	public HistoryDTO saveHistory(HistoryDTO dto) {
		History history = new History();
		hdm.mapToEntity(dto, history);
		historyDAO.persistEntity(history);
		History persisted = historyDAO.findEntity(history.getId());
		return hdm.mapToDTO(persisted);
	}

	/**
	 * Get all histories for bug.
	 * 
	 * @param bugDTO
	 * @return
	 */
	public List<HistoryDTO> getAll(BugDTO bugDTO) {
		Bug bug = bDAO.findEntity(bugDTO.getId());
		return hdm.mapToDTOs(hDAO.getAll(bug));
	}
}
