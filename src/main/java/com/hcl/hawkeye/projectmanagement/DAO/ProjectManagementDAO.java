package com.hcl.hawkeye.projectmanagement.DAO;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

public interface ProjectManagementDAO {
	
	ProjectDetails getProjectDetails(int projectId);
	
	DashBoardDetails getDashBoardInfo();
	
	Velocityinfo getVelocityOfProject( int projectId);
	
	Map<String, Integer> getIssuesOfProject( int projectId, String issueType);

}
