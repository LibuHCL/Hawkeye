package com.hcl.hawkeye.projectmanagement.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	ProjectManagementDAO pmDAO;
	
	@Override
	public SprintDetailsOfProject getProjectDetails(int projectId) {
		SprintDetailsOfProject proDetails = pmDAO.getProjectDetails(projectId);
		return proDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		DashBoardDetails dashBoardInfo = pmDAO.getDashBoardInfo();
		return dashBoardInfo;
	}

	@Override
	public Velocityinfo getVelocityOfProject(int projectId) {
		Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
		return vInfo;
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		Map<String, Integer> issues = pmDAO.getIssuesOfProject(projectId, issueType);
		return issues;
	}

}
