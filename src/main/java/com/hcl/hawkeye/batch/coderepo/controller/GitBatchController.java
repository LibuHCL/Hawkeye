/**
 * 
 */
package com.hcl.hawkeye.batch.coderepo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitBatchController {
	private static final Logger logger = LoggerFactory.getLogger(GitBatchController.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job gitJob;

	@RequestMapping(value = "/runGitBatch", method = RequestMethod.GET)
	@ResponseBody
	public void getBatch() throws Exception {
		logger.info("Git Batch Request Intiated");
		JobParameters jobParameters = new JobParametersBuilder().addString("date", new Date().toString())
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(gitJob, jobParameters);

		BatchStatus batchStatus = jobExecution.getStatus();
		logger.info("Status: {}", jobExecution.getStatus());
		logger.info("BatchStatus : {}", batchStatus.isUnsuccessful());
		logger.info("BatchStatus : {}", batchStatus.isRunning());
	}

}
