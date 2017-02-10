/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service;

import java.util.List;

import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;

/**
 * @author HCL
 *
 */
public interface SonarMetricsManagementService {

	List<SonarMetrics> getSonarMetricsData(Integer projectId);
}
