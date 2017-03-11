package com.hcl.hawkeye.batch.coderepo.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

import com.hcl.hawkeye.batch.coderepo.DAO.GitBatchDAO;
import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;

public class GitCommitItemReader implements ItemReader<List<CodeManagementRepo>> {
	private static final Logger logger = LoggerFactory.getLogger(GitCommitItemReader.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	Environment env;

	@Autowired
	GitBatchDAO gitBatchDaoImpl;

	boolean stepThrough = true;

	@Override
	public List<CodeManagementRepo> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("Started getting the Git Config details using Spring ItemReader");
		if (stepThrough) {
			List<CodeManagementRepo> codeManagementRepo = gitBatchDaoImpl.getAllCommitData();
			return codeManagementRepo;
		}
		stepThrough = false;
		return null;
	}

}
