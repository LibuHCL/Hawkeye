package com.hcl.hawkeye.escalationmanagement.DAO.impl;

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
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
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
	public EscalationDetails noOfEscAtProject(Integer projectId) {
		logger.info("Request to get the no.of escalations per quarter for project :"+projectId);
		EscalationDetails noofEscs= new EscalationDetails();
		String sql = "SELECT QUARTER(ESCALATION_REPORED_DATE) AS quarter, COUNT(ESCALATIONID) AS count FROM ESCALATION "+
						"WHERE PROJECTID = ?  GROUP BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE) ORDER BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE)";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,new Object[] { projectId });
		
		if(resultList  != null && resultList.size() >0){
			for (Map<String, Object> row : resultList) {
				logger.info(" Qurter:"+row.get("quarter"));
				noofEscs.setQuarter((Integer)row.get("quarter"));
				noofEscs.setCount(Integer.valueOf(row.get("count").toString()));
	        } 
		}
		
		return noofEscs;
	}

	@Override
	public EscalationDetails noOfEscPerQtAtProgram(Integer programId) {
		logger.info("Request to get the no.of escalations per quarter for project :"+programId);
		EscalationDetails noofEscs= new EscalationDetails();
		String sql = "SELECT QUARTER(ESCALATION_REPORED_DATE) AS quarter, COUNT(ESCALATIONID) AS count FROM ESCALATION e, PROJECT p WHERE e.PROJECTID=p.PROJECTID "+
				"AND p.PROGRAM_ID = ? GROUP BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE) ORDER BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE)";

		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,new Object[] { programId });
		
		if(resultList  != null && resultList.size() >0){
			for (Map<String, Object> row : resultList) {
				logger.info(" Qurter:"+row.get("quarter"));
				noofEscs.setQuarter((Integer)row.get("quarter"));
				noofEscs.setCount(Integer.valueOf(row.get("count").toString()));
	        } 
		}
		
		return noofEscs;
	}
}
