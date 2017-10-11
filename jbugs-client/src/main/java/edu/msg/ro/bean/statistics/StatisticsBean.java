package edu.msg.ro.bean.statistics;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
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

	private BarChartModel bugRejected;

	private List<Object[]> statusList;

	@PostConstruct
	public void init() {
		statusList = stats.getStatusStats();
		createBugStatuses();
		createBugRejected();
	}

	/**
	 * Get status pie chart.
	 * 
	 * @return
	 */
	public PieChartModel getBugStatuses() {
		return bugStatuses;
	}

	/**
	 * Get rejected and total bugs.
	 * 
	 * @return
	 */
	public BarChartModel getBugRejected() {
		return bugRejected;
	}

	/**
	 * Statistics for bug statuses.
	 */
	private void createBugStatuses() {
		bugStatuses = new PieChartModel();
		bugStatuses.setTitle(t.translate("statistics.status.chart"));
		bugStatuses.setLegendPosition("w");

		if (!statusList.isEmpty()) {
			for (Object[] object : statusList) {
				bugStatuses.set(t.translate("bug.status." + StatusEnum.values()[(int) object[1]].name()),
						(Number) object[0]);
			}
		}
	}

	/**
	 * Statistics to show rejected bug and total.
	 */
	private void createBugRejected() {
		bugRejected = new BarChartModel();
		bugRejected.setTitle(t.translate("statistics.rejected.chart"));

		int count = 0;
		int count_rej = 0;
		if (!statusList.isEmpty()) {
			for (Object[] object : statusList) {
				count += (int) (long) object[0];
				if (StatusEnum.REJECTED.key == (int) object[1]) {
					count_rej = (int) (long) object[0];
				}
			}
		}

		ChartSeries total = new ChartSeries();
		total.setLabel(t.translate("statistics.status.total"));
		total.set("bugs", count);

		ChartSeries rejected = new ChartSeries();
		rejected.setLabel(t.translate("bug.status." + StatusEnum.REJECTED));
		rejected.set("bugs", count_rej);

		bugRejected.addSeries(total);
		bugRejected.addSeries(rejected);
	}
}
