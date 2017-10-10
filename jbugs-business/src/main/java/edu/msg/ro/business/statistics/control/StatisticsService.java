package edu.msg.ro.business.statistics.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.bug.dao.BugDAO;

/**
 * Service class for statistics.
 * 
 * @author balinc
 *
 */
@Stateless
public class StatisticsService {

	@EJB
	private BugDAO bugDAO;

	/**
	 * Method for getting the status statistics.
	 * 
	 * @return
	 */
	public List<Object[]> getStatusStats() {
		return bugDAO.getStatusStats();
	}
}
