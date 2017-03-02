package com.hcl.hawkeye.batch.codequality.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hcl.hawkeye.batch.jira.DO.SprintIssues;
import com.hcl.hawkeye.codequality.DAO.CodeQualityDAO;
import com.hcl.hawkeye.codequality.DO.Resource;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

@Configuration
@PropertySource("classpath:config.properties")
public class CodeQualityReader implements ItemReader<List<Resource>>{

	private static final Logger logger = LoggerFactory.getLogger(CodeQualityReader.class);
	private List<ProjectValues> dashBoardVals;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CodeQualityDAO codeQualityDaoImpl;
	
	@Autowired
	Environment env;
	
	List<SprintIssues> sIssueList = new ArrayList<>();
	boolean stepThrough = true;
	
	@Override
	public List<Resource> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Locale locale=new Locale("en", "IN");
		if (stepThrough) {
				List<Resource> resources = codeQualityDaoImpl.getAllSonarProjectDetails("http://52.24.89.48/sonar");
				return resources;
			}
			stepThrough = false;
		return null;
	}

}
