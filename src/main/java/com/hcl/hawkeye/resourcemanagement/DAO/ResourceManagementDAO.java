package com.hcl.hawkeye.resourcemanagement.DAO;

import com.hcl.hawkeye.portfolio.DO.Resource;

public interface ResourceManagementDAO {

	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
}
