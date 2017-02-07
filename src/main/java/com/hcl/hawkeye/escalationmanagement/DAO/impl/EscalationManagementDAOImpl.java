package com.hcl.hawkeye.escalationmanagement.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.escalationmanagement.DAO.EscalationManagementDAO;
import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;

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
		Escalation escDet = null;
		String sql_insertesc = "INSERT INTO ESCALATION (PROJECTID,DESCRIPTION,PRIORITY,ESCALATION_TYPE,REPORTER_RESOURCE_ID,REPORTER_COMPANY_ID,"
				+ "REPORTER_TYPE,REPORTEE_RESOURCE_ID,ESCALATION_REPORED_DATE,RESOLUTION_DATE,RESOLUTION_SOLUTION,ESCALATION_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql_insertesc,new Object[] {esc.getProjId(),esc.getDescription(),esc.getPriority(),esc.getEscalationType(),esc.getRepResourceId(),
				esc.getRepCompanyId(),esc.getRepType(),esc.getReporteeResId(),esc.getEscRepDate(),esc.getResDate(),esc.getResSolution(),esc.getEscStatus()});
		
		return escDet;
	}

	@Override
	public EscalationDetails noOfEscAtProject(Integer projectId) {
		logger.info("Request to get the no.of escalations per quarter for project :"+projectId);
		EscalationDetails noofEscs= null;
		String sql = "SELECT QUARTER(ESCALATION_REPORED_DATE) AS quarter, COUNT(ESCALATIONID) AS esccount FROM escalation"+
						"WHERE PROJECTID = ?  GROUP BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE) ORDER BY YEAR(ESCALATION_REPORED_DATE), QUARTER(ESCALATION_REPORED_DATE);";
		noofEscs = jdbcTemplate.queryForObject(sql,new Object[] { projectId }, noOFEscMapper);
		
		return noofEscs;
	}
	
	RowMapper<EscalationDetails> noOFEscMapper = new RowMapper<EscalationDetails>() {

		@Override
		public EscalationDetails mapRow(ResultSet rSet, int arg1) throws SQLException {
			EscalationDetails noofEscMap = new EscalationDetails();
			noofEscMap.setQurter(rSet.getInt(1));
			noofEscMap.setCount(rSet.getInt(2));
			return noofEscMap;
		}
	};


}
