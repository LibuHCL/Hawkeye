package com.hcl.hawkeye.portfolio.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;

@Service
public class PortfolioManagementServiceImpl implements PortfolioManagementService {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementServiceImpl.class);

	@Autowired
	PortfolioManagementDAO portfolioDAO;
	
	@Override
	public Cost getConsolidatedPortfolioData() {
		logger.info("Request in getConsolidatedPortfolioData of PortfolioServiceImpl");
		Cost cost = null;
		cost = portfolioDAO.getPortfolioData();
		return cost;
	}

}
