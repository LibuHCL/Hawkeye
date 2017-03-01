package com.hcl.hawkeye.metric.DAO;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.MetricDataDO.PortfolioDO;
import com.hcl.hawkeye.MetricDataDO.ProgramDO;
import com.hcl.hawkeye.MetricDataDO.ProjectDo;

public interface MetricDataDAO {

	MetricDataDO getMetricGraph(String screenname);
	
	List<MetricDataDO> getNumberofMetricName(String screenName);
	
	Map<String, String> getMetricsDetail(String screenName);
	
	MetricConfiguration createMetricConfig(MetricConfiguration metricConfig);
	
	List<MetricDataDO> getMetricscreenDetail();

	List<MetricData> getMetricData();

	List<PortfolioDO> getPortfolioDetails();

	List<ProgramDO> getProgramDetails(int portfolioID);

	List<ProgramDO> getProgramNotAssociateDetails(int portfolioID);
	
	List<ProjectDo> getProjectDetails(int programID);

	List<ProjectDo> getProjectNotAssociateDetails(int programID);

	}
