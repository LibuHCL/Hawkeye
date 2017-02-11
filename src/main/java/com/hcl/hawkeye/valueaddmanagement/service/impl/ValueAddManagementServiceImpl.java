package com.hcl.hawkeye.valueaddmanagement.service.impl;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.PortfolioCreationException;
import com.hcl.hawkeye.Exceptions.ValueAddDataRetrievalException;
import com.hcl.hawkeye.valueaddmanagement.DAO.ValueAddManagementDAO;
import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreationQuarterly;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueIndex;
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
	public Map<Integer, ValueIndex> getValueAddByIds(Integer portfolioId) {
		logger.info("Request in getNumberOfValueAddByProgram of ValueAddManagementServiceImpl");
		Map<Integer, ValueIndex> valueAdd = valueAddManagementDAO.getValueAddByIds(portfolioId);
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
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}	
	}
	
	@Override
	public ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId) {
		logger.info("Request in getValueAddByAcceptedIdeas of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getValueAddByAcceptedIdeas(programId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}

	@Override
	public ValueCreation getValueCreationByProgramId(Integer programId) {
		logger.info("Request in getValueCreationByProgramId of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getValueCreationByProgramId(programId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}
	
	@Override
	public ValueCreation getValueCreationByProjectId(Integer projectId) {
		logger.info("Request in getValueCreationByProjectId of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getValueCreationByProjectId(projectId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}

	@Override
	public ValueCreationQuarterly getQuarterlyValueByProjectId(Integer projectId) {
		logger.info("Request in getQuarterlyValueByProjectId of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getQuarterlyValueByProjectId(projectId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}

	@Override
	public ValueCreationQuarterly getQuarterlyValueByProgramId(Integer programId) {
		logger.info("Request in getQuarterlyValueByProgramId of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getQuarterlyValueByProgramId(programId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}

	@Override
	public ValueAddAcceptedIdeas getEconomicValueAdd(Integer programId) {
		logger.info("Request in getEconomicValueAdd of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getEconomicValueAdd(programId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}
	
	@Override
	public ValueAddAcceptedIdeas getEconomicValueAddByPortfolio(int portfolioId) {
		logger.info("Request in getEconomicValueAdd of ValueAddManagementServiceImpl");
		try {
			return valueAddManagementDAO.getEconomicValueAddByPortfolio(portfolioId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
	}
	
	
}
