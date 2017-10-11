package edu.msg.ro.business.history.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.history.control.HistoryService;
import edu.msg.ro.business.history.dto.HistoryDTO;
import edu.msg.ro.persistence.history.entity.History;

/**
 * Boundary for {@link History} component.
 *
 * @author balinc
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HistoryFacade {

	@EJB
	private HistoryService historieService;

	/**
	 * Method for getting back all {@link History}s.
	 * 
	 * @return
	 */
	public List<HistoryDTO> getAll(BugDTO bug) {
		return historieService.getAll(bug);
	}
}
