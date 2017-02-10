/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.DAO;

import java.util.List;

import com.hcl.hawkeye.sonarmetrics.DO.SonarMetrics;
import com.hcl.hawkeye.sonarmetrics.DO.Trackers;

/**
 * @author Lenovo
 *
 */
public interface SonarMetricsManagementDAO {

	List<Trackers> getSonarMetricsData(Integer projectId);

}
