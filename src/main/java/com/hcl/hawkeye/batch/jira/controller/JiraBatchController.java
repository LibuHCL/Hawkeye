package com.hcl.hawkeye.batch.jira.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.batch.jira.service.JiraBatchJobService;

@RestController
public class JiraBatchController {
	
	private static final Logger logger = LoggerFactory.getLogger(JiraBatchController.class);
	
	@Autowired
	JiraBatchJobService jJobService;
	
	@RequestMapping(value="/runBatch", method = RequestMethod.GET)
	@ResponseBody
	public void getBatch() throws Exception{
		logger.info("Jira Batch Request Intiated");
		jJobService.runJobScheduler();
	}

}
