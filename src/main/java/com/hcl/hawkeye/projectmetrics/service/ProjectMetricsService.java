package com.hcl.hawkeye.projectmetrics.service;

import com.hcl.hawkeye.projectmetrics.DO.ProjectDetails;
import com.hcl.hawkeye.projectmetrics.DO.ProjectMetrics;

public interface ProjectMetricsService {

	ProjectMetrics getProjMetricsData(int projectId);
	
	ProjectDetails getProjectByProgramId(Integer programId);

}
