package com.ing.hawkeye.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ing.hawkeye.portfolio.data.Cost;
import com.ing.hawkeye.service.PortfolioManagementService;

@Controller
public class DataIntegrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataIntegrationController.class);
	
	@Resource(name="PortfolioService")
	public PortfolioManagementService portfolioService;
	
	@RequestMapping(value="/getPortfolioData", method = RequestMethod.GET)
	@ResponseBody
	public Cost getPortfolioData() {
		logger.info("Requested to get the portfolio information");
		Cost cost = null;
		try {
			cost = portfolioService.getConsolidatedPortfolioData();
		} catch (Exception e) {
			logger.error("Exception raised - ", e);
		}
		return cost;
	}

}
