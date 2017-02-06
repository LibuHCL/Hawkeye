package com.hcl.hawkeye.resourcemanagement.service;

import com.hcl.hawkeye.portfolio.DO.Resource;

public interface ResourceManagementService {

	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
}
