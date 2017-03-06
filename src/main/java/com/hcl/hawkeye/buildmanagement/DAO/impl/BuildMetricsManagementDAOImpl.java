package com.hcl.hawkeye.buildmanagement.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.MetricDataDO.ProgramDO;
import com.hcl.hawkeye.buildmanagement.DAO.BuildMetricsManagementDAO;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.portfolio.DO.Graph;
@Repository
public class BuildMetricsManagementDAOImpl implements BuildMetricsManagementDAO {
	private static final Logger logger = LoggerFactory.getLogger(BuildMetricsManagementDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Graph getBuildsPerDay(int projectId) {
		// TODO Auto-generated method stub
		{   
			ArrayList<Double> graphData = new ArrayList<Double>();
			ArrayList<String> labels = new ArrayList<String>();
			String getBuildDetailsPerdDay = "select count(BUILDID) from BUILDMANAGEMENT WHERE PROJECTID = ?  GROUP BY DATE(BUILDSTARTEDTIME) ORDER BY DATE(BUILDSTARTEDTIME) DESC LIMIT 14";
			Graph numberofbuilds = new Graph();
			List<Map<String, Object>> buildList = jdbcTemplate.queryForList(getBuildDetailsPerdDay,	new Object[] {projectId });
			System.out.println("buildList:"+buildList);
			if (buildList != null && buildList.size() > 0) {
					for (Map<String, Object> row : buildList) {
						for (String str : row.keySet()) {
							//graphData.add((Integer) row.get(str));
							graphData.add(Double.valueOf(row.get(str).toString()));
													
						}
					}
				    daysLabels(labels);
					numberofbuilds.setGraphData(graphData);
					numberofbuilds.setLabels(labels);
					System.out.println("graphdata:"+graphData);
				}
			
			return numberofbuilds;
		}

	}
	
	private void daysLabels(ArrayList<String> labels) {
		labels.add("DAY1");
		labels.add("DAY2");
		labels.add("DAY3");
		labels.add("DAY4");
		labels.add("DAY5");
		labels.add("DAY6");
		labels.add("DAY7");
		labels.add("DAY8");
		labels.add("DAY9");
		labels.add("DAY10");
		labels.add("DAY11");
		labels.add("DAY12");
		labels.add("DAY13");
		labels.add("DAY14");
		
	}

	@Override
	public List<BuildStatisticsDetails> getLatestBuildDetails(String planKey, int projectId) {
		// TODO Auto-generated method stub
		List<BuildStatisticsDetails> latestList = new ArrayList<BuildStatisticsDetails>();
		String LATESTBUILD_SQL = "select BUILDID,BUILDSTARTEDTIME from BUILDMANAGEMENT where PLANKEY= ? AND PROJECTID= ? AND BUILDSTARTEDTIME BETWEEN (NOW() - INTERVAL 5 DAY) AND NOW()";
		try{
			List<Map<String, Object>> bldList = jdbcTemplate.queryForList(LATESTBUILD_SQL,new Object[] {planKey,projectId});
			System.out.println("proList:"+bldList);
			if(bldList  != null && bldList.size() >0)  
			{
				for (Map<String, Object> row : bldList) {
					
					BuildStatisticsDetails buildValues = new BuildStatisticsDetails();
					buildValues.setBuildId(Integer.valueOf(row.get("BUILDID").toString()));
					buildValues.setBuildName((String)row.get("BUILDNAME"));
					latestList.add(buildValues);
					
		        } 
			}
		   }
		
		catch (EmptyResultDataAccessException dae) {
			logger.error("Exception in getLatestBuildDetails");
			}
		return latestList;
	}

	@Override
	public List<BuildStatisticsDetails> getTodayDetails(String planKey, int projectId) {
		// TODO Auto-generated method stub
		List<BuildStatisticsDetails> latestList = new ArrayList<BuildStatisticsDetails>();
		String LATESTBUILD_SQL = "select BUILDSTARTEDTIME,count(BUILDID) from BUILDMANAGEMENT WHERE PLANKEY=? and PROJECTID=? AND DATE(BUILDSTARTEDTIME)= current_date() group by DATE(BUILDSTARTEDTIME)";
		try{
			List<Map<String, Object>> bldList = jdbcTemplate.queryForList(LATESTBUILD_SQL,new Object[] {planKey,projectId});
			System.out.println("proList:"+bldList);
			if(bldList  != null && bldList.size() >0)  
			{
				for (Map<String, Object> row : bldList) {
					
					BuildStatisticsDetails buildValues = new BuildStatisticsDetails();
					buildValues.setBuildId(Integer.valueOf(row.get("BUILDID").toString()));
					buildValues.setBuildName((String)row.get("BUILDNAME"));
					latestList.add(buildValues);
					
		        } 
			}
		   }
		
		catch (EmptyResultDataAccessException dae) {
			logger.error("Exception in getTodayDetails");
			}
		return latestList;
	
	}

	@Override
	public Graph getAvgBuildDuration(int projectId) {
		// TODO Auto-generated method stub
		logger.info("Request to get the no.of builds per quarter for project : {}",+projectId);
		ArrayList<Double> graphData = new ArrayList<Double>();
		System.out.println("graphData:"+graphData);
		ArrayList<String> labels = new ArrayList<String>();
		Graph buildDetList = new Graph();
		String sql_det = "SELECT AVG(BUILDDURATIONINSECONDS) AS BUILDDURATION FROM BUILDMANAGEMENT WHERE PROJECTID=? AND BUILDSTARTEDTIME BETWEEN (NOW() - INTERVAL 14 DAY) AND NOW() GROUP BY PROJECTID,BUILDSTARTEDTIME ORDER BY BUILDCOMPLETEDTIME DESC";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql_det, new Object[] {projectId});
		if (resultList != null && resultList.size() > 0) {
			for (Map<String, Object> row : resultList) {
				graphData.add(Double.parseDouble(String.valueOf(row.get("BUILDDURATION"))));

			}
		}
		daysLabels(labels);
		buildDetList.setGraphData(graphData);
		buildDetList.setLabels(labels);
		System.out.println("graphdatachalla:"+graphData);
		return buildDetList;
	}

	}
