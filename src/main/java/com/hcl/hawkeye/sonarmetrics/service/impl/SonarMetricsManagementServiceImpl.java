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
public class SonarMetricsManagementServiceImpl implements SonarMetricsManagementService {
	private static final Logger logger = LoggerFactory.getLogger(SonarMetricsManagementService.class);

	@Autowired
	SonarMetricsManagementDAO sonarMetricsManagementDAO;

	@Autowired
	MessageSource messageSource;

	@Override
	public Map <String, Graph> getSonarMetricsData(Integer projectId) {
		logger.info("Request in getSonarMetricsData of SonarMetricsManagementServiceImpl");
		Map <String, Graph> engMap= new HashMap<String, Graph>();
		List<SonarMetrics> sonarMetrics = new ArrayList<SonarMetrics>();
		List<Trackers> trackers = sonarMetricsManagementDAO.getSonarMetricsData(projectId);
		for (Trackers track : trackers) {
			Graph g = new Graph();
			ArrayList<Double> graphData = new ArrayList<Double>();
			ArrayList<String> labels = new ArrayList<String>();
			graphData.add((track.getComplexity()));
			labels.add(track.getSprint());
			g.setGraphData(graphData);
			g.setLabels(labels);
			engMap.put("Code Violation",g);
			
			
			Graph g1 = new Graph();
			ArrayList<Double> graphData1 = new ArrayList<Double>();
			ArrayList<String> labels1 = new ArrayList<String>();
			graphData1.add((track.getBlockers().doubleValue()));
			labels1.add(track.getSprint());
			g1.setGraphData(graphData1);
			g1.setLabels(labels1);
			engMap.put("Blockers",g1);
			
			
			Graph g2 = new Graph();
			ArrayList<Double> graphData2 = new ArrayList<Double>();
			ArrayList<String> labels2 = new ArrayList<String>();
			graphData2.add((track.getTechnicalDebt()));
			labels2.add(track.getSprint());
			g2.setGraphData(graphData2);
			g2.setLabels(labels2);
			engMap.put("Technical Debt",g2);
			
			
			Graph g3 = new Graph();
			ArrayList<Double> graphData3 = new ArrayList<Double>();
			ArrayList<String> labels3 = new ArrayList<String>();
			graphData3.add((track.getCommentedLines().doubleValue()));
			labels3.add(track.getSprint());
			g3.setGraphData(graphData3);
			g3.setLabels(labels);
			engMap.put("Commented Lines",g3);

		}
		return engMap;
	}
}
