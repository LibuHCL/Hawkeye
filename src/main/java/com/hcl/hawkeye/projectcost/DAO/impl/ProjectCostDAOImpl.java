package com.hcl.hawkeye.projectcost.DAO.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.projectcost.DAO.ProjectCostDAO;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Repository
public class ProjectCostDAOImpl implements ProjectCostDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProjectCostDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ProjectCostDetails addProjectCostDetails(ProjectCostDetails cost) {
		logger.info(
				"Inside addProjectCostDetails method in ProjectCostDAOImpl::addProjectCostDetails(ProjectCostDetails cost) "
						+ cost.getProjectID());

		String projectCostDetailsInsert_SQL = "INSERT INTO PROJECT_COST(PROJECTID, PLANNED_COST, ACTUAL_COST) VALUES(?, ?, ?)";

		jdbcTemplate.update(projectCostDetailsInsert_SQL,
				new Object[] { cost.getProjectID(), cost.getPlannedCost(), cost.getActualCost() });

		logger.info("Project cost details added with project id :" + cost.getProjectID());

		return HawkEyeUtils.populateProjectCostWithProjectID(cost, cost.getProjectID());
	}

	@Override
	public List<ProjectCostDetails> getAllProjectCost() {
		logger.info("Inside getAllProjectCost method in ProjectCostDAOImpl::getAllProjectCost()");

		String ALL_PROJECT_COST_SQL = "SELECT * FROM PROJECT_COST";

		List<ProjectCostDetails> allProjectCost = jdbcTemplate.query(ALL_PROJECT_COST_SQL,
				new BeanPropertyRowMapper<ProjectCostDetails>(ProjectCostDetails.class));

		logger.info("All project cost details are successfully fetched.");
		return allProjectCost;
	}

	@Override
	public ProjectCostDetails getProjectCost(int projectID) {
		logger.info("Inside getProjectCost method in ProjectCostDAOImpl::getProjectCost() " + projectID);

		String PROJECT_COST_WITH_ID_SQL = "SELECT * FROM PROJECT_COST WHERE PROJECTID = " + projectID;

		ProjectCostDetails projectCost = jdbcTemplate.queryForObject(PROJECT_COST_WITH_ID_SQL, projectCostRowMapper);

		logger.info("Project cost details are successfully fetched for project with ID: " + projectID);
		return projectCost;
	}

	RowMapper<ProjectCostDetails> projectCostRowMapper = new RowMapper<ProjectCostDetails>() {

		@Override
		public ProjectCostDetails mapRow(ResultSet rSet, int arg1) throws SQLException {
			ProjectCostDetails cost = new ProjectCostDetails();
			cost.setProjectID(new BigInteger(rSet.getString("projectid")));
			cost.setPlannedCost(rSet.getDouble("planned_cost"));
			cost.setActualCost(rSet.getDouble("actual_cost"));
			cost.setCaptureDate(rSet.getTimestamp("capture_date"));;
			return cost;
		}
	};
	
}
