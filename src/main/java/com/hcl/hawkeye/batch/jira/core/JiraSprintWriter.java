package com.hcl.hawkeye.batch.jira.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;

public class JiraSprintWriter implements ItemWriter<DashBoardValues>{

	private static final Logger logger = LoggerFactory.getLogger(JiraSprintWriter.class);
	@Override
	public void write(List<? extends DashBoardValues> arg0) throws Exception {
		logger.info("FirstOne : {}", arg0.get(0).getSelf());
	}

}
