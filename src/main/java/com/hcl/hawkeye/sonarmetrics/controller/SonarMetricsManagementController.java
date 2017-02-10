/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.sonarmetrics.DO.Result;
import com.hcl.hawkeye.sonarmetrics.DO.Metrics;
import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.service.SonarMetricsManagementService;

/**
 * @author HCL
 *
 */
@RestController
public class SonarMetricsManagementController {
	private static final Logger logger = LoggerFactory.getLogger(SonarMetricsManagementController.class);
	@Autowired
	public SonarMetricsManagementService sonarMetricsManagementService;

	@RequestMapping(value = "/getSonarMetricsDataByProj/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map <String, Graph>> getSonarMetricsData(@PathVariable("projectId") Integer projectId) {
		logger.info("Requested to get the valueAdd information for Program");
		logger.info("Inside noOfPrgmsPerPortFolio method in Controller");
		//List<SonarMetrics> sonarMetrics = new ArrayList<SonarMetrics>();
		Map <String, Graph> map = sonarMetricsManagementService.getSonarMetricsData(projectId);
		//logger.info("sonar metrics data successfully fetched" + map.get(0).getResult().get(0).get_programId());

		return new ResponseEntity<Map <String, Graph>>(map, HttpStatus.CREATED);
	}

}
