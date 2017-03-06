package com.hcl.hawkeye.projectmanagement.DAO.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hcl.hawkeye.Exceptions.NoProjectDetailsException;
import com.hcl.hawkeye.common.JiraRestCallAPI;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectIssues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

/**
 * 
 * @author Haribabu
 *
 */
@Repository
@PropertySource("classpath:ingkpi.properties")
public class ProjectManagementDAOImpl implements ProjectManagementDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementDAOImpl.class);
	
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	JiraRestCallAPI jrCall;
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Autowired
	Environment env;
	
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
	
	@Override
	public ProjectDetails getProjectDetailsOfSprints(int projectId) {
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
	public DashBoardDetails getDashBoard(int startIndex) {
		logger.info("Request to get dashboard info");
		Locale locale=new Locale("en", "IN");
		DashBoardDetails dashBInfo = null;
		try {
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			String dashBoardInfo = jrCall.callRestAPI(url+"?startAt="+startIndex);
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
			Map<String, Map<String, Map<String, Double>>> velData = new TreeMap<>();
			if(velocityData != null){
				for (String key : velocityData.keySet()) {
					if ("velocityStatEntries".equals(key.trim().toString())) {
						velData  = (Map<String, Map<String, Map<String, Double>>>) velocityData.get(key);
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
			gson = new Gson();
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
	public Map<String, Map<String, Integer>> getPriorityOfIssue(int projectId, String blockerType, String criticalType) {
		logger.info("Request to get issues of projects");
		Map<String, Map<String, Integer>> priorityMap = new TreeMap<>();
		Map<String, Integer> blockerTypeIssues = new TreeMap<>();
		Map<String, Integer> criticalTypeIssues = new TreeMap<>();
		Locale locale=new Locale("en", "IN");
		try {
			ProjectDetails pDetails = getProjectDetailsOfSprints(projectId);
			if (null != pDetails && null != pDetails.getValues()) {
				List<ProjectValues> pValues = pDetails.getValues();
				String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
				int blockerIssues = 0;
				int criticalIssues = 0;
				gson = new Gson();
				for (ProjectValues projectValues : pValues) {
					String issuesInfo = jrCall.callRestAPI(url+projectId+"/sprint/"+projectValues.getId()+"/issue?fields=issuetype,priority");
					ProjectIssues pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
					
					for (Issues issue : pIssues.getIssues()) {
						if (null != issue && null != issue.getFields() && !"UAT".equals(projectValues.getName()) && "Defect".equals(issue.getFields().getIssuetype().getName())) {
							if (null != issue.getFields().getPriorityIssues() && !"UAT".equals(projectValues.getName()) && blockerType.equals(issue.getFields().getPriorityIssues().getName())) {
								blockerIssues++;
							}
						}
						
						if (null != issue && null != issue.getFields() && !"UAT".equals(projectValues.getName()) && "Defect".equals(issue.getFields().getIssuetype().getName())) {
							if (null != issue.getFields().getPriorityIssues() && !"UAT".equals(projectValues.getName()) && criticalType.equals(issue.getFields().getPriorityIssues().getName())) {
								criticalIssues++;
							}
						}
					}
				
					if (!"UAT".equals(projectValues.getName())) {
						blockerTypeIssues.put(projectValues.getName(), blockerIssues);
						criticalTypeIssues.put(projectValues.getName(), criticalIssues);
					}
					
				}	
				priorityMap.put(blockerType, blockerTypeIssues);priorityMap.put(criticalType, criticalTypeIssues);
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
			if (null != pDetails && null != pDetails.getValues()) {
				List<ProjectValues> pValues = pDetails.getValues();
				String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
				gson = new Gson();
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
			}
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return dTypes;
	}

	@Override
	public ProjectIssues getKanbanProjectDetails(int projectId) {
		logger.info("Requesting to get the kanban project details for projectid: {}", projectId);
		Locale locale=new Locale("en", "IN");
		ProjectIssues pIssues = null;
		try {
			gson = new Gson();
			String url = messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale);
			String issuesInfo = jrCall.callRestAPI(url+projectId+"/issue?fields=status,created,resolutiondate");
			pIssues = gson.fromJson(issuesInfo, ProjectIssues.class);
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return pIssues;
	}

	@Override
	public DashBoardValues getDashBoard(String url) {
		logger.info("Request to get dashboard info");
		Locale locale=new Locale("en", "IN");
		DashBoardValues dashBInfo = null;
		try {
			String dashBoardInfo = jrCall.callRestAPI(url);
			gson = new Gson();
			dashBInfo = gson.fromJson(dashBoardInfo, DashBoardValues.class);
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return dashBInfo;
	}
	
	@Override
	public List<ProjectValues> getSprintDetails(String url) {
		logger.info("Request to get sprint details with project id: {}", url);
		List<ProjectValues> sprintValuesList = null;
		Locale locale=new Locale("en", "IN");
		ProjectDetails pDetails = null;
		try {			
			String projectInfo = jrCall.callRestAPI(url);
			gson = new Gson();
			pDetails = gson.fromJson(projectInfo, ProjectDetails.class);
			if(null != pDetails){
				sprintValuesList= pDetails.getValues();
			}
			
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return sprintValuesList;
	}
	
	@Override
	public List<Issues> getIssueDetails(String url) {
		logger.info("Request to get sprint details with project id: {}", url);
		List<Issues> sprintValuesList = null;
		Locale locale=new Locale("en", "IN");
		ProjectIssues pDetails = null;
		try {			
			String projectInfo = jrCall.callRestAPI(url);
			gson = new Gson();
			pDetails = gson.fromJson(projectInfo, ProjectIssues.class);
			if(null != pDetails){
				sprintValuesList= pDetails.getIssues();
			}
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.project", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return sprintValuesList;
	}
	
	
	@Override
	public Map<String, Map<String, Integer>> getBusinessContinuity(int projectId, String blockerType, String criticalType) {
		logger.info("to get issues from DB");
		Map<String, Map<String, Integer>> priorityMap = new TreeMap<>();
		Map<String, Integer> blockerTypeIssues = new TreeMap<>();
		Map<String, Integer> criticalTypeIssues = new TreeMap<>();
		Locale locale=new Locale("en", "IN");
		try {			
				String sql_getIssues ="SELECT SMM.SPRINTID,SMM.SPRINT_NAME,SIM.PRIORITY_NAME, COUNT(SIM.PRIORITY_ID) as COUNT "
						+ " FROM PROJECT_METRICS_MANAGEMENT PMM,SPRINT_METRCIS_MANAGEMENT SMM, SPRINT_ISSUEMETRICS_MANAGEMENT SIM "
						+ "WHERE PMM.PROJECTID=SMM.PROJECTID AND PMM.TOOL_PROJECT_ID =SMM.TOOL_PROJECT_ID AND SMM.TOOL_PROJECT_ID=SIM.TOOL_PROJECT_ID"
						+ " AND SMM.PROJECTID=SIM.PROJECTID AND SMM.SPRINTID =SIM.SPRINTID AND PMM.PROJECTID=? AND SMM.SPRINT_NAME != 'UAT' "
						+ "AND SIM.ISSUE_TYPE ='Defect' GROUP BY SIM.PRIORITY_NAME,SMM.SPRINT_NAME; ";
				List<Map<String, Object>> issueList = jdbctemplate.queryForList(sql_getIssues,new Object[] {projectId});
				for (Map<String, Object> row : issueList) {
					if((row.get("PRIORITY_NAME").toString()).equalsIgnoreCase(env.getProperty("project.priority.blocker"))){
						blockerTypeIssues.put(row.get("SPRINT_NAME").toString(), Integer.parseInt(row.get("COUNT").toString()));
					}else if((row.get("PRIORITY_NAME").toString()).equalsIgnoreCase("project.priority.critical")){
						criticalTypeIssues.put(row.get("SPRINT_NAME").toString(),  Integer.parseInt(row.get("COUNT").toString()));	
					}
				}
			
			priorityMap.put(blockerType, blockerTypeIssues);
			priorityMap.put(criticalType, criticalTypeIssues);
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		return priorityMap;

		
	}

	@Override
	public List<VelocityOfProject> getProductivityOfProject(int projectId) {
		logger.info("Request to get velocity of project info");
		Locale locale=new Locale("en", "IN");
		List<VelocityOfProject> velocityList = new ArrayList<VelocityOfProject>();
			String sql_getStoryPoints = "SELECT SMM.SPRINTID,SMM.SPRINT_STATUS,SMM.SPRINT_NAME,SIM.ISSUE_STATUS,SUM(SIM.STORY_POINT) AS COUNT "
					+ "FROM PROJECT_METRICS_MANAGEMENT PMM,SPRINT_METRCIS_MANAGEMENT SMM,SPRINT_ISSUEMETRICS_MANAGEMENT SIM "
					+ "WHERE PMM.PROJECTID=SMM.PROJECTID AND PMM.TOOL_PROJECT_ID =SMM.TOOL_PROJECT_ID AND SMM.TOOL_PROJECT_ID=SIM.TOOL_PROJECT_ID "
					+ "AND SMM.PROJECTID=SIM.PROJECTID AND SMM.SPRINTID =SIM.SPRINTID AND PMM.PROJECTID=?  GROUP BY SIM.ISSUE_STATUS,SMM.SPRINT_NAME "
					+ "ORDER BY SMM.SPRINT_NAME,SIM.ISSUE_STATUS DESC"; 
			
			try{
			List<Map<String, Object>> issueList = jdbctemplate.queryForList(sql_getStoryPoints,new Object[] {projectId});
			Map<String, VelocityOfProject> sprintVelocityMap = new HashMap<String, VelocityOfProject>();
			Double completedValue = null ;
			for (Map<String, Object> row : issueList) {				
				VelocityOfProject velocityOfProject = new VelocityOfProject();
				velocityOfProject.setSprintId(Integer.parseInt(row.get("SPRINTID").toString()));
				velocityOfProject.setSprintName(row.get("SPRINT_NAME").toString());
				velocityOfProject.setSprintState(row.get("SPRINT_STATUS").toString());
				if(env.getProperty("story.point.new").equalsIgnoreCase(row.get("ISSUE_STATUS").toString())){
					completedValue = Double.valueOf(row.get("COUNT").toString());
					velocityOfProject.setCompletedValue(completedValue);
					sprintVelocityMap.put(velocityOfProject.getSprintName(), velocityOfProject);
				}
				if(env.getProperty("story.point.done").equalsIgnoreCase(row.get("ISSUE_STATUS").toString())){			
					sprintVelocityMap.get(velocityOfProject.getSprintName()).setEstimatedValue(completedValue+Double.valueOf(row.get("COUNT").toString()));
					completedValue=0.0;
					
				}	
											
			}				 
			velocityList.addAll(sprintVelocityMap.values());
					
		} catch (Exception e) {
			String errorMsg=messageSource.getMessage("error.get.velocity", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		
		return velocityList;
	}
	
	@Override
	public DefectTypes getUatissues(int projectId) {
		logger.info("Request to  getUatissues projects");
		int invalidDefects = 0;
		int uatDefects = 0;
		DefectTypes dTypes = null;
		Locale locale=new Locale("en", "IN");
		String sql_getStoryPoints = "SELECT SMM.SPRINTID,SMM.SPRINT_NAME,SIM.ISSUE_TYPE,COUNT(SIM.ISSUE_ID) AS COUNT FROM PROJECT_METRICS_MANAGEMENT PMM,SPRINT_METRCIS_MANAGEMENT SMM, SPRINT_ISSUEMETRICS_MANAGEMENT SIM  "
				+ "WHERE PMM.PROJECTID=SMM.PROJECTID AND PMM.TOOL_PROJECT_ID =SMM.TOOL_PROJECT_ID AND SMM.TOOL_PROJECT_ID=SIM.TOOL_PROJECT_ID AND SMM.PROJECTID=SIM.PROJECTID"
				+ " AND SMM.SPRINTID =SIM.SPRINTID AND PMM.PROJECTID=? AND SIM.ISSUE_TYPE ='Defect' GROUP BY SMM.SPRINT_NAME"; 
		
		try{
				List<Map<String, Object>> defectList = jdbctemplate.queryForList(sql_getStoryPoints,new Object[] {projectId});
		
				for (Map<String, Object> row : defectList) {	
					if (null != row.get("SPRINT_NAME").toString()) { 
						
						if(!"UAT".equals(row.get("SPRINT_NAME").toString()))
							invalidDefects= invalidDefects+ Integer.parseInt(row.get("COUNT").toString());
						
						if ( "UAT".equals(row.get("SPRINT_NAME").toString())) 
							uatDefects = uatDefects + Integer.parseInt(row.get("COUNT").toString());
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
