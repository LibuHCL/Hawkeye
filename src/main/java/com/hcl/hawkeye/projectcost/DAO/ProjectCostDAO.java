package com.hcl.hawkeye.projectcost.DAO;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;

public interface ProjectCostDAO {

	ProjectCostDetails addProjectCostDetails(ProjectCostDetails cost);

	List<ProjectCostDetails> getAllProjectCost();

	ProjectCostDetails getProjectCost(int projectID);

	PortfolioDashboard getAllPortfolioDetails();
}
