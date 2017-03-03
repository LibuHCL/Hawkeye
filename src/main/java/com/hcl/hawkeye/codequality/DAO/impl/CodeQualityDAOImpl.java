package com.hcl.hawkeye.codequality.DAO.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hcl.hawkeye.codequality.DAO.CodeQualityDAO;
import com.hcl.hawkeye.codequality.DO.Msr;
import com.hcl.hawkeye.codequality.DO.Resource;
import com.hcl.hawkeye.codequality.controller.CodeQualityController;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
@Configuration
@PropertySource("classpath:config.properties")
public class CodeQualityDAOImpl implements CodeQualityDAO {

	private static final Logger logger = LoggerFactory.getLogger(CodeQualityController.class);

	@Autowired
	Environment env;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Resource getSonarMetrics(String branchId) {

		Resource sonarResultResource = new Resource();
		String sonarUrl = env.getProperty("sonar.url");
		String sonarProjectName = env.getProperty("sonar.projectname");
		String sonarRestUrl = env.getProperty("sonar.restUrl");
		String sonarMetricsList = env.getProperty("sonar.metricList");
		String sonarResultFormat = env.getProperty("sonar.resultFormat");

		// Get the Static Analysis metrics from Sonar
		StringBuilder sonarMetricRestUrl = new StringBuilder(sonarUrl);
		sonarMetricRestUrl.append(sonarRestUrl).append(sonarProjectName).append(":").append(branchId)
				.append(sonarResultFormat).append(sonarMetricsList);

		logger.info("sonarMetricRestUrl: {}", sonarMetricRestUrl);

		RestTemplate restTemplate = new RestTemplate();
		Resource[] sonarResources = restTemplate.getForObject(sonarMetricRestUrl.toString(), Resource[].class);

		logger.debug("Resources: {}", sonarResources);

		if (sonarResources.length > 0)
			sonarResultResource = sonarResources[0];

		return sonarResultResource;

	}

	@Override
	public List<Resource> getAllSonarProjectDetails(String toolURL) {
		//TO-DO This should be done using a service
		String sonarRestUrl = env.getProperty("sonar.restUrl");
		String sonarMetricsList = env.getProperty("sonar.metricList");
		String sonarResultFormat = env.getProperty("sonar.resultFormat");

		// Get the Static Analysis metrics from Sonar
		StringBuilder sonarMetricRestUrl = new StringBuilder(toolURL);
		sonarMetricRestUrl.append(sonarRestUrl).append(sonarResultFormat).append(sonarMetricsList);

		logger.info("sonarMetricRestUrl: {}", sonarMetricRestUrl);

		RestTemplate restTemplate = new RestTemplate();
		Resource[] sonarResources = restTemplate.getForObject(sonarMetricRestUrl.toString(), Resource[].class);

		logger.debug("Resources: {}", sonarResources);

		List<Resource> resourceList = Arrays.asList(sonarResources);

		return resourceList;

	}

	@Override
	public void insertCodeQuality(Resource resource) {
		
		String fetchSprintNameQuery = " SELECT SPRINT_NAME FROM SPRINT_METRCIS_MANAGEMENT SPRINTMETRICS WHERE PROJECTID IN" +
										" (SELECT " +
										" PROJECT_ID " +
										" FROM " +
										" PROJECT_TOOL_MAPPING PROJTABMAP,  " +
										"  DEVOPS_SERVER_CONFIG DEVOPSCONFIG " +
										" WHERE " +
										" PROJTABMAP.TOOL_PROJECT_ID = ? " +
										"   AND PROJTABMAP.TOOL_ID = DEVOPSCONFIG.TOOLID " +
										" AND DEVOPSCONFIG.TOOL_NAME = 'JIRA') " +
										" AND (? BETWEEN SPRINTMETRICS.START_DATE AND SPRINTMETRICS.END_DATE); " ;
		
		List<Map<String, Object>> sprintNameList = jdbcTemplate.queryForList(fetchSprintNameQuery,
				new Object[] {resource.getId(), resource.getDate().substring(0, resource.getDate().indexOf("T")-1)  });
		String sprintName = null;
		for (Map<String, Object> row : sprintNameList) {
			sprintName = (String) row.get("SPRINT_NAME");
			break;
		}
		
		Integer toolId = 0;
		
		String fetchToolIdQuery =  "  SELECT  " +
				" PROJTABMAP.PROJECT_ID, DEVOPSCONFIG.TOOLID  " +
				"  FROM  " +
				"    PROJECT_TOOL_MAPPING PROJTABMAP,  " +
				"     DEVOPS_SERVER_CONFIG DEVOPSCONFIG  " +
				"  WHERE  " +
				"    PROJTABMAP.TOOL_PROJECT_ID = ?  " +
				"         AND PROJTABMAP.TOOL_ID = DEVOPSCONFIG.TOOLID  " +
				"        AND DEVOPSCONFIG.TOOL_NAME = 'SONAR';  " ;
		
		List<Map<String, Object>> toolIdList = jdbcTemplate.queryForList(fetchToolIdQuery,
				new Object[] {resource.getId()});
		
		for (Map<String, Object> row : sprintNameList) {
			toolId = (Integer) row.get("TOOLID");
			break;
		}
		
		String deleteCodeQualityQuery = "Delete from CODE_QUALITY where PROJECTID = ? and SPRINT = ?";
		
		int rows = jdbcTemplate.update(deleteCodeQualityQuery, new Object[] {resource.getId(), sprintName});
		
		String insertCodeQuality = "INSERT INTO CODE_QUALITY"
				+ " (PROJECTID,PROJECTKEY,SPRINT,TECHNICAL_DEBT,BLOCKER_ISSUES,"
				+ "CRITICAL_ISSUES,COMPLEXITY,COMMENTED_LINES,DUPLICATED_LINES_DENSITY,TOOL_ID ) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertCodeQuality,
				new Object[] { resource.getId(), resource.getKey(), sprintName, getValueForMetric("sqale_debt_ratio", resource), 
						getValueForMetric("blocker_violations", resource), getValueForMetric("critical_violations", resource), 
						getValueForMetric("complexity", resource), getValueForMetric("comment_lines", resource), 
						getValueForMetric("duplicated_lines", resource), toolId 
						});
	}

	private Double getValueForMetric(String metricKey, Resource resource) {
		Msr[] msr = resource.getMsr();
		Double metricValue = new Double("0");
		for (int i = 0; i < msr.length; i++) {
			if (msr[i].getKey() != null && msr[i].getKey().equals(metricKey)) {
				metricValue = new Double(msr[i].getVal());
				break;
			}
		}
		return metricValue;
	}

}
