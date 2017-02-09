package com.hcl.hawkeye.resourcemanagement.service;
import com.hcl.hawkeye.resourcemanagement.DO.ProgramResourceCount;
import java.util.HashMap;
import java.util.List;

import com.hcl.hawkeye.resourcemanagement.DO.Resource;

public interface ResourceManagementService {

	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
	HashMap<String,Long> getResourcesCountByProject(int projectId);
	List<ProgramResourceCount> getResourcesCountByProgram(int programId);
	HashMap<String, Long> getResourceAttritionByQuarter(String attritionYear);
	Integer getResourcesPercentByPortfolio(int portfolioId);
}
