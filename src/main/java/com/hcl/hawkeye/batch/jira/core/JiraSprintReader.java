package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.hcl.hawkeye.batch.jira.DO.Project;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public class JiraSprintReader implements ItemReader<List<ProjectValues>>{
	
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProjectManagementDAO pmDao;

	private static final Logger logger = LoggerFactory.getLogger(JiraSprintReader.class);
	
	private List<Project> projectList ;
	
	List<ProjectValues> sprintValuesList = new ArrayList<ProjectValues>();
	boolean val = true;
	@Override
	public List<ProjectValues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(val ){
			Locale locale=new Locale("en", "IN");
			List<ProjectValues> sprintsList = new ArrayList<>();
			for (Project proj : projectList) {
				String sprintUrl = proj.getJiraUrl()+messageSource.getMessage("jira.agile.rest.api.sprint", new Object[]{}, locale);
				//sprintsList =pmDao.getSprintDetails(sprintUrl);
				for(ProjectValues pvalue :pmDao.getSprintDetails(sprintUrl)){
					pvalue.setToolProjectId(proj.getToolProjectId());					
					sprintsList.add(pvalue);
				}
				if(null != sprintsList){
					sprintValuesList.addAll(sprintsList);	
				}
			}
			val= false;
			return sprintValuesList;
		} else {
			return null;
		}
	}


	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.projectList = (List<Project>) jobContext.get("projectDetails");
    }
}
