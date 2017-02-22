package com.hcl.hawkeye.metric.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.metric.DAO.MetricDataDAO;
import com.hcl.hawkeye.metric.service.MetricDataService;

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

}
