package com.hcl.hawkeye.escalationmanagement.service.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.PortfolioCreationException;
import com.hcl.hawkeye.escalationmanagement.DAO.EscalationManagementDAO;
import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.escalationmanagement.service.EscalationManagementService;

@Service
public class EscalationManagementServiceImpl implements EscalationManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(EscalationManagementServiceImpl.class);
	
	@Autowired
	EscalationManagementDAO escMgmtDao;
	@Autowired
	MessageSource messageSource;

	@Override
	public Escalation capEscalationDetails(Escalation escalation) {
		logger.info("Inside capEscalationDetails method in EscalationManagementServiceImpl ");
		try {
			return escMgmtDao.capEscalationDetails(escalation);
		} catch (DataAccessException e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.escalation", new Object[] {}, locale);
			logger.error(errorMsg, e);			
			throw new PortfolioCreationException(errorMsg, e);
		}
	}

	@Override
	public EscalationDetails noOfEscAtProject(Integer projectId) {
		 try {
			return escMgmtDao.noOfEscAtProject(projectId);
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.noof.escalations", new Object[] {}, locale);
			logger.error(errorMsg, e);	
			throw new PortfolioCreationException(errorMsg, e);
		}
	}

}
