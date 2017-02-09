package com.hcl.hawkeye.codequality.DAO.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hcl.hawkeye.codequality.DAO.CodeQualityDAO;
import com.hcl.hawkeye.codequality.DO.Resource;
import com.hcl.hawkeye.codequality.controller.CodeQualityController;

@Repository
@Configuration
@PropertySource("classpath:config.properties")
public class CodeQualityDAOImpl implements CodeQualityDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeQualityController.class);

	@Autowired
	Environment env;

	public Resource getSonarMetrics(String branchId) {

		Resource sonarResultResource=new Resource();
		String sonarUrl = env.getProperty("sonar.url");
		String sonarProjectName = env.getProperty("sonar.projectname");
		String sonarRestUrl = env.getProperty("sonar.restUrl");
		String sonarMetricsList = env.getProperty("sonar.metricList");
		String sonarResultFormat = env.getProperty("sonar.resultFormat");

		// Get the Static Analysis metrics from Sonar
		StringBuilder sonarMetricRestUrl = new StringBuilder(sonarUrl);
		sonarMetricRestUrl.append(sonarRestUrl).append(sonarProjectName)
				.append(":").append(branchId).append(sonarResultFormat)
				.append(sonarMetricsList);

		logger.info("sonarMetricRestUrl: {}", sonarMetricRestUrl);

		RestTemplate restTemplate = new RestTemplate();
		Resource[] sonarResources = restTemplate.getForObject(
				sonarMetricRestUrl.toString(), Resource[].class);

		logger.debug("Resources: {}", sonarResources);
		
		if (sonarResources.length > 0)
			sonarResultResource = sonarResources[0];

		return sonarResultResource;

	}

}
