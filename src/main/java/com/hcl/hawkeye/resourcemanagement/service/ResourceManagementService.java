package com.hcl.hawkeye.resourcemanagement.service;

import java.util.HashMap;

import com.hcl.hawkeye.resourcemanagement.DO.Resource;

public interface ResourceManagementService {

	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
	HashMap<String,Long> getResourcesCountByProject(String projectId);
}
