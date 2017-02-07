package com.hcl.hawkeye.escalationmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.escalationmanagement.service.EscalationManagementService;
import com.hcl.hawkeye.portfolio.DO.Project;

@RestController
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
	
	@RequestMapping(value = "/noOfEscPerQtProject", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EscalationDetails>  noOfEscPerQtProject(@RequestBody Escalation esc) {
		
		logger.info("Inside noOfEscAtProject method in Controller:"+esc.getProjId());
		EscalationDetails noofEscalations= escMgmtService.noOfEscAtProject(esc.getProjId());
		
		logger.info("Escalation Details recieved: " +noofEscalations);
		
		return new ResponseEntity<EscalationDetails>(noofEscalations, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/noOfEscPerQtAtProgram", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EscalationDetails>  noOfEscPerQtAtProgram(@RequestBody Project proj) {
		
		logger.info("Inside noOfEscPerQtAtProfile method in Controller:"+proj.getProgId());
		EscalationDetails noofEscalations= escMgmtService.noOfEscPerQtAtProgram(proj.getProgId());
		
		logger.info("Escalation Details recieved: " +noofEscalations);
		
		return new ResponseEntity<EscalationDetails>(noofEscalations, HttpStatus.CREATED);
	}
	
	

}
