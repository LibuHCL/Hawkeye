/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service;

import java.util.Map;

import com.hcl.hawkeye.sonarmetrics.DO.Graph;

/**
 * @author HCL
 *
 */
public interface SonarMetricsManagementService {

	Map <String, Graph> getSonarMetricsData(Integer projectId);
}
