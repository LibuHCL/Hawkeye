package com.hcl.hawkeye.projectmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

@RestController
public class ProjectManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementController.class);
	
	@Autowired
	ProjectManagementService pmService;
	
	@RequestMapping(value="/getProject/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectDetails getProjectData(@PathVariable("id") int projectId) {
		logger.info("Rquesting api to get the project details with ID: {}", projectId);
		ProjectDetails proDetails = pmService.getProjectDetails(projectId);
		return proDetails;
	}
	
	@RequestMapping(value="/getDashboard", method = RequestMethod.GET)
	@ResponseBody
	public DashBoardDetails getDashBoardData() {
		logger.info("Rquesting api to get the dasboard information");
		DashBoardDetails dashBInfo = pmService.getDashBoardInfo();
		return dashBInfo;
	}
	
	@RequestMapping(value="/getVelocity/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Velocityinfo getVelocityOfProject(@PathVariable("id") int projectId) {
		logger.info("Rquesting api to get the dasboard information");
		Velocityinfo dashBInfo = pmService.getVelocityOfProject(projectId);
		return dashBInfo;
	}

}
