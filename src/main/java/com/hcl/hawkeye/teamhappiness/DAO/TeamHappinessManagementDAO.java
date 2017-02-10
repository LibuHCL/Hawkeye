/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DAO;

import java.util.HashMap;

import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappinessDetails;

public interface TeamHappinessManagementDAO {
	TeamHappiness capHappinessDetails(TeamHappiness happiness);
	TeamHappinessDetails getHappinessAverageByProject(int projectId, int teamYear);
}
