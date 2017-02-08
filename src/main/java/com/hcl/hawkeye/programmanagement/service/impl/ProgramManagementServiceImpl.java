package com.hcl.hawkeye.programmanagement.service.impl;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.ProgramCreationException;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.DAO.ProgramManagementDAO;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;

@Service
public class ProgramManagementServiceImpl implements ProgramManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramManagementServiceImpl.class);
	
	@Autowired
	private ProgramManagementDAO ProgramManagementDAO;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public Program addProgram(Program prog) {
		logger.info("Inside addProgram method in ProgramManagemetServiceImpl: ");		
		
		try {
			return ProgramManagementDAO.addProgram(prog);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.create.program", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ProgramCreationException(errorMsg, dae);
		}
		
	}
	
	@Override
	public Project addProjectsToProgram(Project project) {
		logger.info("Inside addProjectsToProgram method in ProgramManagemetServiceImpl");
		try {
			return ProgramManagementDAO.addProjectsToProgram(project);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.addproj.program", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ProgramCreationException(errorMsg, dae);
		}
				
	}
	
	@Override
	public List<Project> noOfProgramsInQuarter() {
		logger.info("Inside noOfProgramsInQuarter method in ProgramManagemetServiceImpl");
		
		try {
			return ProgramManagementDAO.noOfProgramsInQuarter();
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.noofprograminquarter", new Object[] {}, locale);
			logger.error(errorMsg, dae);			
			throw new ProgramCreationException(errorMsg, dae);
		}
		
	}

	@Override
	public List<Project> getProjectsPerProgramId(int progId) {
		
		try {
			return ProgramManagementDAO.getProjectsPerProgramId(progId);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.getProjectsPerProgramId", new Object[] {progId}, locale);
			logger.error(errorMsg, dae);			
			throw new ProgramCreationException(errorMsg, dae);
		}
	}

}
