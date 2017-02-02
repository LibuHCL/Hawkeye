package com.ing.hawkeye.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ing.hawkeye.DAO.PortfolioManagementDAO;
import com.ing.hawkeye.portfolio.data.Cost;
import com.ing.hawkeye.service.PortfolioManagementService;

@Component(value = "PortfolioService")
public class PortfolioManagementServiceImpl implements PortfolioManagementService {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementServiceImpl.class);

	@Resource(name="PortfolioDAO")
	PortfolioManagementDAO portfolioDAO;
	
	@Override
	public Cost getConsolidatedPortfolioData() {
		logger.info("Request in getConsolidatedPortfolioData of PortfolioServiceImpl");
		
		Cost cost = null;
		try {
			cost = portfolioDAO.getPortfolioData();
		} catch (Exception e) {
			logger.error("Exception raised - ", e);
		}
		
		return cost;
	}

}
