package com.hcl.hawkeye.portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;

@RestController
public class PortfolioController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	
	@Autowired
	public PortfolioManagementService portfolioService;
	
	@RequestMapping(value="/getPortfolioData", method = RequestMethod.GET)
	@ResponseBody
	public Cost getPortfolioData() {
		logger.info("Requested to get the portfolio information");
		Cost cost = null;
		cost = portfolioService.getConsolidatedPortfolioData();
		return cost;
	}

}
