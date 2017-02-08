package com.hcl.hawkeye.projectmanagement.service;

import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

public interface ProjectManagementService {
	
	SprintDetailsOfProject getProjectDetails(int projectID);
	
	DashBoardDetails getDashBoardInfo();
	
	Velocityinfo getVelocityOfProject(int projectId);
	
	Map<String, Integer> getIssuesOfProject(int projectId, String issueType);

}
