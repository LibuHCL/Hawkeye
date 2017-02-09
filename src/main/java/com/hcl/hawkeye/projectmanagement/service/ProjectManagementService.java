package com.hcl.hawkeye.projectmanagement.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;

public interface ProjectManagementService {
	
	SprintDetailsOfProject getProjectDetails(int projectID);
	
	DashBoardDetails getDashBoardInfo();
	
	int getVelocityOfProject(int projectId);
	
	List<VelocityOfProject> getVelocityOfSprint(int ProjectId);
	
	Map<String, Integer> getIssuesOfProject(int projectId, String issueType);
	
	Map<String, Integer> getPriorityOfIssue(int projectId, String issuePriority);
	
	DefectTypes getDefectTypesOfProject(int project);

}
