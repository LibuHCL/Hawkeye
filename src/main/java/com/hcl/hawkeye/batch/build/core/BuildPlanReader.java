package com.hcl.hawkeye.batch.build.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.batch.build.DO.BuildManageConfig;
import com.hcl.hawkeye.batch.build.service.BuildBatchService;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;


public class BuildPlanReader implements ItemReader<List<BuildStatisticsDetails>>{

	private static final Logger logger = LoggerFactory.getLogger(BuildPlanReader.class);

	@Autowired
	BuildBatchService buildBatchService;

	@Override
	public List<BuildStatisticsDetails> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started gettign the Build Config details using Spring ItemReader");
		List<BuildManageConfig> buildManConfList = buildBatchService.getBuildConfig();
		for(BuildManageConfig buildManConf : buildManConfList){
				buildBatchService.getRestDataFromTool(buildManConf);
		}
		return null; 
	}
}
