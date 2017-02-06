package com.hcl.hawkeye.projectmanagement.DAO.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.hcl.hawkeye.Exceptions.NoProjectDetailsException;
import com.hcl.hawkeye.common.JiraRestCallAPI;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;

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
		String url = messageSource.getMessage("jira.agile.rest.api.project", new Object[]{}, locale);
		String projectInfo = jrCall.callRestAPI(url+projectId+"/sprint/");
		gson = new Gson();
		ProjectDetails pDetails = gson.fromJson(projectInfo, ProjectDetails.class);
		return pDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		logger.info("Request to get dashboard info");
		Locale locale=new Locale("en", "IN");
		String url = messageSource.getMessage("jira.agile.rest.api.board", new Object[]{}, locale);
		String dashBoardInfo = jrCall.callRestAPI(url);
		gson = new Gson();
		DashBoardDetails dashBInfo = gson.fromJson(dashBoardInfo, DashBoardDetails.class);
		return dashBInfo;
	}

}
