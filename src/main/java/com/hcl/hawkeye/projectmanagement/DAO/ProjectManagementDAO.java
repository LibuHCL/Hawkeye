package com.hcl.hawkeye.projectmanagement.DAO;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;

public interface ProjectManagementDAO {
	
	ProjectDetails getProjectDetails(int projectId);
	
	DashBoardDetails getDashBoardInfo();

}
