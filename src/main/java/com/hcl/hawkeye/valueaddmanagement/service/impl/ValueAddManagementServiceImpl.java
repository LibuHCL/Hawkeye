package com.hcl.hawkeye.valueaddmanagement.service.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.PortfolioCreationException;
import com.hcl.hawkeye.valueaddmanagement.DAO.ValueAddManagementDAO;
import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.service.ValueAddManagementService;

@Service
public class ValueAddManagementServiceImpl implements ValueAddManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(ValueAddManagementService.class);

	@Autowired
	ValueAddManagementDAO valueAddManagementDAO;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public ValueAdd getNumberOfValueAdd() {
		logger.info("Request in getNumberOfValueAdd of ValueAddManagementServiceImpl");
		ValueAdd valueAdd = valueAddManagementDAO.getNumbersOfValueAdd();
		return valueAdd;
	}

	@Override
	public ValueAdd getValueAddByProgram(Integer programID) {
		logger.info("Request in getNumberOfValueAddByProgram of ValueAddManagementServiceImpl");
		ValueAdd valueAdd = valueAddManagementDAO.getValueAddByProgram(programID);
		return valueAdd;
	}

	@Override
	public Value createValue(Value value) {
		logger.info("Inside createValue method in ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.createValueAdd(value);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new PortfolioCreationException(errorMsg, dae);
		}	
	}

}
