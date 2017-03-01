package com.hcl.hawkeye.batch.jira.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardValues;


public class JiraDashBoardReader implements ItemReader<DashBoardDetails>{
	
	private static final Logger logger = LoggerFactory.getLogger(JiraDashBoardReader.class);

	@Autowired
	ProjectManagementDAO pmDao;
	
	DashBoardDetails dbDetails = null;
	boolean val = true;
	int value = 0;
	List<DashBoardValues> dVals = new ArrayList<>();
	
	@Override
	public DashBoardDetails read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started gettign the JIra details using Spring ItemReader");
		DashBoardDetails dBoardDetails = new DashBoardDetails();
		if (val) {
			dBoardDetails = pmDao.getDashBoard(value);
			if (!dBoardDetails.isLast()) {
				dVals.addAll(dBoardDetails.getValues());
				value = dBoardDetails.getMaxResults()+1;
				this.read();
			} else {
				val = false;
			}
			if (null == dbDetails) {
				dVals.addAll(dBoardDetails.getValues());
				dbDetails = new DashBoardDetails();
				dbDetails.setValues(dVals);
			} 
			return dbDetails;
		} else {
			return null; 
		}
	}
	
}
