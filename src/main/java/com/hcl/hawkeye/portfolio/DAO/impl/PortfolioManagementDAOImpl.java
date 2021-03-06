package com.hcl.hawkeye.portfolio.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.ProgramInfo;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
public class PortfolioManagementDAOImpl implements PortfolioManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementDAOImpl.class);
	
	private static final int INSERT_BATCH_SIZE = 5;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;
		
	@Override
	public Portfolio addPortfolio(Portfolio ptfolio) {
		logger.info("Inside addPortfolio method in PortfolioManagementDAOImpl"+ptfolio.getPortFolioName());
		
		String sql_insertprogram = "INSERT INTO PORTFOLIO (PORTFOLIO_NAME,CLIENT_ID,CREATION_DATE,END_DATE,PORTFOLIO_STATUS) VALUES(?,?,?,?,?)";
			
		 jdbcTemplate.update(sql_insertprogram,new Object[]{ptfolio.getPortFolioName(),
					ptfolio.getClientId(),HawkEyeUtils.getTimeStamp(ptfolio.getCreationDate()),HawkEyeUtils.getTimeStamp(ptfolio.getEndDate()),ptfolio.getStatus()});
		 logger.info("Portfolio added with portfolio id :"+ getPortfolioId());
		return HawkEyeUtils.populatePortfolioWithPortfolioId(ptfolio, getPortfolioId());
		
	}
	
	
	@Override
	public boolean addProgramsToPortfolio(List<Program> progList) {
		
		logger.info("Inside addProgramsToPortfolio method in PortfolioManagementDAOImpl");
		boolean status = false;
		String sql_update ="UPDATE PROGRAM SET PORTFOLIO_ID=? where PROGRAMID=?";	
		for (int i = 0; i < progList.size(); i += INSERT_BATCH_SIZE) {
			 
			final List<Program> batchList 
			= progList.subList(i, i+ INSERT_BATCH_SIZE > progList.size() ? progList.size() : i+ INSERT_BATCH_SIZE);
			logger.info(" batchList size =="+ batchList.size());
			try {
				jdbcTemplate.batchUpdate(sql_update,
						new BatchPreparedStatementSetter() {
							public void setValues(PreparedStatement pStmt, int j)throws SQLException {
								Program proj = batchList.get(j);
								logger.info("Program details=="+proj.getPortfolioId()+"====="+ proj.getProgramId());
								pStmt.setInt(1, proj.getPortfolioId());
								pStmt.setLong(2, proj.getProgramId());							
							}
	 
							@Override
							public int getBatchSize() {
								return batchList.size();
							}
							
						});
				status = true;  
			} catch (DataAccessException e) {
				// TODO: handle exception
				logger.error("Exception in addProjectsToProgram");
			}
					}
		return status;
	}

	@Override
	public Integer noOfPrgmsPerPortFolio(Integer portFoId) {
		logger.info("Inside noOfPrgmsPerPortFolioportFoId method in PortfolioManagementDAOImpl");
		String sql_nofProgms = "SELECT COUNT(*) FROM PROGRAM WHERE PORTFOLIO_ID=?";
		return jdbcTemplate.queryForObject(sql_nofProgms,Integer.class,new Object[]{portFoId});
	}

	private int getPortfolioId() {	
		String highestPortIdQuery="SELECT MAX(PORTFOLIO_ID) FROM PORTFOLIO";
		return jdbcTemplate.queryForObject(highestPortIdQuery, Integer.class);
		
	
	}
	
	@Override
	public String getPortfolioNameById(Integer portfolioId) {
		String portfolioQuery = "SELECT PORTFOLIO_NAME FROM PORTFOLIO WHERE PORTFOLIO_ID="+portfolioId;
		String portfolioName = (String) jdbcTemplate.queryForObject(portfolioQuery,String.class);
		return portfolioName;
	}
	
	@Override
	public Integer noOfProjectsPerPortFolio(Integer portFolioId) {
		logger.info("Inside noOfProjectsPerPortFolio method in PortfolioManagementDAOImpl");
		String sql_nofProgms = "SELECT COUNT(*) FROM PROJECT WHERE PROGRAM_ID IN (SELECT PROGRAMID FROM PROGRAM WHERE PORTFOLIO_ID=?)";
		return jdbcTemplate.queryForObject(sql_nofProgms,Integer.class,new Object[]{portFolioId});
	}


	@Override
	public List<ProgramInfo> getProgramDetailsByPortfolio(Integer portfolioId) {
		String sql = "select pgm.PROGRAM_NAME PROGRAM_NAME,count(prjct.PROJECTID) PROJECT_COUNT,pgm.CREATION_DATE START_DATE,pgm.END_DATE END_DATE,pgm.PROGRAM_STATUS PRGRM_STATUS "
				+ "from PROGRAM pgm,PROJECT prjct where prjct.PROGRAM_ID = pgm.PROGRAMID and pgm.PORTFOLIO_ID="+portfolioId;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		List<ProgramInfo> prgmsList = new ArrayList<>();
		for (Map<String, Object> row : list) {
			ProgramInfo programInfo = new ProgramInfo();
			programInfo.setProjectsCount((Long) row.get("PROJECT_COUNT"));
			programInfo.setProgramName((String)row.get("PROGRAM_NAME"));
			programInfo.setEndDate((Timestamp)row.get("END_DATE"));
			programInfo.setStartDate((Timestamp)row.get("START_DATE"));
			programInfo.setStatus((String)row.get("PRGRM_STATUS"));
			prgmsList.add(programInfo);
		}
		return prgmsList;
	}


}
