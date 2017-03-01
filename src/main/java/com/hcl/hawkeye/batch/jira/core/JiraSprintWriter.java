package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public class JiraSprintWriter implements ItemWriter<List<ProjectValues>>{
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	private StepExecution stepExecution;

	private static final Logger logger = LoggerFactory.getLogger(JiraSprintWriter.class);
	@Override
	public void write (List<? extends List<ProjectValues>> sprintValuesList ) throws Exception {
		logger.info("FirstOne : {}");
		List<ProjectValues> sprintsList = new ArrayList<ProjectValues>();
		if(null != sprintValuesList) {
			for(List<ProjectValues> sprintValue : sprintValuesList){
				for (ProjectValues sprint : sprintValue) {
					sprintsList.add(sprint);
				}
			}
		}
		boolean status = jbDAO.insertSprinttDetails(sprintsList);
		if (status) {
			logger.info("Hooooo Yaaa Success !!!!");
			ExecutionContext stepContext = this.stepExecution.getExecutionContext();
			stepContext.put("someKey", sprintsList);
		} else {
			logger.error("You kicked out -- check your back");
		}
	}
	
	
	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
