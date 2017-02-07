package com.hcl.hawkeye.programmanagement.DAO.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.DAO.ProgramManagementDAO;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
public class ProgramManagementDAOImpl implements ProgramManagementDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProgramManagementDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	//PROGRAM ID, PROGRAM NAME, PORTFOLIO ID, CLIENT ID, PROGRAM MANANGER ID, CREATION DATE, END DATE, STATUS (ACTIVE/INACTIVE)
	@Override
	public Program  addProgram(Program prog) {
		logger.info("Inside addProgram method in ProgramManagementDAOImpl");			
		String sql_insertprogram = "INSERT INTO PROGRAM (PROGRAM_NAME,PORTFOLIO_ID,CLIENT_ID,PROGRAM_MANANGER_ID,CREATION_DATE,END_DATE,PROGRAM_STATUS)"
				+ " VALUES(?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(sql_insertprogram,new Object[]{prog.getProgramName(),prog.getPortfolioId(),
				prog.getClientId(),prog.getProgMangerId(),HawkEyeUtils.getTimeStamp(prog.getCreationDate()),HawkEyeUtils.getTimeStamp(prog.getEndDate()),prog.getStatus()});
		logger.info("Program added with program id:"+geMaxProgId());
		return HawkEyeUtils.populateProgWithProgId(prog, geMaxProgId());
	}

	
	private int geMaxProgId() {
		
		String highestPortIdQuery  ="SELECT MAX(PROGRAMID) FROM PROGRAM";
		return jdbcTemplate.queryForObject(highestPortIdQuery, Integer.class);
	}


	@Override
	public Project addProjectsToProgram(Project proj) {
		logger.info("Inside addProjectsToProgram method in PortfolioManagementDAOImpl before insertion");
		
		String sql_insert_project = "INSERT INTO PROJECT (PROJECT_NAME,PROGRAM_ID,CLIENT_ID,VENDOR_ID,PROJECT_TYPE,SUBTYPE,"
				+ "TECHNICAL_PROJECT_MANAGER_ID,CREATION_DATE,END_DATE,PROJECT_STATUS) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(sql_insert_project,new Object[]{proj.getProjName(),proj.getProgId(),proj.getClientId(),proj.getVendorId(),
				proj.getProjType(),proj.getSubType(),proj.getTechProjectManager(),HawkEyeUtils.getTimeStamp(proj.getCreationDate()),
						HawkEyeUtils.getTimeStamp(proj.getEndDate()),proj.getStatus()});
		
		logger.info("Inside addProjectsToProgram method in PortfolioManagementDAOImpl after insertion");
		
		//jdbcTemplate.update(sql_update_project,new Object[]{proj.getProgId(),HawkEyeUtils.geMaxId("PROJECT")});
		
		return null;
	}

	@Override
	public EscalationDetails noOfProgramsInQuarter() {
		logger.info("Inside noOfProgramsInQuarter method in PortfolioManagementDAOImpl");	
		
		EscalationDetails escDet = new EscalationDetails();
			
		String sql_nofProgms = "SELECT QUARTER(CREATION_DATE) AS quarter, COUNT(PROGRAMID) AS count FROM PROGRAM"+
				" GROUP BY YEAR(CREATION_DATE), QUARTER(CREATION_DATE) ORDER BY YEAR(CREATION_DATE), QUARTER(CREATION_DATE)";
		
		List<Map<String, Object>> resultList =jdbcTemplate.queryForList(sql_nofProgms);
		
		if(resultList  != null && resultList.size() >0){
			for (Map<String, Object> row : resultList) {
				logger.info(" Qurter:"+row.get("quarter"));
	            escDet.setQuarter((Integer)row.get("quarter"));
	            escDet.setCount(Integer.valueOf(row.get("count").toString()));
	        } 
		}

		return escDet;
		
	}
}
