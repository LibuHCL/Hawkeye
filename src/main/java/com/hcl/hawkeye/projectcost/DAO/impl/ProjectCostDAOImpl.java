package com.hcl.hawkeye.projectcost.DAO.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.portfolio.DO.PortfolioInfo;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.projectcost.DAO.ProjectCostDAO;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.utils.HawkEyeConstants;
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

		String PROJECT_COST_WITH_ID_SQL = "SELECT * FROM PROJECT_COST WHERE PROJECT_ID = " + projectID;

		ProjectCostDetails projectCost = null;
		try {
			projectCost = (ProjectCostDetails) jdbcTemplate.queryForObject(PROJECT_COST_WITH_ID_SQL,
					projectCostRowMapper);
		} catch (Exception e) {

		}

		logger.info("Project cost details are successfully fetched for project with ID: " + projectID);
		return projectCost;
	}

	RowMapper<ProjectCostDetails> projectCostRowMapper = new RowMapper<ProjectCostDetails>() {

		@Override
		public ProjectCostDetails mapRow(ResultSet rSet, int arg1) throws SQLException {
			ProjectCostDetails cost = new ProjectCostDetails();
			cost.setProjectID(new BigInteger(rSet.getString("project_id")));
			cost.setPlannedCost(rSet.getDouble("planned_cost"));
			cost.setActualCost(rSet.getDouble("actual_cost"));
			cost.setCaptureDate(rSet.getTimestamp("capture_date"));
			cost.setRoi(rSet.getInt("roi"));
			return cost;
		}
	};

	@Override
	public List<PortfolioInfo> getAllPortfolioDetails() {

		List<PortfolioInfo> portfolioList = new ArrayList<PortfolioInfo>();
		String portfolioListQuery = "select PORTFOLIO_ID from PROGRAM where PROGRAMID IN (select PROGRAM_ID from PROJECT where PROJECTID in (select distinct(PROJECTID) from PROJECT_COST))";
		List<Integer> portfolioIdsList = jdbcTemplate.queryForList(portfolioListQuery, Integer.class);
		if (0 != portfolioIdsList.size()) {
			for (int i = 0; i < portfolioIdsList.size(); i++) {
				
				String sql ="SELECT YEAR(CAPTURE_DATE) yr, ROUND(sum(ACTUAL_COST),2) actual_cost, ROUND(sum(PLANNED_COST),2) planned_cost," 
				+" ROUND(AVG(ROI),2) roi from PROJECT_COST where PROJECT_ID in(select distinct(proj.PROJECTID) from PROJECT proj, PROGRAM prog "
				+"where  proj.PROGRAM_ID = prog.PROGRAMID and prog.PORTFOLIO_ID="+portfolioIdsList.get(i)+") GROUP BY YEAR(CAPTURE_DATE) ORDER BY YEAR(CAPTURE_DATE)";
				/*String sql = "SELECT QUARTER(CAPTURE_DATE) Quarter, YEAR(CAPTURE_DATE) yr, sum(ACTUAL_COST) actual_cost, sum(PLANNED_COST) planned_cost, sum(ROI) roi from PROJECT_COST where PROJECT_ID in(select distinct(PROJECT_ID) from PROJECT_COST,PROJECT, PROGRAM, PORTFOLIO "
						+ "where PROJECT_COST.PROJECT_ID = PROJECT.PROJECTID and PROJECT.PROGRAM_ID = PROGRAM.PROGRAMID and "
						+ "PROGRAM.PORTFOLIO_ID=" + portfolioIdsList.get(i) + ")";*/
				List<Map<String, Object>> resultsList = jdbcTemplate.queryForList(sql);
				for (Map<String, Object> row : resultsList) {
					PortfolioInfo info = new PortfolioInfo();
					info.setActualCost((double) row.get("actual_cost"));
					info.setPlannedCost((double) row.get("planned_cost"));
					//info.setQuarter((Long) row.get("Quarter"));
					info.setRoi((double) row.get("roi"));
					info.setYear((Integer) row.get("yr"));
					info.setPortfolioId(portfolioIdsList.get(i));
					portfolioList.add(info);
				}
			}
		}
		logger.info("Portfolio details fetched successfully");
		return portfolioList;

	}
	
	@Override
	public Map<Integer, Integer> getProjectCostForProjects(List<Integer> projIdList) {
		logger.info("Inside getProjectCost method in ProjectCostDAOImpl::getProjectCost() ");

		//List<Integer> projIdList = new ArrayList<Integer>();
		Map<Integer,Integer> projCostMap = new HashMap<Integer, Integer>();
			
		String list="";
		for (Integer i : projIdList){
			list=list+i+",";
		}
		
		list = list.substring(0,list.lastIndexOf(","));
		try {
		    String PROJECT_COST_WITH_ID_SQL = "SELECT * FROM PROJECT_COST WHERE PROJECT_ID IN ("+list+") ";	
			List<Map<String,Object>> costList= jdbcTemplate.queryForList(PROJECT_COST_WITH_ID_SQL);
			if(costList  != null && costList.size() >0){
				for (Map<String, Object> row : costList) {
					Double plannedCost =(Double) row.get("PLANNED_COST");
					Double actualCost =(Double) row.get("ACTUAL_COST");
					int ragStatus;
					
					if(plannedCost== null || actualCost== null){
						ragStatus =HawkEyeConstants.GREEN;
						
					}
					ragStatus =HawkEyeUtils.getRAGStatus((int) ((actualCost/plannedCost)*100));
					projCostMap.put( Integer.parseInt((row.get("PROJECT_ID")).toString()),ragStatus);
					
		        } 
			}
		} catch (Exception e) {

		}
		
		


		logger.info("Project cost details are successfully fetched for project with ID: " );
		return projCostMap;
	}

}
