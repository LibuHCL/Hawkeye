package com.hcl.hawkeye.resourcemanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.portfolio.DO.Resource;
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
	
	@RequestMapping(value="/getResourcesCount/{role}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public int getResourcesCount(@PathVariable("role") String role) {
		logger.info("Requested to get the resources count");
		int resource = 0;
		resource = resourceManagementService.getResourcesCount(role);
		return resource;
	}
	
	@RequestMapping(value="/getResourceData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Resource getResourceData() {
		logger.info("Requested to get the resource information");
		Resource resource = null;
		resource = resourceManagementService.getResourceData();
		return resource;
	}
	
	
	
	
}
