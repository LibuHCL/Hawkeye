package com.hcl.hawkeye.portfolio.DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;
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
	public void addProgramsToPortfolio(List<Program> progList) {
		
		logger.info("Inside addProgramsToPortfolio method in PortfolioManagementDAOImpl");
		String sql_update ="UPDATE PROGRAM SET PORTFOLIO_ID=? where PROGRAMID=?";	
		for (int i = 0; i < progList.size(); i += INSERT_BATCH_SIZE) {
			 
			final List<Program> batchList 
			= progList.subList(i, i+ INSERT_BATCH_SIZE > progList.size() ? progList.size() : i+ INSERT_BATCH_SIZE);
			logger.info(" batchList size =="+ batchList.size());
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
		}
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

}
