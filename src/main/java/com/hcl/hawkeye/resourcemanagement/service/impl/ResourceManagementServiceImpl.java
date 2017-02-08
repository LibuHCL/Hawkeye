package com.hcl.hawkeye.resourcemanagement.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Resource;
import com.hcl.hawkeye.resourcemanagement.DAO.ResourceManagementDAO;
import com.hcl.hawkeye.resourcemanagement.service.ResourceManagementService;



@Service
public class ResourceManagementServiceImpl implements ResourceManagementService {
	private static final Logger logger = LoggerFactory.getLogger(ResourceManagementServiceImpl.class);
	@Autowired
	ResourceManagementDAO resourceDAO;
	@Override
	public Resource getResourceData() {
		logger.info("Request in getResourcesData of ResourceServiceImpl");
		Resource resource = null;
		resource = resourceDAO.getResourceData();
		logger.info("Resource list size is");
		return resource;
	}
	@Override
	public int getResourcesCount(String roleName) {
		logger.info("Request in getResourcesCount of ResourceServiceImpl");
		int resourceCount = 0;
		resourceCount = resourceDAO.getResourcesCount(roleName);
		logger.info("Resources count is"+resourceCount);
		return resourceCount;
	}
	@Override
	public void createResource(Resource resource) {
		logger.info("Request in createResource of ResourceServiceImpl");
		resourceDAO.createResource(resource);
		logger.info("Resource created successfully");
	}
	@Override
	public HashMap<String,Long> getResourcesCountByProject(String projectId) {
		logger.info("Request in getResourcesCountByProject of ResourceServiceImpl");
		HashMap<String,Long> response = resourceDAO.getResourcesCountByProject(projectId);
		return response;
	}

}
