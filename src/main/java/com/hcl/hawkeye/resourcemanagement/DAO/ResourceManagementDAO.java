package com.hcl.hawkeye.resourcemanagement.DAO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.resourcemanagement.DO.ProgramResourceCount;
import com.hcl.hawkeye.resourcemanagement.DO.Resource;

public interface ResourceManagementDAO {
	Resource getResourceData();
	int getResourcesCount(String roleName);
	void createResource(Resource resource);
	HashMap<String,Long> getResourcesCountByProject(int projectId);
	HashMap<Integer,BigDecimal> getResourceAttritionByQuarter(String attritionYear);
	List<ProgramResourceCount> getResourcesCountByProgram(int programId);
	Double getResourcesPercentByPortfolio(int portfolioId);
	Graph getOffshorePerQtPerProject(int projectId);
}
