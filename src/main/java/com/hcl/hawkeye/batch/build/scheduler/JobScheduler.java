/**
 * 
 */
package com.hcl.hawkeye.batch.build.scheduler;

import java.util.Date;

/**
 * @author Libu
 *
 */
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
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job buildJob;

	private JobExecution execution;

	public void run() {
		try {

			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();
			execution = jobLauncher.run(buildJob, param);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
