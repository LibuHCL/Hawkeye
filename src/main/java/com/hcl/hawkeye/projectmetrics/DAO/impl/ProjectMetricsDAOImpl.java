package com.hcl.hawkeye.projectmetrics.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.ValueAddDataRetrievalException;
import com.hcl.hawkeye.projectmetrics.DAO.ProjectMetricsDAO;
import com.hcl.hawkeye.projectmetrics.DO.ProjectDetails;
import com.hcl.hawkeye.projectmetrics.DO.ProjectInformation;
@Repository
public class ProjectMetricsDAOImpl implements ProjectMetricsDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectMetricsDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public ProjectDetails getProjectByProgramId(Integer programId) {
		ProjectDetails projectDetails = new ProjectDetails();
		List<ProjectInformation> projectInfoList = new ArrayList<ProjectInformation>();
		String projectDetailsQuery = "SELECT PROGRAM_ID, PROJECTID, PROJECT_NAME FROM PROJECT WHERE PROGRAM_ID = ?;";
		try {
			List<Map<String, Object>> list = jdbcTemplate.queryForList(projectDetailsQuery, new Object[] { programId });
			for (Map<String, Object> row : list) {
				projectDetails.setProgramId(((Long) row.get("PROGRAM_ID")).intValue());
				ProjectInformation projectInfo = new ProjectInformation();
				projectInfo.setProjectId(((Long) row.get("PROJECTID")).intValue());
				projectInfo.setProjectName(row.get("PROJECT_NAME").toString());
				projectInfoList.add(projectInfo);
			}
			projectDetails.setProjectInformation(projectInfoList);
		}
		catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
		return projectDetails;
	}

}
