package com.hcl.hawkeye.buildmanagement.service;

import java.util.List;

import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.programingkpis.DO.Result;

public interface BuildMetricsManagementService {

	public Graph getBuildsPerDay(int projectId) ;

	public List<BuildStatisticsDetails> getLatestBuildDetails(String planKey, int projectId);

	public List<BuildStatisticsDetails> getTodayDetails(String planKey, int projectId);
	
	public Graph getAvgBuildDuration(int projectId);
	
	

}
