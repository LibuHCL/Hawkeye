package com.hcl.hawkeye.metric.service;

import java.util.List;

import com.hcl.hawkeye.MetricDataDO.MetricDataDO;

public interface MetricDataService {

	
	MetricDataDO getMetricGraph(int companyid, String screenname);

	List<MetricDataDO> getNumberofMetricName(String screenName);

	

}
