/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;

/**
 * @author HCL
 *
 */
public interface SonarMetricsManagementService {

	Map <String, Graph> getSonarMetricsData(Integer projectId);
}
