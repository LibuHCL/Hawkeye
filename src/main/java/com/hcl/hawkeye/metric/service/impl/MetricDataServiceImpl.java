package com.hcl.hawkeye.metric.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.MetricDataDO.PortfolioDO;
import com.hcl.hawkeye.MetricDataDO.ProgramDO;
import com.hcl.hawkeye.MetricDataDO.ProjectDo;
import com.hcl.hawkeye.metric.DAO.MetricDataDAO;
import com.hcl.hawkeye.metric.service.MetricDataService;
import com.hcl.hawkeye.portfolio.DO.Project;

@Service
public class MetricDataServiceImpl implements MetricDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(MetricDataServiceImpl.class);
	@Autowired
	MetricDataDAO metricdao;

	@Override
	public MetricDataDO getMetricGraph(String screenname) {
		// TODO Auto-generated method stub
		logger.info("Inside getMetricGraph method in MetricDataServiceImpl");
		return metricdao.getMetricGraph(screenname);
	}

	@Override
	public List<MetricDataDO> getNumberofMetricName(String screenName) {
		// TODO Auto-generated method stub
		logger.info("Inside getNumberofMetricName method in MetricDataServiceImpl");
		return metricdao.getNumberofMetricName(screenName);
	}

	@Override
	public Map<String, String> getMetricsDetail(String screenName) {
		return metricdao.getMetricsDetail(screenName);
	}

	@Override
	public MetricConfiguration createMetricConfig(MetricConfiguration metricConfig) {
		return metricdao.createMetricConfig(metricConfig);
	}
	@Override
	public Map<String, List<String>> getMetricscreenDetail() {
		List<MetricDataDO> metricsData =  metricdao.getMetricscreenDetail();
		Map<String, List<String>> dataList = new HashMap<>();
		
		for (MetricDataDO metricDataDO : metricsData) {
			 if (!dataList.containsKey(metricDataDO.getScreen_Name())) {
                 List<String> sData = new ArrayList<>();
                 sData.add(metricDataDO.getMetric_Desc());
                 dataList.put(metricDataDO.getScreen_Name(), sData);
           } else {
                 List<String> sData = dataList.get(metricDataDO.getScreen_Name());
                 sData.add(metricDataDO.getMetric_Desc());
                 dataList.put(metricDataDO.getScreen_Name(), sData);
           }
		}
	     
		return dataList;
	}

	@Override
	public List<MetricData> getMetricData() {
		// TODO Auto-generated method stub
		return metricdao.getMetricData();
	}

	@Override
	public List<PortfolioDO> getPortfolioDetails() {
		// TODO Auto-generated method stub
		return metricdao.getPortfolioDetails();
	}

	@Override
	public Map<String, List<ProgramDO>> getProgramDetails(int portfolioID) {
		// TODO Auto-generated method stub
		List<ProgramDO> associate    = metricdao.getProgramDetails(portfolioID);
		List<ProgramDO> notassociate = metricdao.getProgramNotAssociateDetails(portfolioID);
		Map<String, List<ProgramDO>> programlist = new HashMap<>();
		programlist.put("AssociatedData", associate);
		programlist.put("NotAssociatedData", notassociate);
		return programlist;
	}

	@Override
	public List<ProgramDO> getProgramNotAssociateDetails(int portfolioID) {
		// TODO Auto-generated method stub
		return metricdao.getProgramNotAssociateDetails(portfolioID);
	}
	
	@Override
	public Map<String,List<ProjectDo>> getProjectDetails(int progrmaID) {
		
		List<ProjectDo> projectAssociate  =  metricdao.getProjectDetails(progrmaID);
		List<ProjectDo> projectNotAssociat = metricdao.getProjectNotAssociateDetails(progrmaID);
		Map<String,List<ProjectDo>>  projectList = new HashMap<>();
		projectList.put("projectAssociate", projectAssociate);
		projectList.put("projectNotAssociat", projectNotAssociat);
		// TODO Auto-generated method stub
		return projectList;
	}

	@Override
	public List<ProgramDO> getPorgramList() {
		// TODO Auto-generated method stub
		return metricdao.getPorgramList();
	}

	@Override
	public boolean addProjectsToProgram(List<Project> projectList) {
		// TODO Auto-generated method stub
		logger.info("Inside addProjectsToProgram method"+ projectList.get(0).getProgId());
		
		return metricdao.addProjectsToProgram(projectList);
	}
	

	
}
