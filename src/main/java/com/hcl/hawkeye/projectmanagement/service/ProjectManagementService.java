package com.hcl.hawkeye.projectmanagement.service;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

public interface ProjectManagementService {
	
	ProjectDetails getProjectDetails(int projectID);
	
	DashBoardDetails getDashBoardInfo();
	
	Velocityinfo getVelocityOfProject(int projectId);

}
