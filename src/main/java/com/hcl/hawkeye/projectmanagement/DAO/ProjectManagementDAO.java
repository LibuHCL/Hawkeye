package com.hcl.hawkeye.projectmanagement.DAO;

import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

public interface ProjectManagementDAO {
	
	SprintDetailsOfProject getProjectDetails(int projectId);
	
	DashBoardDetails getDashBoardInfo();
	
	Velocityinfo getVelocityOfProject( int projectId);
	
	Map<String, Integer> getIssuesOfProject( int projectId, String issueType);
	
	Map<String, Integer> getPriorityOfIssue( int projectId, String issuePriority);

}
