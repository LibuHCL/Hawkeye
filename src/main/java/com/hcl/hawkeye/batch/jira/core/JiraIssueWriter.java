package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.batch.jira.DO.SprintIssues;

public class JiraIssueWriter implements ItemWriter<List<SprintIssues>>{
	
	private static final Logger logger = LoggerFactory.getLogger(JiraIssueWriter.class);

	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	List<SprintIssues> list = new ArrayList<>();
	
	@Override
	public void write(List<? extends List<SprintIssues>> sprintIssues) throws Exception {
		logger.info("Requested to insert the details : {}", sprintIssues.get(0).size());
		for (List<SprintIssues> list1 : sprintIssues) {
			list.addAll(list1);
		}
		boolean status = jbDAO.insertIssueDetails(list);
		
		if (status) {
			logger.info("Successfully Inserted");
		}
	}

}
