package com.hcl.hawkeye.batch.jira.core;

import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.hcl.hawkeye.batch.jira.DO.Project;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public class JiraIssueReader implements ItemReader<List<ProjectValues>>{

	private List<Project> dashBoardVals;

	@Override
	public List<ProjectValues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		return null;
	}

	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.dashBoardVals = (List<Project>) jobContext.get("sprintDetails");
    }
}
