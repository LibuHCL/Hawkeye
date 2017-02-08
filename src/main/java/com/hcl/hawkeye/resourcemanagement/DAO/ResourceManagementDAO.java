package com.hcl.hawkeye.resourcemanagement.DAO;

import java.util.HashMap;

import com.hcl.hawkeye.resourcemanagement.DO.Resource;

public interface ResourceManagementDAO {

	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
	HashMap<String,Long> getResourcesCountByProject(String projectId);
	HashMap<String,Long> getResourceAttritionByQuarter(String attritionYear);
	void getResourcesCountByProgram(String programId);
	
}
