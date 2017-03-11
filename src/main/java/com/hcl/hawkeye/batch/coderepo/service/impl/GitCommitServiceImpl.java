package com.hcl.hawkeye.batch.coderepo.service.impl;

import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.batch.coderepo.DAO.GitBatchDAO;
import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;
import com.hcl.hawkeye.batch.coderepo.service.GitCommitService;

@Service
@Configuration
@PropertySource("classpath:config.properties")
public class GitCommitServiceImpl implements GitCommitService {

	private static final Logger logger = LoggerFactory.getLogger(GitCommitServiceImpl.class);

	@Autowired
	GitBatchDAO gitBatchDaoImpl;

	@Autowired
	Environment env;

	@Override
	public boolean insertGitCommitDetails(List<CodeManagementRepo> codeManagementRepo) {
		logger.info("Requested to inserted the GIT batch from data into DB of size: {}", codeManagementRepo.size());
		boolean status = false;
		for (CodeManagementRepo resource : codeManagementRepo) {
			try {
				logger.info("The GIT batch from data into DB of name: {}", resource.getId());
				status = gitBatchDaoImpl.insertGitCommitData(codeManagementRepo);
				return status;
			} catch (Exception e) {
				logger.info("unable to insert the data");
			}

		}
		return status;

	}

}
