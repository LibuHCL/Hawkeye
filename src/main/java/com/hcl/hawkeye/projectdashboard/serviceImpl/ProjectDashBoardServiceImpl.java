package com.hcl.hawkeye.projectdashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.codequality.service.CodeQualityService;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.projectdashboard.DO.DashBoardProjectslist;
import com.hcl.hawkeye.projectdashboard.DO.DashBoardResource;
import com.hcl.hawkeye.projectdashboard.DO.ProjectDashBoard;
import com.hcl.hawkeye.projectdashboard.DO.Projects;
import com.hcl.hawkeye.projectdashboard.service.ProjectDashBoardService;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;
import com.hcl.hawkeye.resourcemanagement.service.ResourceManagementService;
import com.hcl.hawkeye.utils.HawkEyeConstants;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Service
public class ProjectDashBoardServiceImpl implements ProjectDashBoardService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDashBoardServiceImpl.class);
	
	@Autowired
	ProgramManagementService progMgmtService;
	
	@Autowired
	ProjectManagementService projMgmtService;
	
	@Autowired
	ProjectCostService projCostService;
	
	@Autowired
	CodeQualityService codeQService;
	
	@Autowired
	ResourceManagementService resourceService;

	@Override
	public ProjectDashBoard getProjectDashBoard(Integer programId) {
		logger.info("Inside getProjectDashBoard method in ProjectDashBoardServiceImpl for program Id : {}",programId);
		List <Project> listOfProjects = progMgmtService.getProjectsPerProgramId(programId);
		ProjectDashBoard pd= new ProjectDashBoard();
		Projects project = new Projects();
		List<DashBoardProjectslist> listDBProjects  = new ArrayList<DashBoardProjectslist>();
		List<DashBoardResource>  res = new ArrayList<DashBoardResource>();
		
		for(Project proj1 :listOfProjects){
			DashBoardProjectslist projList =new DashBoardProjectslist();
			logger.info("Project Id: {}",proj1);
			
			//resMgmtService.getResourcesCountByProject(Integer.toString(proj1.getProjectId()));
			projList.setId(proj1.getProjectId());
			projList.setName(proj1.getProjName());
			projList.setStartdate(proj1.getCreationDate());
			projList.setEnddate(proj1.getEndDate());
			projList.setRisks(0);
			projList.setCost(getCostData(proj1.getProjectId()));
			projList.setSchedule(projMgmtService.getVelocityOfProject(proj1.getProjectId()));
			projList.setQuality(codeQService.getCodeQualityRAGStatus());
			projList.setTechmanager(resourceService.getProjectManager(proj1.getProjectId()));
			projList.setProgrammanager(resourceService.getProgramManager(programId));
			projList.setCurrentsprint(1);
			projList.setTotalsprinits(2);
			projList.setResource(res);
			listDBProjects.add(projList);	
		}

		project.setProjectslist(listDBProjects);
		project.setRunnningprojects(listOfProjects.size());
		pd.setProjects(project);		
		return pd;
	}
	
	
	public int getCostData(int projectId){
		logger.info("getCostData: {}",projectId);
		ProjectCostDetails costDetails = projCostService.getProjectCostData(projectId);
		if(costDetails == null){
			return HawkEyeConstants.GREEN;
		}
		if(costDetails.getPlannedCost()== null || costDetails.getActualCost()== null){
			return HawkEyeConstants.GREEN;
			
		}
		return HawkEyeUtils.getRAGStatus((int) ((costDetails.getActualCost()/costDetails.getPlannedCost())*100));
	}

}
