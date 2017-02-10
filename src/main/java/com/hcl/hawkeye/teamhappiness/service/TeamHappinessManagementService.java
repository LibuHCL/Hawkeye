/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.service;

import java.util.HashMap;

import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappinessDetails;

/**
 * @author HCL
 *
 */
public interface TeamHappinessManagementService {
	TeamHappiness capHappinessDetails(TeamHappiness teamHappiness);
	TeamHappinessDetails getHappinessPerQtAtProject(int projectId, int teamYear);
}
