package com.hcl.hawkeye.projectcost.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.DO.PortfolioColl;
import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.portfolio.DO.PortfolioDate;
import com.hcl.hawkeye.portfolio.DO.PortfolioInfo;
import com.hcl.hawkeye.portfolio.DO.Quarter;
import com.hcl.hawkeye.projectcost.DAO.ProjectCostDAO;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.utils.HawkEyeConstants;

@Service
public class ProjectCostServiceImpl implements ProjectCostService {

	@Autowired
	ProjectCostDAO costDAO;

	@Override
	public ProjectCostDetails addProjectCostDetails(ProjectCostDetails cost) {
		ProjectCostDetails projectCostDetails = costDAO.addProjectCostDetails(cost);
		return projectCostDetails;
	}

	@Override
	public List<ProjectCostDetails> getAllProjectCostData() {
		List<ProjectCostDetails> projectCostDetails = costDAO.getAllProjectCost();
		return projectCostDetails;
	}

	@Override
	public ProjectCostDetails getProjectCostData(int projectID) {

		return costDAO.getProjectCost(projectID);

	}

	@Override
	public List<PortfolioInfo> getAllPortfolioDetails() {
		List<PortfolioInfo> portfoliosList = costDAO.getAllPortfolioDetails();
		return portfoliosList;
	}

}
