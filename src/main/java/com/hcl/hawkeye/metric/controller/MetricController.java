package com.hcl.hawkeye.metric.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.metric.service.MetricDataService;
import com.hcl.hawkeye.valueaddmanagement.DO.Value;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MetricController {
	
	private static final Logger logger = LoggerFactory.getLogger(MetricController.class);
	
	@Autowired
	MetricDataService merticdataservice;
	
	/* To Capture the MetricsConfiguration data Details */
	@RequestMapping(value = "/getMetricName/{screenName}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity <List<MetricDataDO>> getNumberofMetricName(@PathVariable("screenName") String screenName){
    	logger.info("Inside getNumberofMetricName method in Controller:");
    	List<MetricDataDO> metricdata = merticdataservice.getNumberofMetricName(screenName);
    	return new ResponseEntity<List<MetricDataDO>> (metricdata, HttpStatus.CREATED);
    	
    }
        
    /* To Get the feedbackTrack Details for Project */
    @RequestMapping(value = "/getMetricsDetail/{screenname}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, String>  getMetricsDetail(@PathVariable("screenname") String screenname){    	
    	logger.info("Inside getMetricType method in Controller:"+screenname);
    	Map<String, String> metricdata = merticdataservice.getMetricsDetail(screenname);
    	logger.info("screenname Details recieved: " + metricdata);
    	return metricdata;
    	
    }
      
    @RequestMapping(value = "/getMetricGraph/{screenname}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<MetricDataDO>  getMetricType(@PathVariable("screenname") String screenname){
    	
    	logger.info("Inside getMetricType method in Controller:"+screenname);
    	MetricDataDO metricdata = merticdataservice.getMetricGraph(screenname);
    	logger.info("screenname Details recieved: " + metricdata);
    	return new ResponseEntity<MetricDataDO> (metricdata, HttpStatus.CREATED);
    	
    }
  	
	@RequestMapping(value = "/createMetricData", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MetricConfiguration>  createMetricData(@RequestBody MetricConfiguration metricConfig) {
		MetricConfiguration createdMetric= merticdataservice.createMetricConfig(metricConfig);
		logger.info("Value Add inserted successfully: " +metricConfig);
		return new ResponseEntity<>(createdMetric, HttpStatus.CREATED);
	}
	 /* To Get the Metric Details for Project */
    @RequestMapping(value = "/getMetricscreenDetail", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, List<String>>  getMetricscreenDetail(){    	
    	logger.info("Inside getMetricscreenDetail method in Controller");
    	Map<String, List<String>> metricdata = merticdataservice.getMetricscreenDetail();
    	return metricdata;
    	
    }
}
