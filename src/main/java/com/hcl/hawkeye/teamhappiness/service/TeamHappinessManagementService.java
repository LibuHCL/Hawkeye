/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.service;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;

/**
 * @author HCL
 *
 */
public interface TeamHappinessManagementService {
	TeamHappiness capHappinessDetails(TeamHappiness teamHappiness);
	Graph getHappinessPerQtAtProject(int projectId, int teamYear);
}
