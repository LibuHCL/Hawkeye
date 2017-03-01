package com.hcl.hawkeye.metric.DAO.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.MetricDataException;
import com.hcl.hawkeye.Exceptions.ValueAddDataRetrievalException;
import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.MetricDataDO.PortfolioDO;
import com.hcl.hawkeye.MetricDataDO.ProgramDO;
import com.hcl.hawkeye.MetricDataDO.ProjectDo;
import com.hcl.hawkeye.metric.DAO.MetricDataDAO;
import com.hcl.hawkeye.portfolio.DO.Project;


@Repository
public class MetricDataDAOImpl implements MetricDataDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MetricDataDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;
	
	private static final int INSERT_BATCH_SIZE = 5;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<MetricDataDO> getNumberofMetricName(String screenName) {
		// TODO Auto-generated method stub
		List<MetricDataDO> NameList = new ArrayList<MetricDataDO>();
		String getMetricNames_SQL = "SELECT * FROM METRIC_DATA WHERE SCREEN_NAME = ? ";
		try{
			List<Map<String, Object>> metricnameList = jdbcTemplate.queryForList(getMetricNames_SQL,new Object[] {screenName});
			if(metricnameList  != null && metricnameList.size() >0)  
			{
				for (Map<String, Object> row : metricnameList) {
					
					MetricDataDO metricnm = new MetricDataDO();
					metricnm.setMetric_Name((String)row.get("METRIC_NAME"));
					NameList.add(metricnm);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getNumberofMetricName");
			throw new MetricDataException("Exception in MetricDataDAOImpl Details", dae);
		
	     }
		return NameList;
	}
	
	@Override
	public MetricDataDO getMetricGraph(String screenname) {
		// TODO Auto-generated method stub
		MetricDataDO metricdatadetails = null;
		String getgrapth_SQL = "SELECT MD . * FROM  METRIC_DATA MD, METRIC_CONFIGURATION MC WHERE MD.METRIC_NAME = MC.METRICNAME AND MD.SCREEN_NAME = ?";
		try{
			metricdatadetails = jdbcTemplate.queryForObject(getgrapth_SQL, new Object[] {screenname}, rowMapper);
		   }
		
		catch (EmptyResultDataAccessException  dae) {
			logger.error("Exception in getMetricGraphDetails");
			//throw new MetricDataException("Exception in Metricdata Details", dae);		
	     }
		logger.info("get the result getMetricGraph METHOD");
		return metricdatadetails;
	}
	
	RowMapper<MetricDataDO> rowMapper = new RowMapper<MetricDataDO>() {
		@Override
		public MetricDataDO mapRow(ResultSet rSet, int arg1) throws SQLException {
			MetricDataDO metricdata = new MetricDataDO();
			metricdata.setGraph_Type(rSet.getString("GRAPH_TYPE"));
			return metricdata;
		}
		
	};

	@Override
	public Map<String, String> getMetricsDetail(String screenName) {
		Map<String, String> metricDetails = new HashMap<String, String>();
		String getgrapth_SQL = "SELECT MD . * FROM  METRIC_DATA MD, METRIC_CONFIGURATION MC WHERE MD.METRIC_NAME = MC.METRICNAME AND MD.SCREEN_NAME = ?";
		try{
			List<Map<String, Object>> list = jdbcTemplate.queryForList(getgrapth_SQL, new Object[] { screenName });
			for (Map<String, Object> row : list) {
				metricDetails.put((row.get("METRIC_NAME")).toString(), (row.get("GRAPH_TYPE")).toString());
			}
		   }
		
		catch (EmptyResultDataAccessException  dae) {
			logger.error("Exception in getMetricGraphDetails");
			//throw new MetricDataException("Exception in Metricdata Details", dae);		
	     }
		logger.info("get the result getMetricGraph METHOD");
		return metricDetails;
	}

	@Override
	public MetricConfiguration createMetricConfig(MetricConfiguration metricConfig) {
		String createMetricConfig = "INSERT INTO METRIC_CONFIGURATION (METRICNAME) "
				+ "VALUES (?)";
		try {
		jdbcTemplate.update(createMetricConfig,
				new Object[] { metricConfig.getMetricName()});
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new ValueAddDataRetrievalException(errorMsg, dae);
			}		
		return metricConfig;
	}

	@Override
	public List<MetricDataDO> getMetricscreenDetail() {
		List<MetricDataDO> metricDataList = new ArrayList<>();
		String getgrapth_SQL = "SELECT MD . * FROM  METRIC_DATA MD, METRIC_CONFIGURATION MC WHERE MD.METRIC_NAME = MC.METRICNAME";
		try{
			List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(getgrapth_SQL);
			for (Map<String, Object> row : categoryList) {
				MetricDataDO dd = new MetricDataDO();
				dd.setScreen_Name((String) row.get("SCREEN_NAME"));
				dd.setMetric_Name((String) row.get("METRIC_NAME"));
				metricDataList.add(dd);
			}
		}
		catch (EmptyResultDataAccessException  dae) {
			logger.error("Exception in getMetricGraphDetails");
		}
		return metricDataList;
	}

	@Override
	public List<MetricData> getMetricData() {
		// TODO Auto-generated method stub
		List<MetricData> metricdata = new ArrayList<MetricData>();
		String getMetricNames_SQL = "SELECT * FROM METRIC_DATA WHERE METRIC_NAME NOT IN (SELECT METRICNAME FROM METRIC_CONFIGURATION)";
		try{
			List<Map<String, Object>> metricnameList = jdbcTemplate.queryForList(getMetricNames_SQL);
			System.out.println("metricnameList:"+metricnameList);
			if(metricnameList  != null && metricnameList.size() >0)  
			{
				for (Map<String, Object> row : metricnameList) {
					
					MetricData metricnm = new MetricData();
					metricnm.setMetric_Name((String)row.get("METRIC_NAME"));
					metricnm.setGraph_Type((String)row.get("GRAPH_TYPE"));
					metricnm.setScreen_Name((String)row.get("SCREEN_NAME"));
					metricdata.add(metricnm);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getMetricData");
			throw new MetricDataException("Exception in MetricDataDAOImpl Details", dae);
		
	     }
	 return metricdata;
	}

	@Override
	public List<PortfolioDO> getPortfolioDetails() {
		// TODO Auto-generated method stub
		List<PortfolioDO> portfoliolist = new ArrayList<>();
		String getgrapth_SQL = "select * from PORTFOLIO";
		try{
			List<Map<String, Object>> portfolioList = jdbcTemplate.queryForList(getgrapth_SQL);
			System.out.println("portfolioList:"+portfolioList);
			for (Map<String, Object> row : portfolioList) {
				PortfolioDO plist = new PortfolioDO();
				plist.setPortfolio_Id(Integer.valueOf(row.get("PORTFOLIO_ID").toString()));
				plist.setPortfolio_name((String)row.get("PORTFOLIO_NAME"));
				portfoliolist.add(plist);
			}
		}
		catch (EmptyResultDataAccessException  dae) {
			logger.error("Exception in getPortfolioDetails");
		}
		return portfoliolist;
	
	}

	@Override
	public List<ProgramDO> getProgramDetails(int portfolioID) {
		// TODO Auto-generated method stub
		List<ProgramDO> programList = new ArrayList<ProgramDO>();
		String PROGRAM_SQL = "select * from PROGRAM where PORTFOLIO_ID = ? ";
		try{
			List<Map<String, Object>> proList = jdbcTemplate.queryForList(PROGRAM_SQL,new Object[] {portfolioID});
			System.out.println("proList:"+proList);
			if(proList  != null && proList.size() >0)  
			{
				for (Map<String, Object> row : proList) {
					
					ProgramDO program = new ProgramDO();
					program.setProgramId(Integer.valueOf(row.get("PROGRAMID").toString()));
					program.setProgramName((String)row.get("PROGRAM_NAME"));
							
					programList.add(program);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getPortfolioDetails");
			}
		return programList;}

	@Override
	public List<ProgramDO> getProgramNotAssociateDetails(int portfolioID) {
		// TODO Auto-generated method stub
		List<ProgramDO> programList = new ArrayList<ProgramDO>();
		String PROGRAM_SQL = "select * from PROGRAM where PORTFOLIO_ID != ? ";
		try{
			List<Map<String, Object>> proList = jdbcTemplate.queryForList(PROGRAM_SQL,new Object[] {portfolioID});
			System.out.println("proList:"+proList);
			if(proList  != null && proList.size() >0)  
			{
				for (Map<String, Object> row : proList) {
					
					ProgramDO program = new ProgramDO();
					program.setProgramId(Integer.valueOf(row.get("PROGRAMID").toString()));
					program.setProgramName((String)row.get("PROGRAM_NAME"));
					programList.add(program);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getPortfolioDetails");
			}
		return programList;
	
	}
	@Override
	public List<ProjectDo> getProjectDetails(int programID) {
		// TODO Auto-generated method stub
		List<ProjectDo> programList = new ArrayList<ProjectDo>();
		String PROGRAM_SQL = "select * from PROJECT where PROGRAM_ID = ? ";
		try{
			List<Map<String, Object>> proList = jdbcTemplate.queryForList(PROGRAM_SQL,new Object[] {programID});
			System.out.println("proList:"+proList);
			if(proList  != null && proList.size() >0)  
			{
				for (Map<String, Object> row : proList) {
					
					ProjectDo program = new ProjectDo();
					program.setProjectID(Integer.valueOf(row.get("PROJECTID").toString()));
					program.setProjectName((String)row.get("PROJECT_NAME"));
					programList.add(program);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getPortfolioDetails");
			}
		return programList;
	}
	
	@Override
	public List<ProjectDo> getProjectNotAssociateDetails(int programID) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<ProjectDo> programList = new ArrayList<ProjectDo>();
		String PROGRAM_SQL = "select * from PROJECT where PROGRAM_ID != ? ";
		try{
			List<Map<String, Object>> proList = jdbcTemplate.queryForList(PROGRAM_SQL,new Object[] {programID});
			System.out.println("proList:"+proList);
			if(proList  != null && proList.size() >0)  
			{
				for (Map<String, Object> row : proList) {
					
					ProjectDo program = new ProjectDo();
					program.setProjectID(Integer.valueOf(row.get("PROJECTID").toString()));
					program.setProjectName((String)row.get("PROJECT_NAME"));
					programList.add(program);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getPortfolioDetails");
			}
		return programList;
	
	}
	@Override
	public List<ProgramDO> getPorgramList() {
		// TODO Auto-generated method stub
		List<ProgramDO> programList = new ArrayList<>();
		String getgrapth_SQL = "SELECT * FROM PROGRAM";
		try{
			List<Map<String, Object>> progList = jdbcTemplate.queryForList(getgrapth_SQL);
			System.out.println("programList:"+progList);
			for (Map<String, Object> row : progList) {
				ProgramDO plist = new ProgramDO();
				plist.setProgramId(Integer.valueOf(row.get("PROGRAMID").toString()));
				plist.setProgramName((String)row.get("PROGRAM_NAME"));
				programList.add(plist);
			}
		}
		catch (EmptyResultDataAccessException  dae) {
			logger.error("Exception in getPortfolioDetails");
		}
		return programList;
	
	}

	@Override
	public void addProjectsToProgram(List<Project> progList) {
		// TODO Auto-generated method stub
		logger.info("Inside addProjectsToProgram method");
		String sql_update = "UPDATE PROJECT SET PROGRAM_ID= ? WHERE PROJECTID = ?";	
		for (int i = 0; i < progList.size(); i += INSERT_BATCH_SIZE) {
			 
			final List<Project> batchList = progList.subList(i, i+ INSERT_BATCH_SIZE > progList.size() ? progList.size() : i+ INSERT_BATCH_SIZE);
			logger.info(" batchList size =="+ batchList.size());
			jdbcTemplate.batchUpdate(sql_update,new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement pStmt, int j)throws SQLException {
							Project proj = batchList.get(j);
							logger.info("Project details=="+proj.getProgId()+"====="+ proj.getProjectId());
							pStmt.setInt(1, proj.getProgId());
							pStmt.setLong(2, proj.getProjectId());							
						}
 
						@Override
						public int getBatchSize() {
							return batchList.size();
						}
				});
		}
	
		
	}

	
}
