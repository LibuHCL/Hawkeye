package com.hcl.hawkeye.projectdashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectdashboard.DO.ProjectDashBoard;
import com.hcl.hawkeye.projectdashboard.service.ProjectDashBoardService;

@RestController
public class ProjectDashBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDashBoardController.class);
	
	@Autowired
	ProjectDashBoardService projDashBoardService;
	
	// To add Portfolio
		@RequestMapping(value ="/getProjDashBoard/{progId}", method = RequestMethod.GET, produces = "application/json")
		public ResponseEntity<ProjectDashBoard>  getProjDashBoard(@PathVariable("progId") int progId) {
			
			logger.info("Inside getProjDashBoard ProjectDashBoardController:"+progId);
			ProjectDashBoard projDashBoard= projDashBoardService.getProjectDashBoard(progId);
			logger.info("getProjDashBoard successfully: " +projDashBoard);
			return new ResponseEntity<ProjectDashBoard>(projDashBoard,HttpStatus.CREATED);
			
		}

}
