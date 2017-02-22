package com.hcl.hawkeye.metric.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.MetricDataException;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.metric.DAO.MetricDataDAO;

@Repository
public class MetricDataDAOImpl implements MetricDataDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MetricDataDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
			logger.error("Exception in getFeedbackParameter");
			throw new MetricDataException("Exception in Feedbacktrack Details", dae);
		
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
		
		catch (DataAccessException dae) {
			logger.error("Exception in getMetricGraphDetails");
			throw new MetricDataException("Exception in Metricdata Details", dae);
		
	     }
		logger.info("get the result getMetricGraph METHOD");
		System.out.println("metricdatadetails:"+metricdatadetails.getGraph_Type());
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

	
}