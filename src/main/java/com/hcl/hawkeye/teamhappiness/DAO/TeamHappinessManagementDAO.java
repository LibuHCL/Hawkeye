/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DAO;

import java.util.HashMap;

import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;

public interface TeamHappinessManagementDAO {
	TeamHappiness capHappinessDetails(TeamHappiness happiness);
	HashMap<String, Double> getHappinessAverageByProject(String projectId, String teamYear);
}
