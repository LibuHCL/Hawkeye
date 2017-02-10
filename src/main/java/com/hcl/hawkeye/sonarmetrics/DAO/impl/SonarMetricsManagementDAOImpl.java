/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.DAO.impl;

import java.sql.ResultSet;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.SonarMetricsDataRetrievalException;
import com.hcl.hawkeye.Exceptions.ValueAddDataRetrievalException;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.portfolio.DO.Result;
import com.hcl.hawkeye.sonarmetrics.DAO.SonarMetricsManagementDAO;
import com.hcl.hawkeye.sonarmetrics.DO.Metrics;
import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;
import com.hcl.hawkeye.sonarmetrics.service.SonarMetricsManagementService;
import com.hcl.hawkeye.valueaddmanagement.DO.Kpi;

/**
 * @author HCL
 *
 */
@Repository
public class SonarMetricsManagementDAOImpl implements SonarMetricsManagementDAO {
	private static final Logger logger = LoggerFactory.getLogger(SonarMetricsManagementDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public List<Trackers> getSonarMetricsData(Integer projectId) {
		logger.info("Request to get the Sonar Metrics at project level");
		//SonarMetrics sonarMetrics = new SonarMetrics();
		List<SonarMetrics> sonarMetrics = new ArrayList<SonarMetrics>();
		List<Trackers> trackers = new ArrayList<Trackers>();
		try {
			//String fetchMetricsData = "SELECT * FROM CODE_QUALITY_TRACKER WHERE PROJECTID=? AND SPRINT IN(SELECT SPRINT FROM CODE_QUALITY_TRACKER WHERE PROJECTID=? ORDER BY CREATION_DATE DESC LIMIT 4)";
			String fetchMetricsData = "SELECT SPRINT as sprint BLOCKER_ISSUES AS blockers,TECHNICAL_DEBT AS TechDebt,COMPLEXITY AS Complexity,DUPLICATED_LINES_DENSITY AS DuplicateLines,CRITICAL_ISSUES AS CriticalIssues,COMMENTED_LINES AS CommentedLines FROM CODE_QUALITY_TRACKER  WHERE  PROJECTID=?  ORDER BY CREATION_DATE DESC  LIMIT 4";
			List<Map<String, Object>> resultList = jdbcTemplate.queryForList(fetchMetricsData,new Object[] { projectId},SONARMETRICSROWMAPPER);
			if(resultList  != null && resultList.size() >0){
				for (Map<String, Object> row : resultList) {
					Metrics metrics = new Metrics();
					Trackers tracker=new Trackers();
					tracker.setSprint((String)row.get("sprint"));
					tracker.setBlockers((Integer) row.get("blockers"));
					tracker.setTechnicalDebt((Integer) row.get("TechDebt"));
					tracker.setComplexity((Integer) row.get("Complexity"));
					tracker.setDuplicateLines((Integer) row.get("DuplicateLines"));
					tracker.setCritical((Integer) row.get("CriticalIssues"));
					tracker.setCommentedLines((Integer) row.get("CommentedLines"));
					trackers.add(tracker);
		        } 
			}
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.getsonarmetrics", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new SonarMetricsDataRetrievalException(errorMsg, dae);
		}
		return trackers;
	}

	RowMapper<SonarMetrics> SONARMETRICSROWMAPPER = new RowMapper<SonarMetrics>() {

		@Override
		public SonarMetrics mapRow(ResultSet rSet, int arg1) throws SQLException {
			SonarMetrics sonarMetrics = new SonarMetrics();
			return sonarMetrics;
		}
	};

}
