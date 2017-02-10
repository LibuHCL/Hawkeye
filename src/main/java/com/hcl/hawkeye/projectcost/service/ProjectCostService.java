package com.hcl.hawkeye.projectcost.service;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;

public interface ProjectCostService {

	ProjectCostDetails addProjectCostDetails(ProjectCostDetails cost);
	List<ProjectCostDetails> getAllProjectCostData();
	ProjectCostDetails getProjectCostData(int projectID);
	PortfolioDashboard getAllPortfolioDetails();
}
