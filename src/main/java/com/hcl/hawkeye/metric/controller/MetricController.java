package com.hcl.hawkeye.metric.controller;

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

import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.metric.service.MetricDataService;


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
    @RequestMapping(value = "/getMetricGraph/{screenname}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<MetricDataDO>  getMetricType(@PathVariable("screenname") String screenname){
    	
    	logger.info("Inside getMetricType method in Controller:"+screenname);
    	MetricDataDO metricdata = merticdataservice.getMetricGraph(screenname);
    	logger.info("screenname Details recieved: " + metricdata);
    	return new ResponseEntity<MetricDataDO> (metricdata, HttpStatus.CREATED);
    	
    }
      
    
  
	 
}
