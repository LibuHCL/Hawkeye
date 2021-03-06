package com.hcl.hawkeye.resourcemanagement.service;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.resourcemanagement.DO.ProgramResourceCount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.resourcemanagement.DO.Resource;

public interface ResourceManagementService {
	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
	HashMap<String,Long> getResourcesCountByProject(int projectId);
	List<ProgramResourceCount> getResourcesCountByProgram(int programId);
	HashMap<Integer, BigDecimal> getResourceAttritionByQuarter(String attritionYear);
	Double getResourcesPercentByPortfolio(int portfolioId);
	
	Graph getOffshorePerQtPerProject(int projectId);
	String getProgramManager(Integer programId);
	String getProjectManager(Integer projectId);
	Map<Integer, String> getProjectCostForProjects(List<Integer> projIdList);
}
