package edu.msg.ro.business.statistics.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.statistics.control.StatisticsService;

/**
 * Facade class for statistics.
 * 
 * @author balinc
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class StatisticsFacade {

	@EJB
	private StatisticsService statsService;

	/**
	 * Method for getting the status statistics.
	 * 
	 * @return
	 */
	public List<Object[]> getStatusStats() {
		return statsService.getStatusStats();
	}

}
