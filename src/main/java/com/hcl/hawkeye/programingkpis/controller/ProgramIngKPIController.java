package com.hcl.hawkeye.programingkpis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.Exceptions.IngKpiRetrievalException;
import com.hcl.hawkeye.programingkpis.DO.Result;
import com.hcl.hawkeye.programingkpis.service.ProgramIngKPIService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProgramIngKPIController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramIngKPIController.class);
	
	@Autowired
	ProgramIngKPIService piService;
	
	@RequestMapping(value="/getOperationalKpi/projectId/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getOperationKpi(@PathVariable("projectId") int projectId) {
		logger.info("Rquesting api to get the operational KPI");
		Result proDetails;
		try {
			proDetails = piService.getOperationalKpiResults(projectId);
		} catch (IngKpiRetrievalException e) {
			proDetails = new Result();
		}
		return proDetails;
	}
	
	@RequestMapping(value="/getTacticalKpi/projectId/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getTacticalKpi(@PathVariable("projectId") int projectId) {
		logger.info("Rquesting api to get the tactical KPI");
		Result proDetails;
		try {
			proDetails = piService.getTacticalKpiResults(projectId);
		} catch (IngKpiRetrievalException e) {
			proDetails = new Result();
		}
		return proDetails;
	}
	
	@RequestMapping(value="/getSratigicalKpi/portFolioId/{portfolioId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getStrategicalKpi(@PathVariable("portfolioId") int portfolioId) {
		logger.info("Rquesting api to get the StrategicalKpi");
		Result proDetails;
		try {
			proDetails = piService.getStrategicalKpiResults(portfolioId);
		} catch (IngKpiRetrievalException e) {
			proDetails = new Result();
		}
		return proDetails;
	}

}
