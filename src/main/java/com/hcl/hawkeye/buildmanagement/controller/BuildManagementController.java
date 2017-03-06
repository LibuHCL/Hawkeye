package com.hcl.hawkeye.buildmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.Exceptions.IngKpiRetrievalException;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.buildmanagement.service.BuildMetricsManagementService;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.programingkpis.DO.Result;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BuildManagementController {
	private static final Logger logger = LoggerFactory.getLogger(BuildManagementController.class);

	@Autowired
	public BuildMetricsManagementService buildManagementService;

	@RequestMapping(value = "/getBuildsPerDay/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Graph> getBuildsPerDay(@PathVariable("projectId") int projectId) {

		logger.info("Inside getDetailsForLasttwoWeeks method in Controller:"+ projectId);
		Graph buildDetails = buildManagementService.getBuildsPerDay(projectId);
		return new ResponseEntity<Graph> (buildDetails, HttpStatus.CREATED);

	}
	
	/* To get Latest Build Details */
	@RequestMapping(value = "/getLatestBuildDetails/{planKey}/{projectId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity <List<BuildStatisticsDetails>> getMetricData(@PathVariable("planKey") String planKey,	@PathVariable("projectId") int projectId){
    	logger.info("Inside getLatestBuildDetails method in Controller:");
    	List<BuildStatisticsDetails> metricdata = buildManagementService.getLatestBuildDetails(planKey,projectId);
    	return new ResponseEntity<List<BuildStatisticsDetails>> (metricdata, HttpStatus.CREATED);
    	
    }
	/* To get Today Build Details */
	@RequestMapping(value = "/getTodayDetails/{planKey}/{projectId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity <List<BuildStatisticsDetails>> getTodayDetails(@PathVariable("planKey") String planKey,	@PathVariable("projectId") int projectId){
    	logger.info("Inside getTodayDetails method in Controller:");
    	List<BuildStatisticsDetails> metricdata = buildManagementService.getTodayDetails(planKey,projectId);
    	return new ResponseEntity<List<BuildStatisticsDetails>> (metricdata, HttpStatus.CREATED);
    	
    }
	@RequestMapping(value="/getAvgBuildDuration/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Graph> getAvgBuildDuration(@PathVariable("projectId") int projectId) {
		logger.info("Requesting api to get the getAvgBuildDuration");
		Graph buildDetails = buildManagementService.getAvgBuildDuration(projectId);
		return new ResponseEntity<Graph> (buildDetails, HttpStatus.CREATED);
	}
	
}
