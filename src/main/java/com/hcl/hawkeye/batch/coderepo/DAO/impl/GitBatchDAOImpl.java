package com.hcl.hawkeye.batch.coderepo.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hcl.hawkeye.batch.build.exception.BuildManagementMetricsException;
import com.hcl.hawkeye.batch.coderepo.DAO.GitBatchDAO;
import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;
import com.hcl.hawkeye.batch.coderepo.DO.CodeRepoConfig;
import com.hcl.hawkeye.batch.coderepo.controller.GitBatchController;
import com.hcl.hawkeye.batch.coderepo.exception.GitManagementMetricsException;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
@Configuration
@PropertySource("classpath:config.properties")
public class GitBatchDAOImpl implements GitBatchDAO {

	private static final Logger logger = LoggerFactory.getLogger(GitBatchDAOImpl.class);

	@Autowired
	Environment env;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public List<CodeManagementRepo> getAllCommitData() {
		String gitRestUrl = env.getProperty("git.restUrl");
		String gitResultFormat = env.getProperty("git.resultFormat");
		String gitPrivateTokenKey = env.getProperty("git.privateToken");
		RestTemplate restTemplate = new RestTemplate();
		List<CodeManagementRepo> codeManagementResourceList = new ArrayList<CodeManagementRepo>();
		List<CodeRepoConfig> gitURLList = getGitURLList();
		for (CodeRepoConfig gitServerUrl : gitURLList) {
			StringBuilder gitMetricRestUrl = new StringBuilder(gitServerUrl.getToolUrl());
			gitMetricRestUrl.append(gitRestUrl).append(gitResultFormat).append(gitPrivateTokenKey);
			logger.info("git framed RestUrl for fetching is: {}", gitMetricRestUrl);
			CodeManagementRepo[] gitResources = restTemplate.getForObject(gitMetricRestUrl.toString(), CodeManagementRepo[].class);
			Collections.addAll(codeManagementResourceList, gitResources);
			CodeManagementRepo.setProjectConfId(gitServerUrl.getProjectId());
			logger.debug("The result after hitting the rest api: {}", gitResources);

		}
		return codeManagementResourceList;
	}

	private List<CodeRepoConfig> getGitURLList() {
		List<CodeRepoConfig> gitrepoList = new ArrayList<CodeRepoConfig>();

		String sql_getProjects = "SELECT  PROJECT_ID,  TOOL_NAME,TOOL_HOST, TOOL_URL, USERNAME, PASSWORD  FROM DEVOPS_SERVER_CONFIG AS DSC INNER JOIN PROJECT_TOOL_MAPPING AS PTM ON DSC.TOOLID=PTM.TOOL_ID WHERE TOOL_TYPE = 'REPO'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_getProjects);
		for (Map<String, Object> row : list) {
			CodeRepoConfig gitrepoObj = new CodeRepoConfig();
			gitrepoObj.setToolUrl((String) row.get("TOOL_URL"));
			gitrepoObj.setToolHost((String) row.get("TOOL_HOST"));
			gitrepoObj.setProjectId((Long) row.get("PROJECT_ID"));
			gitrepoObj.setUsername((String) row.get("USERNAME"));
			gitrepoObj.setPassword((String) row.get("PASSWORD"));
			gitrepoList.add(gitrepoObj);
		}

		return gitrepoList;

	}

	@Override
	public boolean insertGitCommitData(final List<CodeManagementRepo> resource) {
		logger.info("inserted build data into DB of size: {}", resource.size());
		boolean status = false;
		try {
			String insert_sql = "INSERT IGNORE INTO CODE_REPO_MANAGEMENT(ID,AUTHOR_NAME,AUTHOR_EMAIL,COMMIT_DATE,BRANCH_NAME,PROJECT_ID) VALUES(?,?,?,?,?,?)";
			jdbcTemplate.batchUpdate(insert_sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					CodeManagementRepo gitDet = resource.get(arg1);
					ps.setDouble(1, gitDet.getId());
					ps.setString(2, gitDet.getAuthor_name());
					ps.setString(3, gitDet.getAuthor_email());
					ps.setTimestamp(4, gitDet.getDate());
					ps.setString(5, "MASTER");
					ps.setLong(6, gitDet.getProjectId());
				}

				@Override
				public int getBatchSize() {
					return resource.size();
				}
			});
			status = true;
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.pushgitmetrics", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new GitManagementMetricsException(errorMsg, dae);
		}
		return status;
		// TODO Auto-generated method stub

	}

}
