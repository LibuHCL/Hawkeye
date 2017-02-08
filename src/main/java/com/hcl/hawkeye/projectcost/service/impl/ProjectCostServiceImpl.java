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
	public Integer getProjectCostData(int projectID) {
		
		ProjectCostDetails costDetails = costDAO.getProjectCost(projectID);
		if(costDetails == null){
			return 0;
		}
		if(costDetails.getPlannedCost()== null || costDetails.getActualCost()== null){
			return 0;
			
		}
		return HawkEyeUtils.getRAGStatus((int) ((costDetails.getActualCost()/costDetails.getPlannedCost())*100));
		
	}

}
