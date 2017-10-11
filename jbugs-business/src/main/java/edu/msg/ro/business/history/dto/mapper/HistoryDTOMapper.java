package edu.msg.ro.business.history.dto.mapper;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.bug.dao.BugDAO;
import edu.msg.ro.business.bug.dto.mapper.BugDTOMapper;
import edu.msg.ro.business.common.dto.mapper.AbstractDTOMapper;
import edu.msg.ro.business.history.dto.HistoryDTO;
import edu.msg.ro.business.history.enums.BugFields;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.persistence.history.entity.History;

/**
 * DTO mapper for {@link HistoryDTO}
 * 
 * @author balinc
 *
 */
@Stateless
public class HistoryDTOMapper extends AbstractDTOMapper<History, HistoryDTO> {

	@EJB
	UserDTOMapper udm;

	@EJB
	BugDTOMapper bdm;

	@EJB
	UserDAO userDAO;

	@EJB
	BugDAO bugDAO;

	@Override
	public HistoryDTO getDTOInstance() {
		return new HistoryDTO();
	}

	@Override
	protected void mapEntityToDTOFields(History entity, HistoryDTO dto) {
		dto.setAttribut(BugFields.values()[entity.getAttribut()]);
		dto.setBug(bdm.mapToDTO(entity.getBug()));
		dto.setModifiedBy(udm.mapToDTO(entity.getModifiedBy()));
		dto.setNewValue(entity.getNewValue());
		dto.setOldValue(entity.getOldValue());

		Date date = new Date((long) entity.getDate() * 1000);
		dto.setDate(date);

	}

	@Override
	protected void mapDTOToEntityFields(HistoryDTO dto, History entity) {
		entity.setAttribut(dto.getAttribut().getId());
		entity.setBug(bugDAO.findEntity(dto.getBug().getId()));
		entity.setModifiedBy(userDAO.findEntity(dto.getModifiedBy().getId()));
		entity.setNewValue(dto.getNewValue());
		entity.setOldValue(dto.getOldValue());
	}
}
