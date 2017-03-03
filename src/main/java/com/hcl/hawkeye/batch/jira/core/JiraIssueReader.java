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

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.batch.jira.DO.SprintIssues;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public class JiraIssueReader implements ItemReader<List<SprintIssues>>{

	private static final Logger logger = LoggerFactory.getLogger(JiraIssueReader.class);
	private List<ProjectValues> sprintVals;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ProjectManagementDAO pmDao;
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	List<SprintIssues> sIssueList = new ArrayList<>();
	boolean stepThrough = true;
	
	@Override
	public List<SprintIssues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Locale locale=new Locale("en", "IN");
		if (stepThrough) {
			for (ProjectValues projectValues : sprintVals) {
				String sprintUrl = projectValues.getSelf()+messageSource.getMessage("jira.agile.rest.api.issue", new Object[]{}, locale);
				List<Issues> sprintsList =pmDao.getIssueDetails(sprintUrl);
				logger.info("Number of Issues: {} per Sprint ID: {}", sprintsList.size(), projectValues.getId());
				if(null != sprintsList && !sprintsList.isEmpty()){
					for (Issues issues : sprintsList) {
						SprintIssues sIssue = new SprintIssues();
						sIssue = setUpIssues(issues, projectValues);
						sIssueList.add(sIssue);
					}
				}
			}
			stepThrough = false;
			return sIssueList;
		} else {
			stepThrough = true;
			sIssueList = new ArrayList<>();
			return null;
		}
	}
	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.sprintVals = (List<ProjectValues>) jobContext.get("sprintDetails");
    }
	
	private SprintIssues setUpIssues(Issues issues, ProjectValues sprintValue) throws java.text.ParseException {
		SprintIssues sIssue = new SprintIssues();
		if (null != issues && null != issues.getFields() && null != issues.getFields().getIssuetype()) {
			sIssue.setSprintId(sprintValue.getId());
			sIssue.setIssueId(issues.getId());
			sIssue.setIssueType(issues.getFields().getIssuetype().getName());
			sIssue.setIssueTypeId(issues.getFields().getIssuetype().getId());
			sIssue.setProjectId(sprintValue.getToolProjectId());
			sIssue.setToolProjectId(sprintValue.getOriginBoardId());
		}
		if (null != issues && null != issues.getFields() && null != issues.getFields().getPriorityIssues()) { 
			sIssue.setPriorityId(issues.getFields().getPriorityIssues().getId());
			sIssue.setPriorityName(issues.getFields().getPriorityIssues().getName());
		}
		
		if (null != issues && null != issues.getFields() && null != issues.getFields().getCustomfield_10002()) {
			sIssue.setStoryPoint(Double.parseDouble((issues.getFields().getCustomfield_10002())));
		}
		
		if (null != issues && null != issues.getFields() && null != issues.getFields().getCreated()) {
			sIssue.setIssueStartDate(jbDAO.getFormatDate(issues.getFields().getCreated()));
		}
		
		if (null != issues && null != issues.getFields() && null != issues.getFields().getResolutiondate()) {
			sIssue.setIssueEndDate(jbDAO.getFormatDate(issues.getFields().getResolutiondate()));
		}
		
		if (null != issues && null != issues.getFields() && null != issues.getFields().getStatus() && null != issues.getFields().getStatus().getStatusCategory() && null != issues.getFields().getStatus().getStatusCategory().getKey()) {
			sIssue.setIssueStatus(issues.getFields().getStatus().getStatusCategory().getKey());
		}
		
		return sIssue;
	}
}
