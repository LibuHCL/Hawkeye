package com.hcl.hawkeye.batch.codequality.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.batch.jira.DO.SprintIssues;
import com.hcl.hawkeye.codequality.DAO.CodeQualityDAO;
import com.hcl.hawkeye.codequality.DO.Msr;
import com.hcl.hawkeye.codequality.service.CodeQualityService;
import com.hcl.hawkeye.codequality.DO.Resource;

@Configuration
@PropertySource("classpath:config.properties")
public class CodeQualityWriter implements ItemWriter<List<Resource>>{
	
	private static final Logger logger = LoggerFactory.getLogger(CodeQualityWriter.class);

	@Autowired
	CodeQualityService codeQualityService;
	
	@Autowired
	Environment env;
	
	List<Resource> resources = new ArrayList<>();
	
	List<String>  metricsList = new ArrayList<String>();
	
	@Override
	public void write(List<? extends List<Resource>> resources) throws Exception {
		logger.info("Requested to insert the details : {}", resources.size());		
		for (List<Resource> resource : resources) {
			codeQualityService.insertQualityDetails(resource);
		}		
	}

}

