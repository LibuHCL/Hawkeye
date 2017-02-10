/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.sonarmetrics.DO.Metrics;
import com.hcl.hawkeye.sonarmetrics.DO.Result;
import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;
import com.hcl.hawkeye.sonarmetrics.service.SonarMetricsManagementService;
import com.hcl.hawkeye.sonarmetrics.DAO.SonarMetricsManagementDAO;

/**
 * @author HCL
 *
 */
@Service
public class SonarMetricsManagementServiceImpl implements SonarMetricsManagementService {
	private static final Logger logger = LoggerFactory.getLogger(SonarMetricsManagementService.class);

	@Autowired
	SonarMetricsManagementDAO sonarMetricsManagementDAO;

	@Autowired
	MessageSource messageSource;

	@Override
	public List<SonarMetrics> getSonarMetricsData(Integer projectId) {
		logger.info("Request in getSonarMetricsData of SonarMetricsManagementServiceImpl");
		List<SonarMetrics> sonarMetrics = new ArrayList<SonarMetrics>();
		List<Trackers> trackers = sonarMetricsManagementDAO.getSonarMetricsData(projectId);
		for (int i = 0; i < trackers.size(); i++) {
			{
				Result result = new Result();
				result.set_programId("1002");
				result.set_programName("Engineering Metrics");
				// result.setMetrics(metrics[i].setKey(sonarMetrics.getResult().get(i).getMetrics()[i].getKey()));
				result.getMetrics().get(i).setKey("Blockers");
				result.getMetrics().get(i).setKey("Technical Debt");
				result.getMetrics().get(i).setKey("Code Complexity");
				result.getMetrics().get(i).setKey("Duplicated Lines");

			}

		}
		return sonarMetrics;
	}
}
