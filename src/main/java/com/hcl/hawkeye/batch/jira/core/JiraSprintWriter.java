package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public class JiraSprintWriter implements ItemWriter<List<ProjectValues>>{
	private static final Logger logger = LoggerFactory.getLogger(JiraSprintWriter.class);
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	private StepExecution stepExecution;
	List<ProjectValues> sprintsList = new ArrayList<ProjectValues>();
	
	@Override
	public void write (List<? extends List<ProjectValues>> sprintValuesList ) throws Exception {
		if(null != sprintValuesList && ! sprintValuesList.isEmpty()) {
			for(List<ProjectValues> sprintValue : sprintValuesList){
				for (ProjectValues sprint : sprintValue) {
					sprintsList.add(sprint);
				}
			}

			boolean status = jbDAO.insertSprinttDetails(sprintsList);
			if (status) {
				logger.info("Hooooo Yaaa Success in JiraSprintWriter!!!!");
			} 
		}
	}
	
	@AfterStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		stepContext.put("sprintDetails", sprintsList);
    }

}
