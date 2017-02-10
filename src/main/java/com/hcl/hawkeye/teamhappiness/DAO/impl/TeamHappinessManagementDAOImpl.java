/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DAO.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.teamhappiness.DAO.TeamHappinessManagementDAO;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappinessDetails;

/**
 * @author HCL
 *
 */
@Repository
public class TeamHappinessManagementDAOImpl implements TeamHappinessManagementDAO{
	private static final Logger logger = LoggerFactory.getLogger(TeamHappinessManagementDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public TeamHappiness capHappinessDetails(TeamHappiness happiness) {
		logger.info("Capturing the happiness details");
		String sql_insertesc = "INSERT INTO TEAM_HAPPINESS (PROJECTID,EMAIL,SCALE,DESCRIPTION,CAPTURE_DATE)VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql_insertesc,new Object[] {happiness.getProjectId(),happiness.getEmail(),happiness.getScale(),happiness.getDescription(),happiness.getCaptureDate()});
		return populateHappinessWithId(happiness, geMaxId());
	}
	
	public static TeamHappiness populateHappinessWithId(TeamHappiness happiness, BigInteger Id) {
		TeamHappiness createdHappiness=new TeamHappiness();
		BeanUtils.copyProperties(happiness, createdHappiness);
		createdHappiness.setId(Id);
		return createdHappiness;
	}
	
	public BigInteger geMaxId() {
		String highestIdQuery="SELECT MAX(ID) FROM TEAM_HAPPINESS";
		return jdbcTemplate.queryForObject(highestIdQuery, BigInteger.class);
	}
	
	@Override
	public Graph  getHappinessAverageByProject(int projectId, int teamYear) {
		ArrayList<Double> graphData = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		Graph teamHapDetails = new Graph();
		String sql="SELECT QUARTER(CAPTURE_DATE) AS quarter,AVG(SCALE) AS rating FROM TEAM_HAPPINESS WHERE PROJECTID ="+projectId+" AND YEAR(CAPTURE_DATE)="+teamYear+" GROUP BY YEAR(CAPTURE_DATE), QUARTER(CAPTURE_DATE) ORDER BY YEAR(CAPTURE_DATE), QUARTER(CAPTURE_DATE)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		//HashMap<String,Double> quarteRes = new HashMap<>();
		for (Map<String, Object> row : list) {
			//quarteRes.put(String.valueOf(row.get("quarter")), Double.valueOf(String.valueOf(row.get("rating"))));
			String q =String.valueOf(row.get("quarter"));
			labels.add(q.equals("1")? "Q1":(q.equals("2") ? "Q2" : (q.equals("3") ? "Q3" :"Q4")));
			graphData.add(Double.valueOf(String.valueOf(row.get("rating"))));
		}
		teamHapDetails.setGraphData(graphData);
		teamHapDetails.setLabels(labels);
		return teamHapDetails;		
	}

}
