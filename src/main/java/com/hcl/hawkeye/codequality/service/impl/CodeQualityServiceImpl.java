package com.hcl.hawkeye.codequality.service.impl;

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

import com.hcl.hawkeye.codequality.DAO.CodeQualityDAO;
import com.hcl.hawkeye.codequality.DO.Msr;
import com.hcl.hawkeye.codequality.DO.Resource;
import com.hcl.hawkeye.codequality.controller.CodeQualityController;
import com.hcl.hawkeye.codequality.service.CodeQualityService;
import com.hcl.hawkeye.utils.HawkEyeConstants;

@Service
@Configuration
@PropertySource("classpath:config.properties")
public class CodeQualityServiceImpl implements CodeQualityService {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeQualityController.class);


	@Autowired
	CodeQualityDAO codeQualityDaoImpl;

	@Autowired
	Environment env;

	/* This method is used to get Red Amber or Green Status on Code Quality Metrics
	 * 
	 */
	@Override
	public int getCodeQualityRAGStatus() {
		int ragStatus = 0;
		String branch = env.getProperty("git.branch");
		logger.info("Branch: {}", branch);
		
		Resource resource = codeQualityDaoImpl.getSonarMetrics(branch);
		logger.info("Resource: {}", resource);

		if (resource != null) {
			Double debtRatio = extractDebtRatio(resource);
			ragStatus = calculateRAGStatus(debtRatio);
			logger.info("RAG Status: {}", ragStatus);
		}
		return ragStatus;
	}

	/** This method is used to calculate RAG Status based on thresholds
	 * @param debtRatio
	 * @return
	 */
	private int calculateRAGStatus(double debtRatio) {
		if (debtRatio > 50)
			return HawkEyeConstants.RED;
		else if (debtRatio > 10)
			return HawkEyeConstants.AMBER;

		return HawkEyeConstants.GREEN;
	}

	private Double extractDebtRatio(Resource resource) {
		String debtRatioKey = env.getProperty("sonar.sqale_debt_Ratio");
		Msr[] msr = resource.getMsr();
		Double debtRatio = new Double("0");
		for (int i = 0; i < msr.length; i++) {
			if (msr[i].getKey() != null && msr[i].getKey().equals(debtRatioKey)) {
				logger.debug("debt ratio key: {}", msr[i].getKey());
				logger.debug("debt ratio value: {}", msr[i].getVal());
				debtRatio = new Double(msr[i].getVal());
			}

		}
		return debtRatio;
	}

	@Override
	public void insertQualityDetails(List<Resource> resources) {		
		for(Resource resource: resources){
			codeQualityDaoImpl.insertCodeQuality(resource);
		}
	}

}

