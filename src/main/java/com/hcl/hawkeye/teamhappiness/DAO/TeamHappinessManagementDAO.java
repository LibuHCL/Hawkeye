/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DAO;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;

public interface TeamHappinessManagementDAO {
	TeamHappiness capHappinessDetails(TeamHappiness happiness);
	Graph getHappinessAverageByProject(int projectId, int teamYear);
}
