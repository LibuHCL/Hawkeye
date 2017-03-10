package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
import com.hcl.hawkeye.batch.jira.DO.ProjectToolMapping;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;


public class JiraDashBoardReader implements ItemReader<List<DashBoardValues>>{
	
	private static final Logger logger = LoggerFactory.getLogger(JiraDashBoardReader.class);

	@Autowired
	ProjectManagementDAO pmDao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	@Autowired
	MessageSource messageSource;
	
	boolean stepThrough = true;
	List<DashBoardValues> dVals = new ArrayList<>();
	
	@Override
	public List<DashBoardValues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started gettign the JIra details using Spring ItemReader");
	
		// Get projects from PROJECT table.
		List<ProjectToolMapping> projToolList =jbDAO.getProjects();
		
		DashBoardValues dBoardDetails = new DashBoardValues();
		if (stepThrough) {
			for (ProjectToolMapping ptm: projToolList){
				if(ptm.getProjectStatus().equals("notavailable")){
				    dBoardDetails = pmDao.getDashBoard(ptm.getUrl());
				}else{
					Locale locale=new Locale("en", "IN");
					dBoardDetails.setId(ptm.getToolProjectId());
					dBoardDetails.setSelf(messageSource.getMessage("jira.agile.rest.api.board.url", new Object[]{}, locale)+ptm.getToolProjectId());
				}
				dBoardDetails.setToolProjectId(ptm.getProjectId());
				dBoardDetails.setProjectStatus(ptm.getProjectStatus());
				dVals.add(dBoardDetails);	
			}			
			stepThrough=false;
			return dVals;
		} else {
			stepThrough=true;
			dVals = new ArrayList<>();			
		}
	return null; 	
	}
	
}
