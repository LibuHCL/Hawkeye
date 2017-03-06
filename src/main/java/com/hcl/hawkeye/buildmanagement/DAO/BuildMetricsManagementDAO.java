package com.hcl.hawkeye.buildmanagement.DAO;

import java.util.List;

import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.portfolio.DO.Graph;

public interface BuildMetricsManagementDAO {

	Graph getBuildsPerDay(int projectId);

	List<BuildStatisticsDetails> getLatestBuildDetails(String planKey, int projectId);

	List<BuildStatisticsDetails> getTodayDetails(String planKey, int projectId);

}
