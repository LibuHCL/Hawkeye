package com.hcl.hawkeye.metric.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricData;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;

public interface MetricDataService {

	MetricDataDO getMetricGraph(String screenname);
	
	List<MetricDataDO> getNumberofMetricName(String screenName);
	
	Map<String, String> getMetricsDetail(String screenName);
	
	MetricConfiguration createMetricConfig(MetricConfiguration metricConfig);
	
	Map<String, List<String>> getMetricscreenDetail();

	List<MetricData> getMetricData();

}