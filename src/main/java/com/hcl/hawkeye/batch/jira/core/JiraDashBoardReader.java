package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hcl.hawkeye.batch.jira.DAO.JiraBatchUpdateDAO;
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
	
	boolean stepThrough = true;
	List<DashBoardValues> dVals = new ArrayList<>();
	
	@Override
	public List<DashBoardValues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started gettign the JIra details using Spring ItemReader");
	
		// Get projects from PROJECT table.
		Map<Integer,String> projToolMap =jbDAO.getProjects();
		
		DashBoardValues dBoardDetails = new DashBoardValues();
		if (stepThrough) {
			for (Entry<Integer, String> entry : projToolMap.entrySet()){
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			    dBoardDetails = pmDao.getDashBoard(entry.getValue());
			    dBoardDetails.setToolProjectId(entry.getKey());
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
