package com.hcl.hawkeye.metric.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.MetricDataDO.PortfolioDO;
import com.hcl.hawkeye.MetricDataDO.ProgramDO;
import com.hcl.hawkeye.MetricDataDO.ProjectDo;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface MetricDataService {

	MetricDataDO getMetricGraph(String screenname);
	
	List<MetricDataDO> getNumberofMetricName(String screenName);
	
	Map<String, String> getMetricsDetail(String screenName);
	
	MetricConfiguration createMetricConfig(MetricConfiguration metricConfig);
	
	Map<String, List<String>> getMetricscreenDetail();

	List<MetricData> getMetricData();

	List<PortfolioDO> getPortfolioDetails();

	Map<String, List<ProgramDO>> getProgramDetails(int portfolioID);

	List<ProgramDO> getProgramNotAssociateDetails(int portfolioID);
		
	Map<String, List<ProjectDo>> getProjectDetails(int progrmaID);
	
	List<ProgramDO> getPorgramList();
	
	void addProjectsToProgram(List<Project> projectList);
	
}