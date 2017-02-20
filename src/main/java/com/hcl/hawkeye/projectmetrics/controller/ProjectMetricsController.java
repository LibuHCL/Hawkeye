package com.hcl.hawkeye.projectmetrics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectmetrics.DO.ProjectDetails;
import com.hcl.hawkeye.projectmetrics.DO.ProjectMetrics;
import com.hcl.hawkeye.projectmetrics.service.ProjectMetricsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)	
public class ProjectMetricsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectMetricsController.class);
	
	@Autowired
	ProjectMetricsService projMetservice;
	
	@RequestMapping(value="/getProjMetData/{projectId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ProjectMetrics getProjMetricsData(@PathVariable("projectId") int projectId) {
		logger.info("getProjMetricsData tinside ProjectMetricsController: {}", projectId);
		ProjectMetrics projMet = null;
		projMet = projMetservice.getProjMetricsData(projectId);
		return projMet;
	}
	
	@RequestMapping(value="/getProjectByProgramId/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectDetails getProjectByProgramId(@PathVariable("programId") Integer programId) {
		logger.info("getProjectByProgramId inside ProjectMetricsController: {}", programId);
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails = projMetservice.getProjectByProgramId(programId);
		return projectDetails;
	}
}
