package com.hcl.hawkeye.batch.jira.core;

import java.util.List;

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

import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;

public class JiraSprintReader implements ItemReader<DashBoardValues>{

	private static final Logger logger = LoggerFactory.getLogger(JiraSprintReader.class);
	private List<DashBoardValues> dashBoardVals;
	DashBoardValues db = null;
	@Override
	public DashBoardValues read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		for (DashBoardValues dashBoardValues : dashBoardVals) {
			if (null == db) {
				db = dashBoardValues;
				return db;
			} else {
				return null;
			}
		}
		return null;
	}

	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.dashBoardVals = (List<DashBoardValues>) jobContext.get("someKey");
    }
}
