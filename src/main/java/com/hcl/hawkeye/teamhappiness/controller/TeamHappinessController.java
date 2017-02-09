/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;
import com.hcl.hawkeye.teamhappiness.service.TeamHappinessManagementService;
/**
 * @author HCL
 *
 */
@RestController
public class TeamHappinessController {
	private static final Logger logger = LoggerFactory.getLogger(TeamHappinessController.class);

	@Autowired
	TeamHappinessManagementService happinessMgmtService;

	/* Capturing happiness details */
	@RequestMapping(value = "/capHappiness", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TeamHappiness> capHappinessDetails(@RequestBody TeamHappiness teamHappiness) {

		logger.info("Inside capHappinessDetails method in Controller:" + teamHappiness.getProjectId());
		TeamHappiness happiness = happinessMgmtService.capHappinessDetails(teamHappiness);

		logger.info("Happiness Details recieved: " + happiness);

		return new ResponseEntity<TeamHappiness>(happiness, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getTeamHappiness/{projectId}-{teamYear}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public HashMap<String,Double> getTeamHappiness(@PathVariable("projectId") String projectId, @PathVariable("teamYear") String teamYear) {
		logger.info("Requested to get the happiness for project in Quarterly basis");
		HashMap<String,Double> response = happinessMgmtService.getHappinessPerQtAtProject(projectId,teamYear);
		return response;
	}
}
