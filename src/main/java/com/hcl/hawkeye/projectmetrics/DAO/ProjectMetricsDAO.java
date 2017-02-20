package com.hcl.hawkeye.projectmetrics.DAO;

import com.hcl.hawkeye.projectmetrics.DO.ProjectDetails;

public interface ProjectMetricsDAO {

	public ProjectDetails getProjectByProgramId(Integer programId);

}
