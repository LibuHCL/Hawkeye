package com.hcl.hawkeye.batch.jira.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

@Repository
public class JiraBatchUpdateDAOImpl implements JiraBatchUpdateDAO{
	
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
		boolean status = false ;
		try {
			String sql  = "INSERT IGNORE INTO PROJECTDETAILS (PROJECTID, PROJECT_NAME, PROJECT_TYPE) VALUES(?, ?, ?)";
			
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					Project pro = pj.get(arg1);
					ps.setInt(1, pro.getId());
					ps.setString(2, pro.getName());
					ps.setString(3, pro.getType());
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
	public  List<String> getProjects() {
			
			String url = null;
			Locale locale=new Locale("en", "IN");
			
			String sql_getProjects="SELECT PROJECTID,PROJECT_HOST,PROJECT_URL,PROJECT_TOOL,USERNAME,PASSWORD FROM PROJECTMANAGEMENT";
			List<String> projectURLList = new ArrayList<String>();
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_getProjects);
			for (Map<String, Object> row : list) {
				if(((String)row.get("PROJECT_TOOL")).equals("JIRA")){
					url = (String)row.get("PROJECT_URL")+messageSource.getMessage("jira.agile.rest.api.board", new Object[]{}, locale)+row.get("PROJECTID").toString();
				}
				projectURLList.add(url);
			}
			
			return projectURLList;
		}
}
