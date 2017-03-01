package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;

public class JiraBatchProcessor implements ItemProcessor<DashBoardDetails, List<DashBoardValues>>{

	private static final Logger logger = LoggerFactory.getLogger(JiraBatchProcessor.class);
	List<DashBoardValues> dbValues = new ArrayList<>();
	
	@Override
	public List<DashBoardValues> process(DashBoardDetails dbDetails) throws Exception {
		if (null != dbDetails ) {
			dbValues.addAll(dbDetails.getValues());
		}
		return dbValues;
	}

}
