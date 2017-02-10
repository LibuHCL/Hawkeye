package com.hcl.hawkeye.escalationmanagement.DAO.impl;

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
		String sql = "SELECT QUARTER(ESCALATION_REPORED_DATE) AS quarter, COUNT(ESCALATIONID) AS count FROM ESCALATION "+
						"WHERE PROJECTID = ?  AND ESCALATION_REPORED_DATE >= DATE_FORMAT( curdate() - INTERVAL 12 MONTH, '%Y/%m/01' ) "
						+ "GROUP BY QUARTER(ESCALATION_REPORED_DATE) ORDER BY YEAR(ESCALATION_REPORED_DATE) DESC, QUARTER(ESCALATION_REPORED_DATE)";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,new Object[] { projectId });		
		if(resultList  != null && resultList.size() >0){
			for (Map<String, Object> row : resultList) {
				//quarteRes.put(String.valueOf(row.get("quarter")), Double.valueOf(String.valueOf(row.get("rating"))));
				String q =String.valueOf(row.get("quarter"));
				labels.add(q.equals("1")? "Q1":(q.equals("2") ? "Q2" : (q.equals("3") ? "Q3" :"Q4")));
				graphData.add((Double) row.get("count"));
			}
		}
		escDetList.setGraphData(graphData);
		escDetList.setLabels(labels);

		return escDetList;
	}

	@Override
	public List<Graph> noOfEscPerQtAtProgram(Integer programId) {
		logger.info("Request to get the no.of escalations per quarter for project :"+programId);
		
		List<Graph> escDetList = new ArrayList<Graph>();
		
		String sql = "SELECT  QUARTER(ESCALATION_REPORED_DATE) AS quarter, COUNT(ESCALATIONID) AS count FROM ESCALATION e, PROJECT p WHERE e.PROJECTID=p.PROJECTID "+
				"AND p.PROGRAM_ID = ? AND ESCALATION_REPORED_DATE >= DATE_FORMAT( curdate() - INTERVAL 12 MONTH, '%Y/%m/01' ) "
				+ "GROUP BY  QUARTER(ESCALATION_REPORED_DATE) ORDER BY YEAR(ESCALATION_REPORED_DATE) DESC, QUARTER(ESCALATION_REPORED_DATE)";

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
}
