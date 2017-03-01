package com.hcl.hawkeye.batch.jira.core;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.hcl.hawkeye.portfolio.DO.ProgramInfo;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;


public class JiraDashBoardReader implements ItemReader<List<DashBoardValues>>{
	
	private static final Logger logger = LoggerFactory.getLogger(JiraDashBoardReader.class);

	@Autowired
	ProjectManagementDAO pmDao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	JiraBatchUpdateDAO jbDAO;
	
	boolean val = true;
	List<DashBoardValues> dVals = new ArrayList<>();
	
	@Override
	public List<DashBoardValues> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started gettign the JIra details using Spring ItemReader");
	
		// Get projects from PROJECT table.
		List<String> projUrlList =jbDAO.getProjects();		
		
		DashBoardValues dBoardDetails = new DashBoardValues();
		if (val) {
			for(String url:projUrlList){
				dBoardDetails = pmDao.getDashBoard(url);
				dVals.add(dBoardDetails);				
			}
			val=false;
			return dVals;
		} else {
			return null; 
		}
	}	
	
}
