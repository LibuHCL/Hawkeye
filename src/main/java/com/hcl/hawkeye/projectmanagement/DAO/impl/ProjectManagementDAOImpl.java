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
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.PriorityOfIssue;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectIssues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.StoryPoint;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

/**
 * 
 * @author Haribabu
 *
 */
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
		SprintDetailsOfProject sProject = new SprintDetailsOfProject();
		try {
			int countOfSprints = 0;
			ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
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
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return sProject;
	}
	
	private ProjectDetails getProjectDetailsOfSprints(int projectId) {
		logger.info("Request to get project details with project id: {}", projectId);
		Locale locale=new Locale("en", "IN");
		ProjectDetails pDetails = null;
		try {
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			String projectInfo = jrCall.callRestAPI(url+projectId+"/sprint/");
			gson = new Gson();
			pDetails = gson.fromJson(projectInfo, ProjectDetails.class);		
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return pDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		logger.info("Request to get dashboard info");
		Locale locale=new Locale("en", "IN");
		DashBoardDetails dashBInfo = null;
		try {
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			String dashBoardInfo = jrCall.callRestAPI(url);
			gson = new Gson();
			dashBInfo = gson.fromJson(dashBoardInfo, DashBoardDetails.class);
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return dashBInfo;
	}

	@Override
	public Velocityinfo getVelocityOfProject(int projectId) {
		logger.info("Request to get velocity of project info");
		Velocityinfo velInfo;
		Locale locale=new Locale("en", "IN");
		try {
			
			String url = messageSource.getMessage("jira.agile.rest.api.velocity.url", new Object[]{}, locale);
			String velocityInfo = jrCall.callRestAPI(url+projectId);
			gson = new Gson();
			velInfo = gson.fromJson(velocityInfo, Velocityinfo.class);
			Type type = new TypeToken<Map<String, Object>>() {}.getType();
			Map<String, Object> velocityData = new Gson().fromJson(velocityInfo, type);
			if(velocityData != null){
				for (String key : velocityData.keySet()) {
					if ("velocityStatEntries".equals(key.trim().toString())) {
						Map<String, Map<String, Map<String, Double>>> velData  = (Map<String, Map<String, Map<String, Double>>>) velocityData.get(key);
						velInfo.setVelocityStatEntries(velData);
					}
				}
			}
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.velocity", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		
		return velInfo;
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		logger.info("Request to get issues of projects");
		Map<String, Integer> issuesMap = new TreeMap<String, Integer>();
		Locale locale=new Locale("en", "IN");
		try {
			ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
			List<ProjectValues> pValues = pDetails.getValues();
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			for (ProjectValues projectValues : pValues) {
				int count = 0;
				String issuesInfo = jrCall.callRestAPI(url+projectId+"/sprint/"+projectValues.getId()+"/issue?fields=issuetype");
				ProjectIssues pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
				
				for (Issues issue : pIssues.getIssues()) {
					if (null != issue.getFields().getIssuetype() && issueType.equals(issue.getFields().getIssuetype().getName()) && !"UAT".equals(projectValues.getName())) {
						count++;
					}
				}
				
				Integer issueCount = new Integer(count);
				if (!"UAT".equals(projectValues.getName())) {
					issuesMap.put(projectValues.getName(), issueCount);
				}
			}
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return issuesMap;
	}

	@Override
	public Map<String, Integer> getPriorityOfIssue(int projectId, String issuePriority) {
		logger.info("Request to get issues of projects");
		Map<String, Integer> priorityMap = new TreeMap<String, Integer>();
		Locale locale=new Locale("en", "IN");
		try {
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
				
				if (!"UAT".equals(projectValues.getName())) {
					priorityMap.put(projectValues.getName(), priorityIssues);
				}
				
			}
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return priorityMap;
	}

	@Override
	public DefectTypes getDefectTypesOfProject(int projectId) {
		logger.info("Request to get issues of projects");
		String issueType = "Defect";
		int invalidDefects = 0;
		int uatDefects = 0;
		DefectTypes dTypes = null;
		Locale locale=new Locale("en", "IN");
		try {
			ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
			List<ProjectValues> pValues = pDetails.getValues();
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			for (ProjectValues projectValues : pValues) {
				String issuesInfo = jrCall.callRestAPI(url+projectId+"/sprint/"+projectValues.getId()+"/issue?fields=issuetype");
				ProjectIssues pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
				
				for (Issues issue : pIssues.getIssues()) {
					if (null != issue.getFields().getIssuetype() && issueType.equals(issue.getFields().getIssuetype().getName()) && !"UAT".equals(projectValues.getName())) {
						invalidDefects++;
					}
					
					if (null != issue.getFields().getIssuetype() && issueType.equals(issue.getFields().getIssuetype().getName()) && "UAT".equals(projectValues.getName())) {
						uatDefects++;
					}
				}
			}
			dTypes = new DefectTypes();
			dTypes.setInternalDefects(invalidDefects);
			dTypes.setValidInternalDefects(invalidDefects);
			dTypes.setUatDefects(uatDefects);
			dTypes.setDefectLekage(uatDefects);
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return dTypes;
	}
	

}
