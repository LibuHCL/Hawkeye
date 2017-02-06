package com.hcl.hawkeye.portfolio.service.impl;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.PortfolioCreationException;
import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;

@Service
public class PortfolioManagementServiceImpl implements PortfolioManagementService {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementServiceImpl.class);

	@Autowired
	PortfolioManagementDAO portfolioDAO;
	@Autowired
	MessageSource messageSource;
	
	@Override
	public Cost getConsolidatedPortfolioData() {
		logger.info("Request in getConsolidatedPortfolioData of PortfolioServiceImpl");
		Cost cost = null;
		cost = portfolioDAO.getPortfolioData();
		return cost;
	}
	
	@Override
	public Portfolio addPortfolio(Portfolio portfolio) {
		logger.info("Inside addPortfolio method in PortFolioMangementServiceImpl");
		
		try {
			return portfolioDAO.addPortfolio(portfolio);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.porfolio", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new PortfolioCreationException(errorMsg, dae);
		}	
	}
	
	@Override
	public void addProgramsToPortfolio(List<Program> progList) {
		logger.info("Inside addProgramsToPortfolio method in PortFolioMangementServiceImpl=="+progList.get(0).getPortfolioId());
		
		 try {
			portfolioDAO.addProgramsToPortfolio(progList);
		} catch (DataAccessException e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.addprog.porfolio", new Object[] {}, locale);
			logger.error(errorMsg, e);
		}
		
	}

	@Override
	public Integer noOfPrgmsPerPortFolio(Integer portFoId) {
		logger.info("Inside noOfPrgmsPerPortFolio method in PortFolioMangementServiceImpl: "+portFoId);
		
		try {
			return portfolioDAO.noOfPrgmsPerPortFolio(portFoId);
		} catch (DataAccessException e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.noofprogram", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new PortfolioCreationException(errorMsg, e);
		}
	}

}
