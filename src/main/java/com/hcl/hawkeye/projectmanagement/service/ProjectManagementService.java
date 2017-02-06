package com.hcl.hawkeye.projectmanagement.service;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;

public interface ProjectManagementService {
	
	ProjectDetails getProjectDetails(int projectID);
	
	DashBoardDetails getDashBoardInfo();

}
