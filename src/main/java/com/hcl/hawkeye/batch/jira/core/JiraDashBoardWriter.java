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
import com.hcl.hawkeye.batch.jira.DO.Project;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;

public class JiraDashBoardWriter implements ItemWriter<List<DashBoardValues>> {

	private static final Logger logger = LoggerFactory.getLogger(JiraDashBoardWriter.class);

	//private StepExecution stepExecution;
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;

	private StepExecution stepExecution;
	
	List<Project> projList = new ArrayList<>();
	
	
	@Override
	public void write(List<? extends List<DashBoardValues>> details) throws Exception {
		logger.info("Requested to write the data coming from the Reader");
		if (null != details) {
			for (List<DashBoardValues> list : details) {
				for (DashBoardValues dashBoardValues : list) {
					Project pj = new Project();
					pj.setId(dashBoardValues.getId());
					pj.setName(dashBoardValues.getName());
					pj.setType(dashBoardValues.getType());
					pj.setJiraUrl(dashBoardValues.getSelf());
					projList.add(pj);
				}
			}
		}
		boolean status = jbDAO.insertProjectDetails(projList);
		if (status) {
			logger.info("Hooooo Yaaa Success !!!!");
		} 
	}

	@AfterStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		stepContext.put("projectDetails", projList);
    }
}
