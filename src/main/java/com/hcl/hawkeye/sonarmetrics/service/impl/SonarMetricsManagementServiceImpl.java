/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.sonarmetrics.DAO.SonarMetricsManagementDAO;
import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;
import com.hcl.hawkeye.sonarmetrics.service.SonarMetricsManagementService;

/**
 * @author HCL
 *
 */
@Service
public class SonarMetricsManagementServiceImpl implements
		SonarMetricsManagementService {
	private static final Logger logger = LoggerFactory
			.getLogger(SonarMetricsManagementService.class);

	@Autowired
	SonarMetricsManagementDAO sonarMetricsManagementDAO;

	@Autowired
	MessageSource messageSource;

	@Override
	public Map<String, Graph> getSonarMetricsData(Integer projectId) {
		logger.info("Request in getSonarMetricsData of SonarMetricsManagementServiceImpl");
		Map<String, Graph> engMap = new HashMap<String, Graph>();
		List<SonarMetrics> sonarMetrics = new ArrayList<SonarMetrics>();
		List<Trackers> trackers = sonarMetricsManagementDAO
				.getSonarMetricsJobData(projectId);
		
		Graph codeComplexityGraph = new Graph();
		ArrayList<Double> codeComplexityGraphData = new ArrayList<Double>();
		ArrayList<String> codeComplexityLabels = new ArrayList<String>();

		Graph blockerGraph = new Graph();
		ArrayList<Double> blockerIssueGraphData = new ArrayList<Double>();
		ArrayList<String> blockerIssueLabels = new ArrayList<String>();

		Graph technicalDebtGraph = new Graph();
		ArrayList<Double> technicalDebtGraphData = new ArrayList<Double>();
		ArrayList<String> technicalDebtLables = new ArrayList<String>();

		Graph duplicatedLineGraph = new Graph();
		ArrayList<Double> duplicateLineGraphData = new ArrayList<Double>();
		ArrayList<String> duplicateLineLabels = new ArrayList<String>();

		for (Trackers track : trackers) {

			codeComplexityGraphData.add((track.getComplexity()));
			codeComplexityLabels.add(track.getSprint());

			blockerIssueGraphData.add((track.getBlockers().doubleValue()));
			blockerIssueLabels.add(track.getSprint());

			technicalDebtGraphData.add((track.getTechnicalDebt()));
			technicalDebtLables.add(track.getSprint());

			duplicateLineGraphData
					.add((track.getDuplicateLines().doubleValue()));
			duplicateLineLabels.add(track.getSprint());

		}

		codeComplexityGraph.setGraphData(codeComplexityGraphData);
		codeComplexityGraph.setLabels(codeComplexityLabels);
		engMap.put("Code Complexity", codeComplexityGraph);

		blockerGraph.setGraphData(blockerIssueGraphData);
		blockerGraph.setLabels(blockerIssueLabels);
		engMap.put("Blockers", blockerGraph);

		technicalDebtGraph.setGraphData(technicalDebtGraphData);
		technicalDebtGraph.setLabels(technicalDebtLables);
		engMap.put("Technical Debt", technicalDebtGraph);
		
		duplicatedLineGraph.setGraphData(duplicateLineGraphData);
		duplicatedLineGraph.setLabels(codeComplexityLabels);
		engMap.put("Duplicate Lines", duplicatedLineGraph);
		
		return engMap;

		
	}
}
