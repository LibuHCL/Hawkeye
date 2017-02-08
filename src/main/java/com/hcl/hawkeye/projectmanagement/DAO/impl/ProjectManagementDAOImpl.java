package com.hcl.hawkeye.projectmanagement.DAO.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hcl.hawkeye.Exceptions.NoProjectDetailsException;
import com.hcl.hawkeye.common.JiraRestCallAPI;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.PriorityOfIssue;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectIssues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.StoryPoint;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

@Repository
public class ProjectManagementDAOImpl implements ProjectManagementDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementDAOImpl.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	JiraRestCallAPI jrCall;
	
	Gson gson = null;

	@Override
	public SprintDetailsOfProject getProjectDetails(int projectId) {
		logger.info("Request to get project details with project id: {}", projectId);
		ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
		int countOfSprints = 0;
		SprintDetailsOfProject sProject = new SprintDetailsOfProject();
		if (null != pDetails && !pDetails.getValues().isEmpty()) {
			for (ProjectValues pValue : pDetails.getValues()) {
				if ("active".equals(pValue.getState())) {
					sProject.setCurrentSprint(pValue.getName());
					countOfSprints++;
				} else {
					countOfSprints++;
				}
			}
		}
		sProject.setNoOfSprintPerProject(countOfSprints);
		
		return sProject;
	}
	
	private ProjectDetails getProjectDetailsOfSprints(int projectId) {
		logger.info("Request to get project details with project id: {}", projectId);
		Locale locale=new Locale("en", "IN");
		String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
		String projectInfo = jrCall.callRestAPI(url+projectId+"/sprint/");
		gson = new Gson();
		ProjectDetails pDetails = gson.fromJson(projectInfo, ProjectDetails.class);		
		return pDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		logger.info("Request to get dashboard info");
		Locale locale=new Locale("en", "IN");
		String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
		String dashBoardInfo = jrCall.callRestAPI(url);
		gson = new Gson();
		DashBoardDetails dashBInfo = gson.fromJson(dashBoardInfo, DashBoardDetails.class);
		return dashBInfo;
	}

	@Override
	public Velocityinfo getVelocityOfProject(int projectId) {
		logger.info("Request to get velocity of project info");
		Velocityinfo velInfo;
		try {
			Locale locale=new Locale("en", "IN");
			String url = messageSource.getMessage("jira.agile.rest.api.velocity.url", new Object[]{}, locale);
			String velocityInfo = jrCall.callRestAPI(url+projectId);
			gson = new Gson();
			velInfo = gson.fromJson(velocityInfo, Velocityinfo.class);
			Type type = new TypeToken<Map<String, Object>>() {}.getType();
			Map<String, Object> velocityData = new Gson().fromJson(velocityInfo, type);
			
			for (String key : velocityData.keySet()) {
				if ("velocityStatEntries".equals(key.trim().toString())) {
					Map<String, Map<String,StoryPoint>> velData  = (Map<String, Map<String, StoryPoint>>) velocityData.get(key);
					velInfo.setVelocityStatEntries(velData);
				}
			}
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.velocity.", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		
		return velInfo;
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		logger.info("Request to get issues of projects");
		Locale locale=new Locale("en", "IN");
		ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
		List<ProjectValues> pValues = pDetails.getValues();
		Map<String, Integer> issuesMap = new TreeMap<String, Integer>();
		String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
		for (ProjectValues projectValues : pValues) {
			int count = 0;
			int criticalIssues = 0;
			int highPriorityIssues = 0;
			String issuesInfo = jrCall.callRestAPI(url+projectId+"/sprint/"+projectValues.getId()+"/issue?fields=issuetype");
			ProjectIssues pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
			
			for (Issues issue : pIssues.getIssues()) {
				if (null != issue.getFields().getIssuetype() && issueType.equals(issue.getFields().getIssuetype().getName()) && !"UAT".equals(projectValues.getName())) {
					count++;
				}
				
				if (null != issue.getFields().getPriorityIssues() && !"UAT".equals(projectValues.getName()) && "High".equals(issue.getFields().getPriorityIssues().getName())) {
					
				}
			}
			
			Integer issueCount = new Integer(count);
			if (!"UAT".equals(projectValues.getName())) {
				issuesMap.put(projectValues.getName(), issueCount);
			}
		}
		return issuesMap;
	}

	@Override
	public Integer getPriorityOfIssue(int projectId, String issuePriority) {
		logger.info("Request to get issues of projects");
		Locale locale=new Locale("en", "IN");
		ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
		List<ProjectValues> pValues = pDetails.getValues();
		String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
		int priorityIssues = 0;
		for (ProjectValues projectValues : pValues) {
			String issuesInfo = jrCall.callRestAPI(url+projectId+"/sprint/"+projectValues.getId()+"/issue?fields=priority");
			ProjectIssues pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
			
			for (Issues issue : pIssues.getIssues()) {
				if (null != issue.getFields().getPriorityIssues() && !"UAT".equals(projectValues.getName()) && issuePriority.equals(issue.getFields().getPriorityIssues().getName())) {
					priorityIssues++;
				}
			}
		}
		return priorityIssues;
	}
	

}
