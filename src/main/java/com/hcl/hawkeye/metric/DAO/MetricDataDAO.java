package com.hcl.hawkeye.metric.DAO;

import java.util.List;

import com.hcl.hawkeye.MetricDataDO.MetricDataDO;

public interface MetricDataDAO {

	MetricDataDO getMetricGraph(int companyid, String screenname);

	List<MetricDataDO> getNumberofMetricName(String screenName);

}
