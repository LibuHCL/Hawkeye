package com.hcl.hawkeye.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	ProjectManagementDAO pmDAO;
	
	@Override
	public ProjectDetails getProjectDetails(int projectId) {
		ProjectDetails proDetails = pmDAO.getProjectDetails(projectId);
		return proDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		DashBoardDetails data = pmDAO.getDashBoardInfo();
		return data;
	}

}
