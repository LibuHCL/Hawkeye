package com.hcl.hawkeye.batch.jira.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;

public class JiraDashBoardWriter implements ItemWriter<List<DashBoardValues>> {

	private static final Logger logger = LoggerFactory.getLogger(JiraDashBoardWriter.class);

	private StepExecution stepExecution;
	
	@Override
	public void write(List<? extends List<DashBoardValues>> details) throws Exception {
		logger.info("Requested to write the data coming from the Reader");
		
		if (null != details) {
			for (List<DashBoardValues> list : details) {
				logger.info("Details are : {}",list.size());
				ExecutionContext stepContext = this.stepExecution.getExecutionContext();
				stepContext.put("someKey", list);
			}
		}
	}

	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
