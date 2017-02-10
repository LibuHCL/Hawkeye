/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.service;

import java.util.Map;

import com.hcl.hawkeye.portfolio.DO.Graph;


/**
 * @author HCL
 *
 */
public interface SonarMetricsManagementService {

	Map <String, Graph> getSonarMetricsData(Integer projectId);
}
