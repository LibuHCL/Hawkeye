package com.hcl.hawkeye.programingkpis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.Exceptions.IngKpiRetrievalException;
import com.hcl.hawkeye.programingkpis.DO.Result;
import com.hcl.hawkeye.programingkpis.service.ProgramIngKPIService;

@RestController
public class ProgramIngKPIController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramIngKPIController.class);
	
	@Autowired
	ProgramIngKPIService piService;
	
	@RequestMapping(value="/getOperationalKpi", method = RequestMethod.GET)
	@ResponseBody
	public Result getProjectData() {
		logger.info("Rquesting api to get the operational KPI");
		Result proDetails;
		try {
			proDetails = piService.getKpiResults();
		} catch (IngKpiRetrievalException e) {
			proDetails = new Result();
		}
		return proDetails;
	}

}
