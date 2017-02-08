package com.hcl.hawkeye.projectmanagement.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

@RestController
public class ProjectManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementController.class);
	
	@Autowired
	ProjectManagementService pmService;
	
	@RequestMapping(value="/getProject/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SprintDetailsOfProject getProjectData(@PathVariable("id") int projectId) {
		logger.info("Rquesting api to get the project details with ID: {}", projectId);
		SprintDetailsOfProject proDetails = pmService.getProjectDetails(projectId);
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
	public int getVelocityOfProject(@PathVariable("id") int projectId) {
		logger.info("Rquesting api to get the dasboard information");
		int velocityInfo = pmService.getVelocityOfProject(projectId);
		return velocityInfo;
	}
	
	@RequestMapping(value="/getIssues/project/{id}/issueType/{issueType}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getIssuesOfProject(@PathVariable("id") int projectId, @PathVariable("issueType") String issueType) {
		logger.info("Rquesting api to get the issues of project");
		Map<String, Integer> dashBInfo = pmService.getIssuesOfProject(projectId, issueType);
		return dashBInfo;
	}
	
	@RequestMapping(value="/getIssues/project/{id}/issuePriority/{issuePriority}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getPriorityOfIssues(@PathVariable("id") int projectId, @PathVariable("issuePriority") String issuePriority) {
		logger.info("Rquesting api to get the issues of project");
		Integer priorityIssues = pmService.getPriorityOfIssue(projectId, issuePriority);
		return priorityIssues;
	}


}
