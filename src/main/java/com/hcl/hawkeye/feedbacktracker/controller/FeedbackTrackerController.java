package com.hcl.hawkeye.feedbacktracker.controller;

import java.util.List;

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


import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.feedbacktracker.service.FeedbackTrackerService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FeedbackTrackerController {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackTrackerController.class);
	
    @Autowired
    FeedbackTrackerService feedbkService;
  
    /* To Capture the feedbackTrack Details */
    @RequestMapping(value = "/feedbacktrack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> capFeedBackDetails(@RequestBody FeedbackDetails feedback){
    	
    	logger.info("Inside capFeedBackDetails method in Controller:"+feedback.getFeedbackId());
    	int feedbackdetails = feedbkService.capFeedbackTrackDetails(feedback);
    	logger.info("Feedback Details recieved: " + feedbackdetails);
    	return new ResponseEntity<Integer> (feedbackdetails, HttpStatus.CREATED);
       	
    }
    
    /* To Get the feedbackTrack Details for Project */
    @RequestMapping(value = "/getFeedbackperProject/{reporterType}/{projectId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<FeedbackDetails>  getFeedbackPerProject(@PathVariable("reporterType") String reporterType,@PathVariable("projectId") int projectId){
    	
    	logger.info("Inside getFeedbackPerQtProject method in Controller:"+reporterType+projectId);
    	FeedbackDetails feedbackdetails = feedbkService.getFeedbackPerProject(reporterType,projectId);
    	logger.info("reporterType projectid Details recieved: " + feedbackdetails);
    	return new ResponseEntity<FeedbackDetails> (feedbackdetails, HttpStatus.CREATED);
    	
    }
    
    /* To Get the feedbackTrack Details for Program */
    @RequestMapping(value = "/getFeedbackperProgram/{reporterType}/{programId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<FeedbackDetails>>  getFeedbackPerProgram(@PathVariable("reporterType") String reporterType,@PathVariable("programId") int programId){
    	
    	logger.info("Inside getFeedbackPerProgram method in Controller:"+reporterType+programId);
    	List<FeedbackDetails> feedbackdetails = feedbkService.getFeedbackPerProgram(reporterType,programId);
    	logger.info("reporterType and projectid Details recieved: " + feedbackdetails);
    	return new ResponseEntity<List<FeedbackDetails>> (feedbackdetails, HttpStatus.CREATED);
    	
    }
       
  }
