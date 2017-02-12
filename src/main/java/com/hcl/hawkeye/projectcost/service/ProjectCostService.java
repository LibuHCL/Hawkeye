package com.hcl.hawkeye.projectcost.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.portfolio.DO.PortfolioInfo;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;

public interface ProjectCostService {

	ProjectCostDetails addProjectCostDetails(ProjectCostDetails cost);
	List<ProjectCostDetails> getAllProjectCostData();
	ProjectCostDetails getProjectCostData(int projectID);
	List<PortfolioInfo> getAllPortfolioDetails();
	Map<Integer, Integer> getProjectCostForProjects(List<Integer> projIdList);
}
