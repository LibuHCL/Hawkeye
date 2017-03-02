package com.hcl.hawkeye.batch.jira.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.batch.jira.service.JiraBatchJobService;

@Service
public class JiraBatchJobServiceImpl implements JiraBatchJobService{

	private static final Logger logger = LoggerFactory.getLogger(JiraBatchJobServiceImpl.class);
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job jiraJob;
	
	@Override
	//@Scheduled(cron="${jira.spring.job.cron.expression}")
	public void runJobScheduler() {
		logger.info("Requested for batch process to get Jira data with Project Id ");
		JobParameters jobParameters = new JobParametersBuilder().addString("date", new Date().toString()).toJobParameters();
		JobExecution jobExecution = null;
		try {
			jobExecution = jobLauncher.run(jiraJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			logger.error("Exception : {}", e);
		}
		
		BatchStatus batchStatus = jobExecution.getStatus();
		logger.info("Status: {}", jobExecution.getStatus());
		logger.info("BatchStatus : {}", batchStatus.isUnsuccessful());
		logger.info("BatchStatus : {}", batchStatus.isRunning());
		
		if (jobExecution.getStatus().equals("COMPLETED")) {
			jobExecution.stop();
		}
		
	}

}
