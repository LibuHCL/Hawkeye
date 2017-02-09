package com.hcl.hawkeye.projectcost.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.projectcost.DAO.ProjectCostDAO;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.utils.HawkEyeUtils;

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
	public List<ProjectCostDetails> getAllProjectCostData(){
		List<ProjectCostDetails> projectCostDetails = costDAO.getAllProjectCost();
		return projectCostDetails;
	}

	@Override
	public ProjectCostDetails getProjectCostData(int projectID) {
		
		return costDAO.getProjectCost(projectID);
		
		
	}

}
