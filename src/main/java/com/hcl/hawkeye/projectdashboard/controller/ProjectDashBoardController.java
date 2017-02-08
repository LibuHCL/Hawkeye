package com.hcl.hawkeye.projectdashboard.controller;

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

import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.projectdashboard.DO.ProjectDashBoard;
import com.hcl.hawkeye.projectdashboard.service.ProjectDashBoardService;

@RestController
public class ProjectDashBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDashBoardController.class);
	
	@Autowired
	ProjectDashBoardService projDashBoardService;
	
	// To add Portfolio
		@RequestMapping(value = "/getProjDashBoard", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<ProjectDashBoard>  getProjDashBoard(@RequestBody Project proj) {
			
			logger.info("Inside getProjDashBoard ProjectDashBoardController:"+proj.getProgId());
			ProjectDashBoard projDashBoard= projDashBoardService.getProjectDashBoard(proj);
			logger.info("getProjDashBoard successfully: " +projDashBoard);
			return new ResponseEntity<ProjectDashBoard>(projDashBoard,HttpStatus.CREATED);
		}

}
