/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.teamhappiness.DAO.TeamHappinessManagementDAO;
import com.hcl.hawkeye.teamhappiness.DO.TeamHappiness;
import com.hcl.hawkeye.teamhappiness.service.TeamHappinessManagementService;

/**
 * @author SamrajT
 *
 */
@Service
public class TeamHappinessManagementServiceImpl implements TeamHappinessManagementService {
	private static final Logger logger = LoggerFactory.getLogger(TeamHappinessManagementServiceImpl.class);

	@Autowired
	TeamHappinessManagementDAO teamHappinessMgmtDao;
	@Autowired
	MessageSource messageSource;


	@Override
	public TeamHappiness capHappinessDetails(TeamHappiness teamHappiness) {
		logger.info("Inside capHappinessDetails method in TeamHappinessManagementServiceImpl ");
		return teamHappinessMgmtDao.capHappinessDetails(teamHappiness);
	}

	@Override
	public Graph getHappinessPerQtAtProject(int projectId, int teamYear) {
		logger.info("Request in getHappinessPerQtAtProject of TeamHappinessManagementServiceImpl");
		return teamHappinessMgmtDao.getHappinessAverageByProject(projectId,teamYear);
	}

}
