package com.hcl.hawkeye.buildmanagement.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.IngKpiRetrievalException;
import com.hcl.hawkeye.buildmanagement.DAO.BuildMetricsManagementDAO;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;
import com.hcl.hawkeye.buildmanagement.service.BuildMetricsManagementService;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.programingkpis.DO.KPIType;
import com.hcl.hawkeye.programingkpis.DO.KPIValue;
import com.hcl.hawkeye.programingkpis.DO.Result;

@Service
public class BuildMetricsManagementServiceImpl implements BuildMetricsManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(BuildMetricsManagementServiceImpl.class);

	@Autowired
	BuildMetricsManagementDAO buildDAO;

	@Autowired
	Environment env;
	
	@Autowired
	MessageSource messageSource;
	

	@Override
	public Graph getBuildsPerDay(int projectId) {
		// TODO Auto-generated method stub
		return buildDAO.getBuildsPerDay(projectId);
	}


	@Override
	public List<BuildStatisticsDetails> getLatestBuildDetails(String planKey, int projectId) {
		// TODO Auto-generated method stub
		return buildDAO.getLatestBuildDetails(planKey,projectId);
	}

	@Override
	public List<BuildStatisticsDetails> getTodayDetails(String planKey, int projectId) {
		// TODO Auto-generated method stub
		return buildDAO.getTodayDetails(planKey,projectId);
	}


	@Override
	public Graph getAvgBuildDuration(int projectId) {
		// TODO Auto-generated method stub
		return buildDAO.getAvgBuildDuration(projectId);
	}


}
