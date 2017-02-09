/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.service;

import java.util.HashMap;

import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;

/**
 * @author HCL
 *
 */
public interface TeamHappinessManagementService {
	TeamHappiness capHappinessDetails(TeamHappiness teamHappiness);
	HashMap<String,Double> getHappinessPerQtAtProject(String projectId, String teamYear);
}
