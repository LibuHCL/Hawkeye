package com.hcl.hawkeye.batch.build.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.batch.build.DAO.BuildBatchDAO;
import com.hcl.hawkeye.batch.build.DO.BuildManageConfig;
import com.hcl.hawkeye.batch.build.exception.BuildManagementMetricsException;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
public class BuildBatchDAOImpl implements BuildBatchDAO{

	private static final Logger logger = LoggerFactory.getLogger(BuildBatchDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;


	@Override
	public  List<BuildManageConfig> getBuildConfig() {
		logger.info("getting buildconfig details for all configured project into Hawkeye DB:");
		
		//V_BUILD_MANAGEMENT_BCKUP to be replaced wd V_BUILD_MANAGEMENT
		String buildsql="SELECT BTM.PROJECTID, PTM.TOOL_PROJECT_ID, BTM.MAX_BUILDNUMBER, TOOL_NAME, TOOL_HOST, TOOL_URL, USERNAME, PASSWORD FROM DEVOPS_SERVER_CONFIG AS DSC INNER JOIN PROJECT_TOOL_MAPPING AS PTM ON DSC.TOOLID=PTM.TOOL_ID INNER JOIN V_BUILD_MANAGEMENT_BCKUP AS BTM ON PTM.PROJECT_ID = BTM.PROJECTID WHERE PTM.TOOL_PROJECT_ID = BTM.PLANKEY AND TOOL_TYPE = 'BUILD'";
		//Can use directly a RowMapper implementation class without an object creation
		return jdbcTemplate.query(buildsql,new RowMapper<BuildManageConfig>(){  
			@Override  
			public BuildManageConfig mapRow(ResultSet rs, int rownumber) throws  SQLException    {  
				BuildManageConfig bconf=new BuildManageConfig();
				bconf.setProjectid(rs.getDouble(1));
				bconf.setToolProjectId(rs.getString(2));
				bconf.setHawkeyeBuildNo(rs.getDouble(3));
				bconf.setToolname(rs.getString(4));
				bconf.setToolhost(rs.getString(5));
				bconf.setToolurl(rs.getString(6));
				bconf.setUsername(rs.getString(7));
				bconf.setPassword(rs.getString(8));  
				return bconf;  
			}  
		});  
	}


	@Override
	public boolean insertBuildStatisticsDetails(final List<BuildStatisticsDetails> buildDetails) {

		logger.info("Requested to inserted the build data into DB of size: {}", buildDetails.size());
		boolean status = false;
		try {
			//BUILD_MANAGEMENT_BCKUP to be replaced wd BUILD_MANAGEMENT
			String insert_Sql = "INSERT IGNORE INTO BUILDMANAGEMENT_BCKUP(ID,BUILDTOOL,PROJECTID,BUILDNUMBER,PLANNAME,PLANSHORTNAME,PLANKEY,BUILDRESULTKEY,BUILDSTATE,LIFECYCLESTATE,ENABLED,TYPE,PROJECTKEY,PROJECTNAME,ISACTIVE,BUILDNAME,ISBUILDING,BUILDSTARTEDTIME,BUILDCOMPLETEDTIME,BUILDDURATIONINSECONDS,VCSREVISIONKEY,BUILDTESTSUMMARY,BUILDREASON,BUILDID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.batchUpdate(insert_Sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					BuildStatisticsDetails buildDet = buildDetails.get(arg1);
					ps.setDouble(1, buildDet.getId());
					ps.setString(2, buildDet.getBuildTool());
					ps.setDouble(3, buildDet.getProjectId());
					ps.setDouble(4, buildDet.getBuildNumber());
					ps.setString(5, buildDet.getPlanName());
					ps.setString(6, buildDet.getPlanShortName());
					ps.setString(7, buildDet.getPlanKey());
					ps.setString(8, buildDet.getBuildResultKey());
					ps.setString(9, buildDet.getBuildState());
					ps.setString(10, buildDet.getLifeCycleState());
					ps.setString(11, buildDet.getEnabled());
					ps.setString(12, buildDet.getType());
					ps.setString(13, buildDet.getProjectKey());
					ps.setString(14, buildDet.getProjectName());
					ps.setString(15, buildDet.getIsActive());
					ps.setString(16, buildDet.getBuildName());
					ps.setString(17, buildDet.getIsBuilding());
					ps.setTimestamp(18, HawkEyeUtils.getTimeStamp(buildDet.getBuildStartedTime()));
					ps.setTimestamp(19, Timestamp.valueOf(buildDet.getBuildCompletedTime()));
					ps.setDouble(20, buildDet.getBuildDurationInSeconds());
					ps.setString(21, buildDet.getVcsrevisionKey());
					ps.setString(22, buildDet.getBuildTestSummary());
					ps.setString(23, buildDet.getBuildReason());
					ps.setInt(24, buildDet.getBuildId());
				}

				@Override
				public int getBatchSize() {
					return buildDetails.size();
				}
			});
			status = true;
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.pushbuildmetrics", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new BuildManagementMetricsException(errorMsg, dae);
		}
		return status;
	}
	
}
