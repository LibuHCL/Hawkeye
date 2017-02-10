package com.hcl.hawkeye.escalationmanagement.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.service.EscalationManagementService;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.portfolio.DO.Project;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EscalationManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(EscalationManagementController.class);	
	
	@Autowired
	EscalationManagementService escMgmtService;
	
	//To capture escalation details
	@RequestMapping(value = "/capEscalation", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Escalation>  capEscalationDetails(@RequestBody Escalation escalation) {
		
		logger.info("Inside capEscalationDetails method in Controller:"+escalation.getProjId());
		Escalation escDetails= escMgmtService.capEscalationDetails(escalation);
		
		logger.info("Escalation Details recieved: " +escDetails);
		
		return new ResponseEntity<Escalation>(escDetails, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/noOfEscPerQtProject/{projId}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Graph>  noOfEscPerQtProject(@PathVariable int projId) {
		
		logger.info("Inside noOfEscAtProject method in Controller:"+projId);
		Graph escdet= escMgmtService.noOfEscAtProject(projId);
		
		logger.info("Escalation Details recieved: " +escdet);
		
		return new ResponseEntity<Graph>(escdet, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/noOfEscPerQtAtProgram", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Graph>>  noOfEscPerQtAtProgram(@RequestBody Project proj) {
		
		logger.info("Inside noOfEscPerQtAtProfile method in Controller:"+proj.getProgId());
		List<Graph> noofEscalations= escMgmtService.noOfEscPerQtAtProgram(proj.getProgId());
		
		logger.info("Escalation Details recieved: " +noofEscalations);
		
		return new ResponseEntity<List<Graph>>(noofEscalations, HttpStatus.CREATED);
	}
	
	

}
