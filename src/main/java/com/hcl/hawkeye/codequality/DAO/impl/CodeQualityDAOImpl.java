package com.hcl.hawkeye.codequality.DAO.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<String> metricsList = new ArrayList<>(
				Arrays.asList((env.getProperty("sonar.metrics")).split("\\s*,\\s*")));
		String createValueAddQuery = "INSERT INTO CODE_QUALITY"
				+ " (PROJECTID,PROJECTKEY,SPRINT,TECHNICAL_DEBT,BLOCKER_ISSUES,"
				+ "CRITICAL_ISSUES,COMPLEXITY,COMMENTED_LINES,DUPLICATED_LINES_DENSITY,TOOL_ID ) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(createValueAddQuery,
				new Object[] { resource.getId(), resource.getKey(), "", getValueForMetric("sqale_debt_ratio", resource), 
						getValueForMetric("blocker_violations", resource), getValueForMetric("critical_violations", resource), 
						getValueForMetric("complexity", resource), getValueForMetric("comment_lines", resource), 
						getValueForMetric("duplicated_lines", resource), "" 
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
