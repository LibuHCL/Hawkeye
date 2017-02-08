package com.hcl.hawkeye.resourcemanagement.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.resourcemanagement.DO.Resource;
import com.hcl.hawkeye.resourcemanagement.service.ResourceManagementService;

@RestController
public class ResourceManagementController {
private static final Logger logger = LoggerFactory.getLogger(ResourceManagementController.class);
	
	@Autowired
	public ResourceManagementService resourceManagementService;
	
	
	@RequestMapping(value = "/createResource", method = RequestMethod.POST,consumes = "application/json")
	public void createResource(@RequestBody Resource resource) {
		resourceManagementService.createResource(resource);
	    
	}
	
	@RequestMapping(value="/getResourceData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Resource getResourceData() {
		logger.info("Requested to get the resource information");
		Resource resource = null;
		resource = resourceManagementService.getResourceData();
		return resource;
	}
	
	@RequestMapping(value="/getResourcesCount/{projectId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String,Long> getResourcesCountByProject(@PathVariable("projectId") String projectId) {
		logger.info("Requested to get the resources count based on project");
		HashMap<String,Long> response = resourceManagementService.getResourcesCountByProject(projectId);
		return response;
	}
	
	
}
