package com.hcl.hawkeye.programmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProgramManagementController {
	private static final Logger logger = LoggerFactory.getLogger(ProgramManagementController.class);
	
	@Autowired
	ProgramManagementService ProgramMangementService ; // Service to manage Project management
	
	
	@RequestMapping(value="/addprogram", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Program> addProgram(@RequestBody Program program) {
		logger.info("Inside addProgram method in Controller: "+program.getProgramId());
		Program progInsert = ProgramMangementService.addProgram(program);
		logger.info("portfolio inserted successfully: " +progInsert.getProgramId());
		return new ResponseEntity<Program>(progInsert, HttpStatus.CREATED);
	}
	
	// to add projects to program
	@RequestMapping(value = "/addproject", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Project> addProjsToProgram(@RequestBody Project project) {
		logger.info("Inside addProjsToProgram method in Controller");
		Project projInsert = ProgramMangementService.addProjectsToProgram(project);
		return new ResponseEntity<Project>(projInsert,HttpStatus.CREATED);
	}
	
	// get no.of programs in a quarter	
	@RequestMapping(value = "/noofprojects", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Project>> noofProjectsInQuarter() {
		logger.info("Inside noOfProgramsInQuarter method in Controller");
		List<Project> escDet = ProgramMangementService.noOfProjectsInQuarter();
		return new ResponseEntity<List<Project>>(escDet,HttpStatus.CREATED);
	}

}
