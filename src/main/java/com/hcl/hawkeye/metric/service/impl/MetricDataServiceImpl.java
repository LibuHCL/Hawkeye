package com.hcl.hawkeye.metric.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
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

	@Override
	public Map<String, String> getMetricsDetail(String screenName) {
		return metricdao.getMetricsDetail(screenName);
	}

	@Override
	public MetricConfiguration createMetricConfig(MetricConfiguration metricConfig) {
		return metricdao.createMetricConfig(metricConfig);
	}
	@Override
	public Map<String, String> getMetricscreenDetail() {
		Map<String, String> metricsData =  metricdao.getMetricscreenDetail();
		Map<String, List<String>> dataList = new HashMap<>();
	       for (String key : metricsData.keySet()) {
	              if (!dataList.containsKey(key)) {
	                           List<String> sData = new ArrayList<>();
	                           sData.add(metricsData.get(key));
	                           dataList.put(key, sData);
	                     } else {
	                           List<String> sData = dataList.get(key);
	                           sData.add(metricsData.get(key));
	                           dataList.put(key, sData);
	                     }
	              }
		System.out.println("list data:"+dataList);
		return null;
	}

}
