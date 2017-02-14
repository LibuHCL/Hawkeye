package com.hcl.hawkeye.projectdashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<Integer> projIdList = new ArrayList<Integer>();
		for(Project costProj :listOfProjects){
			projIdList.add(costProj.getProjectId());
		}
		Map<Integer, Integer> projCostMap = projCostService.getProjectCostForProjects(projIdList);
		Map<Integer, String> projManagerMap = resourceService.getProjectCostForProjects(projIdList);
		String pmName= resourceService.getProgramManager(programId);
		
		for(Project proj1 :listOfProjects){
			DashBoardProjectslist projList =new DashBoardProjectslist();
			int schedule =projMgmtService.getVelocityOfProject(proj1.getProjectId());
			logger.info("Project Id: {}",proj1);
			projList.setId(proj1.getProjectId());
			projList.setName(proj1.getProjName());
			projList.setStartdate(proj1.getCreationDate());
			projList.setEnddate(proj1.getEndDate());
			projList.setRisks(0);
			projList.setCost(projCostMap.get(proj1.getProjectId()) != null ? projCostMap.get(proj1.getProjectId()) : HawkEyeConstants.GREEN);
			projList.setSchedule(schedule);
			//projList.setQuality(codeQService.getCodeQualityRAGStatus());
			projList.setQuality(schedule);
			projList.setTechmanager(projManagerMap.get(proj1.getProjectId()));
			projList.setProgrammanager(pmName);
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
