package com.hcl.hawkeye.projectmanagement.service;

import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;

public interface ProjectManagementService {
	
	SprintDetailsOfProject getProjectDetails(int projectID);
	
	DashBoardDetails getDashBoardInfo();
	
	int getVelocityOfProject(int projectId);
	
	Map<String, Integer> getIssuesOfProject(int projectId, String issueType);
	
	Integer getPriorityOfIssue(int projectId, String issuePriority);

}
