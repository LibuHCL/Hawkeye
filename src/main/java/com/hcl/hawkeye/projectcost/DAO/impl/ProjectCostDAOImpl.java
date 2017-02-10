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
import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.portfolio.DO.PortfolioInfo;
import com.hcl.hawkeye.projectcost.DAO.ProjectCostDAO;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.resourcemanagement.DO.ProgramResourceCount;
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

		ProjectCostDetails projectCost = null;
		try {
			projectCost = (ProjectCostDetails) jdbcTemplate.queryForObject(
					PROJECT_COST_WITH_ID_SQL, projectCostRowMapper);
		} catch (Exception e) {
			
		}

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
	
	@Override
	public PortfolioDashboard getAllPortfolioDetails(){
		
		PortfolioDashboard portfolioDashboard = new PortfolioDashboard();
		String sql = "SELECT quarter, yr, SUM(ACTUAL_COST) actual_cost, SUM(PLANNED_COST) planned_cost, SUM(ROI) roi FROM ("+
  "SELECT QUARTER('2014-01-01')  quarter union SELECT QUARTER('2014-04-01')  quarter union "+
  "SELECT QUARTER('2014-08-01')  quarter union SELECT QUARTER('2014-12-01')  quarter) QRTR_T LEFT JOIN ("+
   "SELECT QUARTER(CAPTURE_DATE) Quater, YEAR(CAPTURE_DATE) yr, ACTUAL_COST, PLANNED_COST, ROI from PROJECT_COST where PROJECT_ID in(select distinct(PROJECT_ID) from PROJECT_COST,PROJECT, PROGRAM, PORTFOLIO"+ 
" where PROJECT_COST.PROJECT_ID = PROJECT.PROJECTID and PROJECT.PROGRAM_ID = PROGRAM.PROGRAMID and "+
"PROGRAM.PORTFOLIO_ID=1)) costs on Quater = quarter group by quarter";
		List<PortfolioInfo> resultsList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PortfolioInfo>(PortfolioInfo.class));
		return portfolioDashboard;
		
	}
	
}
