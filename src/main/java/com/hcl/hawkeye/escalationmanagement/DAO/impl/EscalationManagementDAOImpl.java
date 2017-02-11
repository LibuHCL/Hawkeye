package com.hcl.hawkeye.escalationmanagement.DAO.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.escalationmanagement.DAO.EscalationManagementDAO;
import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
public class EscalationManagementDAOImpl implements EscalationManagementDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EscalationManagementDAOImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public Escalation capEscalationDetails(Escalation esc) {
		
		logger.info("Capturing the escalation details");
		String sql_insertesc = "INSERT INTO ESCALATION (PROJECTID,DESCRIPTION,PRIORITY,ESCALATION_TYPE,REPORTER_RESOURCE_ID,REPORTER_COMPANY_ID,"
				+ "REPORTER_TYPE,REPORTEE_RESOURCE_ID,ESCALATION_REPORED_DATE,RESOLUTION_DATE,RESOLUTION_SOLUTION,ESCALATION_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql_insertesc,new Object[] {esc.getProjId(),esc.getDescription(),esc.getPriority(),esc.getEscalationType(),esc.getRepResourceId(),
				esc.getRepCompanyId(),esc.getRepType(),esc.getReporteeResId(),esc.getEscRepDate(),esc.getResDate(),esc.getResSolution(),esc.getEscStatus()});
		
		return HawkEyeUtils.populateEscalationWithescId(esc, geMaxEscId());
	}

	private int geMaxEscId() {
		String highestPortIdQuery  ="SELECT MAX(ESCALATIONID) FROM ESCALATION";
		return jdbcTemplate.queryForObject(highestPortIdQuery, Integer.class);
	}

	@Override
	public Graph noOfEscAtProject(int  projectId) {
		logger.info("Request to get the no.of escalations per quarter for project : {}",projectId);
		ArrayList<Double> graphData = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		
		Graph escDetList = new Graph();
		String sql =  "SELECT  VALUE.PROJECTID , "
			+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1', "
			+ "  SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2', "
			+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3', "
			+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4' FROM " 
			+ " (SELECT esc.ESCALATIONID,esc.PROJECTID, QUARTER(esc.ESCALATION_REPORED_DATE) as QUARTER,  COUNT(1) AS TOTAL" 
			+ " FROM ESCALATION esc  "
			+ "	WHERE esc.PROJECTID= ? AND esc.ESCALATION_REPORED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)"
			+ "	GROUP BY esc.ESCALATIONID, QUARTER) VALUE GROUP BY VALUE.PROJECTID";
			
			
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { projectId });
			for (Map<String, Object> row : list) {
				graphData.add(Double.parseDouble(String.valueOf(row.get("Q1"))));
				graphData.add(Double.parseDouble(String.valueOf(row.get("Q2"))));
				graphData.add(Double.parseDouble(String.valueOf(row.get("Q3"))));
				graphData.add(Double.parseDouble(String.valueOf(row.get("Q4"))));
			}
			updateQuarterlyLabels(labels);
			escDetList.setGraphData(graphData);
			escDetList.setLabels(labels);
	
			return escDetList;
		
	}

	@Override
	public List<Graph> noOfEscPerQtAtProgram(Integer programId) {
		logger.info("Request to get the no.of escalations per quarter for project :"+programId);
		
		List<Graph> escDetList = new ArrayList<Graph>();
		
		String sql =  "SELECT  VALUE.PROGRAMID , "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1', "
				+ "  SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2', "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3', "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4' FROM " 
				+ " (SELECT esc.ESCALATIONID,proj.PROGRAM_ID, QUARTER(esc.ESCALATION_REPORED_DATE) as QUARTER,  COUNT(1) AS TOTAL" 
				+ " FROM ESCALATION esc, PROJECT proj "
				+ "	WHERE proj.PROJECTID = esc.PROJECTID"
				+ "	AND proj.PROGRAM_ID= ? AND esc.ESCALATION_REPORED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)"
				+ "	GROUP BY esc.ESCALATIONID, QUARTER) VALUE GROUP BY VALUE.PROGRAM_ID";
		
		

		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,new Object[] { programId });
		
		if(resultList  != null && resultList.size() >0){
			for (Map<String, Object> row : resultList) {
				Graph escDet = new Graph();
				logger.info(" Qurter:"+row.get("quarter"));
	            //escDet.setQuarter((Integer)row.get("quarter"));
	            //escDet.setCount(Integer.valueOf(row.get("count").toString()));
	            escDetList.add(escDet);
	        } 
		}

		return escDetList;
	}

	@Override
	public Graph noOfEscAtPortfolioLevelPerQt(int portfolioId) {
		logger.info("Request to get the no.of escalations per quarter for project : {}",portfolioId);
		ArrayList<Double> graphData = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		
		Graph escDetList = new Graph();
		String sql_noofesc = "SELECT  VALUE.PORTFOLIO_ID , "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1', "
				+ "  SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2', "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3', "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4' FROM " 
				+ " (SELECT pf.PORTFOLIO_ID,esc.ESCALATIONID, QUARTER(esc.ESCALATION_REPORED_DATE) as QUARTER,  COUNT(2) AS TOTAL" 
				+ " FROM ESCALATION esc, PROJECT proj, PROGRAM prog, PORTFOLIO pf"
				+ " WHERE pf.PORTFOLIO_ID=prog.PORTFOLIO_ID"
				+ "	AND prog.PROGRAMID=proj.PROGRAM_ID"
				+ "	AND proj.PROJECTID = esc.PROJECTID"
				+ "	AND pf.PORTFOLIO_ID= ? AND esc.ESCALATION_REPORED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)"
				+ "	GROUP BY esc.ESCALATIONID, QUARTER) VALUE GROUP BY VALUE.PORTFOLIO_ID";
               
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_noofesc, new Object[] { portfolioId });
		for (Map<String, Object> row : list) {
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q1"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q2"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q3"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q4"))));
		}
		updateQuarterlyLabels(labels);
		escDetList.setGraphData(graphData);
		escDetList.setLabels(labels);

		return escDetList;
	}
	
	private void updateQuarterlyLabels(ArrayList<String> labels) {
		labels.add("Q1");
		labels.add("Q2");
		labels.add("Q3");
		labels.add("Q4");
	}

}
