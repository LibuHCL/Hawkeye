package com.hcl.hawkeye.metric.DAO;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.MetricDataDO.MetricConfiguration;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;

public interface MetricDataDAO {

	MetricDataDO getMetricGraph(String screenname);
	
	List<MetricDataDO> getNumberofMetricName(String screenName);
	
	Map<String, String> getMetricsDetail(String screenName);
	
	MetricConfiguration createMetricConfig(MetricConfiguration metricConfig);
	
	List<MetricDataDO> getMetricscreenDetail();

	List<MetricDataDO> getMetricData();
}
