package com.hcl.hawkeye.batch.jira.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.batch.jira.DO.Project;
import com.hcl.hawkeye.batch.jira.DO.SprintIssues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

@Repository
public class JiraBatchUpdateDAOImpl implements JiraBatchUpdateDAO {

	private static final Logger logger = LoggerFactory.getLogger(JiraBatchUpdateDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean insertProjectDetails(Project pj) {
		return false;
	}

	@Override
	public boolean insertProjectDetails(final List<Project> pj) {
		logger.info("Requested to inserted the project data into DB of size: {}", pj.size());
		boolean status = false;
		try {
			String sql = "INSERT INTO PROJECT_METRICS_MANAGEMENT (TOOL_PROJECT_ID,PROJECTID, PROJECT_NAME, PROJECT_TYPE) "
					+ "VALUES(?,?, ?, ?) ON DUPLICATE KEY UPDATE PROJECT_NAME=VALUES(PROJECT_NAME),PROJECT_TYPE=VALUES(PROJECT_TYPE);";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					Project pro = pj.get(arg1);
					ps.setInt(1, pro.getId());
					ps.setInt(2, pro.getToolProjectId());
					ps.setString(3, pro.getName());
					ps.setString(4, pro.getType());
				}

				@Override
				public int getBatchSize() {
					return pj.size();
				}
			});
			status = true;
		} catch (DataAccessException e) {
			logger.error("Exception: {}", e);
		}
		return status;
	}

	@Override
	public boolean insertSprinttDetails(final List<ProjectValues> sprintsList) {
		logger.info("Requested to inserted the project data into DB of size: {}", sprintsList.size());
		boolean status = false;
		try {
			String sql = "INSERT INTO SPRINT_METRCIS_MANAGEMENT (TOOL_PROJECT_ID,PROJECTID,SPRINTID,SPRINT_BOARD_ID,SPRINT_STATUS,SPRINT_NAME,START_DATE,END_DATE,COMPLETED_DATE) "
					+ "VALUES(?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
					+ "SPRINT_BOARD_ID=VALUES(SPRINT_BOARD_ID),SPRINT_STATUS=VALUES(SPRINT_STATUS),SPRINT_NAME=VALUES(SPRINT_NAME),"
					+ "START_DATE=VALUES(START_DATE),END_DATE=VALUES(END_DATE),COMPLETED_DATE=VALUES(COMPLETED_DATE)";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					ProjectValues sprint = sprintsList.get(arg1);
					ps.setInt(1, sprint.getOriginBoardId());
					ps.setInt(2, sprint.getToolProjectId());
					ps.setInt(3, sprint.getId());
					ps.setInt(4, sprint.getOriginBoardId());
					ps.setString(5, sprint.getState());
					ps.setString(6, sprint.getName());
					ps.setString(7, sprint.getStartDate());
					ps.setString(8, sprint.getEndDate());
					ps.setString(9, sprint.getCompleteDate());
				}

				@Override
				public int getBatchSize() {
					return sprintsList.size();
				}
			});
			status = true;
		} catch (DataAccessException e) {
			logger.error("Exception: {}", e);
		}
		return status;
	}

	@Override
	public Map<Integer,String> getProjects() {

		String url = null;
		Locale locale = new Locale("en", "IN");

		String sql_getProjects = "SELECT PTM.PROJECT_ID,PTM.TOOL_PROJECT_ID,DSC.TOOL_HOST,DSC.TOOL_URL,DSC.TOOL_NAME,DSC.TOOL_TYPE,DSC.USERNAME,DSC.PASSWORD "
				+ "FROM DEVOPS_SERVER_CONFIG DSC, PROJECT_TOOL_MAPPING PTM WHERE PTM.TOOL_ID=DSC.TOOLID AND DSC.TOOL_NAME='JIRA'";
		Map<Integer,String> projectURLList = new HashMap<Integer,String>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_getProjects);
		for (Map<String, Object> row : list) {
			if (((String) row.get("TOOL_NAME")).equals("JIRA")) {
				url = (String) row.get("TOOL_URL")
						+ messageSource.getMessage("jira.agile.rest.api.board", new Object[] {}, locale)
						+ row.get("TOOL_PROJECT_ID").toString();
			}
			projectURLList.put(Integer.parseInt((row.get("PROJECT_ID")).toString()),url);
		}

		return projectURLList;
	}

	@Override
	public boolean insertIssueDetails(final List<SprintIssues> pj) {

		logger.info("Requested to inserted the project data into DB of size: {}", pj.size());
		boolean status = false;
		try {
			String sql = "INSERT INTO SPRINT_ISSUEMETRICS_MANAGEMENT (TOOL_PROJECT_ID,PROJECTID, SPRINTID, ISSUE_ID, ISSUE_TYPE_ID, ISSUE_TYPE, PRIORITY_ID, PRIORITY_NAME)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?,?) ON DUPLICATE KEY UPDATE "
					+ "ISSUE_ID =VALUES(ISSUE_ID), ISSUE_TYPE_ID=VALUES(ISSUE_TYPE_ID), ISSUE_TYPE=VALUES(ISSUE_TYPE), PRIORITY_ID=VALUES(PRIORITY_ID),"
					+ " PRIORITY_NAME=VALUES(PRIORITY_NAME)";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					SprintIssues pro = pj.get(arg1);
					ps.setInt(1, pro.getToolProjectId());
					ps.setInt(2, pro.getProjectId());
					ps.setInt(3, pro.getSprintId());
					ps.setString(4, pro.getIssueId());
					ps.setString(5, pro.getIssueTypeId());
					ps.setString(6, pro.getIssueType());
					ps.setString(7, pro.getPriorityId());
					ps.setString(8, pro.getPriorityName());
				}

				@Override
				public int getBatchSize() {
					return pj.size();
				}
			});
			status = true;
		} catch (DataAccessException e) {
			
			logger.error("Exception: {}", e);
		}
		return status;

	}
}
