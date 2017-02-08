package com.hcl.hawkeye.projectcost.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.projectmanagement.controller.ProjectManagementController;

@RestController
public class ProjectCostController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementController.class);
	
	@Autowired
	public ProjectCostService projectCostService;
	
	@RequestMapping(value="/addProjectCost", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectCostDetails> addProjectCostData(@RequestBody ProjectCostDetails cost){
		logger.info("Requesting API to add project cost.");
		ProjectCostDetails projectCost = null;
		if(cost != null){
			cost.setCaptureDate(new Timestamp(System.currentTimeMillis()));
			projectCost = projectCostService.addProjectCostDetails(cost);
		}
		return new ResponseEntity<ProjectCostDetails>(projectCost, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllProjectCost", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List> getAllProjectCostData(){
		logger.info("Requesting API to get all project cost.");
		List<ProjectCostDetails> allProjectCostDetails = projectCostService.getAllProjectCostData();
		return new ResponseEntity<List>(allProjectCostDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProjectCost/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getProjectCostData(@PathVariable("id") int projectID){
		logger.info("Requesting API to get project cost with ID: " + projectID);
		Integer cost = null;
		//ProjectCostDetails projectCost = null;
		if(projectID != 0){
			 cost = projectCostService.getProjectCostData(projectID);
		}
		logger.info("Cost::{}",cost);
	}
}
