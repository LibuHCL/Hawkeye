package com.hcl.hawkeye.projectdashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;
import com.hcl.hawkeye.projectdashboard.DO.ProjectDashBoard;
import com.hcl.hawkeye.projectdashboard.DO.Projects;
import com.hcl.hawkeye.projectdashboard.DO.Projectslist;
import com.hcl.hawkeye.projectdashboard.DO.Resource;
import com.hcl.hawkeye.projectdashboard.controller.ProjectDashBoardController;
import com.hcl.hawkeye.projectdashboard.service.ProjectDashBoardService;
@Service
public class ProjectDashBoardServiceImpl implements ProjectDashBoardService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDashBoardServiceImpl.class);
	
	@Autowired
	ProgramManagementService progMgmtService;
	
	

	@Override
	public ProjectDashBoard getProjectDashBoard(Project proj) {
		List <Project> listOfProjects = progMgmtService.getProjectsPerProgramId(proj.getProgId());
		ProjectDashBoard pd= new ProjectDashBoard();
		Projects project = new Projects();
		Projectslist [] projArray  = new Projectslist[listOfProjects.size()];
		Resource [] res = new Resource[3];
		for(int i =0; i<listOfProjects.size();i++){
			Projectslist projList =new Projectslist();
			Project proj1 =listOfProjects.get(i);
			logger.info("Project"+proj1);
			projList.setId(proj1.getProjectId());
			projList.setName(proj1.getProjName());
			projList.setStartdate(proj1.getCreationDate());
			projList.setEnddate(proj1.getEndDate());
			projList.setRisks(0);
			projList.setCost(0);
			projList.setSchedule(0);
			projList.setQuality(0);
			projList.setTechmanager("NULL");
			projList.setProgrammanager("NULL");
			projList.setCurrentsprint(1);
			projList.setTotalsprinits(2);
			projList.setResource(res);
			projArray[i]=projList;			
		}

		project.setProjectslist(projArray);
		project.setRunnningprojects(listOfProjects.size());
		pd.setProjects(project);
		
		return pd;
	}

}
