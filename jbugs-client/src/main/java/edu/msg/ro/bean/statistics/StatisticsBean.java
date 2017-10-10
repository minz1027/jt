package edu.msg.ro.bean.statistics;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import edu.msg.ro.bean.AbstractBean;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.statistics.boundary.StatisticsFacade;

@ManagedBean
public class StatisticsBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5715454666286932110L;

	@EJB
	private StatisticsFacade stats;

	private PieChartModel bugStatuses;

	@PostConstruct
	public void init() {
		createbugStatuses();
	}

	/**
	 * 
	 * @return
	 */
	public PieChartModel getBugStatuses() {
		return bugStatuses;
	}

	/**
	 * Statistics for bug statuses.
	 */
	private void createbugStatuses() {
		List<Object[]> statsList = stats.getStatusStats();
		bugStatuses = new PieChartModel();
		bugStatuses.setTitle(t.translate("statistics.status.chart"));
		bugStatuses.setLegendPosition("w");

		if (!statsList.isEmpty()) {
			for (Object[] object : statsList) {
				bugStatuses.set(t.translate("bug.status." + StatusEnum.values()[(int) object[1]].name()),
						(Number) object[0]);
			}
		}
	}
}
