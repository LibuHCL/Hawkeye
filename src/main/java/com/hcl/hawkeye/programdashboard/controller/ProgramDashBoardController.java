/**
 * 
 */
package com.hcl.hawkeye.programdashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.programdashboard.DO.ProgramDashBoard;
import com.hcl.hawkeye.programdashboard.service.ProgramDashBoardService;

@RestController
public class ProgramDashBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramDashBoardController.class);
	
	@Autowired
	ProgramDashBoardService programDashBoardService;
	
	@RequestMapping(value ="/getProgramDashBoard/{programId}", method = RequestMethod.GET, produces = "application/json")
	public ProgramDashBoard getProgramDashBoard(@PathVariable("programId") int programId){
		logger.info("Inside getProgramDashBoard ProgramDashBoardController:" + programId);
		ProgramDashBoard programDashBoard = programDashBoardService.getProgramDashBoard(programId);
		logger.info("getProgramDashBoard successfully: " +programDashBoard);
		return programDashBoard;
		
		
	}

}
