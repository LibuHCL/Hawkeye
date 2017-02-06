package com.hcl.hawkeye.projectmanagement.DAO.impl;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;

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
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
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
	public ProjectDetails getProjectDetails(int projectId) {
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

}
