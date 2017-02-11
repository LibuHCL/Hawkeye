package com.hcl.hawkeye.resourcemanagement.DAO.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.ResourceManagementException;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.resourcemanagement.DAO.ResourceManagementDAO;
import com.hcl.hawkeye.resourcemanagement.DO.ProgramResourceCount;
import com.hcl.hawkeye.resourcemanagement.DO.Resource;

@Repository
public class ResourceManagementDAOImpl implements ResourceManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(ResourceManagementDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public Resource getResourceData() {
		logger.info("Request to get the data of resources");
		Resource resource = null;
		String sql = "select * from RESOURCE";

		try {
			resource = jdbcTemplate.queryForObject(sql, resourceRowMapper);
		} catch (DataAccessException dae) {
			logger.error("Exception in resource retrieval");
			throw new ResourceManagementException("Exception in resource retrieval", dae);

		}
		return resource;
	}

	RowMapper<Resource> resourceRowMapper = new RowMapper<Resource>() {

		@Override
		public Resource mapRow(ResultSet rSet, int arg1) throws SQLException {
			Resource resource = new Resource();
			resource.setResourceID(rSet.getString("RESOURCEID"));
			resource.setProjectID(rSet.getString("PROJECTID"));
			resource.setFirstName(rSet.getString("FIRSTNAME"));
			resource.setLastName(rSet.getString("LASTNAME"));
			resource.setEmployeeID(rSet.getString("EMPLOYEEID"));
			resource.setCompanyID(rSet.getString("COMPANYID"));
			resource.setClientID(rSet.getString("CLIENTID"));
			resource.setEmail(rSet.getString("EMAIL"));
			resource.setPhoneNumber(rSet.getString("PHONE_NUMBER"));
			resource.setRole(rSet.getString("ROLE"));
			resource.setWorkLocation(rSet.getString("WORK_LOCATION"));
			resource.setProjectJoiningDate(rSet.getString("PROJECT_JOINING_DATE"));
			resource.setPlannedReleaseDate(rSet.getString("PLANNED_RELEASE_DATE"));
			resource.setExitDate(rSet.getString("EXIT_DATE"));
			resource.setResourceStatus("RESOURCE_STATUS");
			return resource;
		}
	};

	@Override
	public int getResourcesCount(String roleName) {
		int resourceCount = 0;
		String sql = "select count(*) from RESOURCE";
		try {
			resourceCount = jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (DataAccessException dae) {
			logger.error("Exception in resource count retrieval");
			throw new ResourceManagementException("Exception in resource count retrieval", dae);

		}
		return resourceCount;
	}

	@Override
	public void createResource(Resource resource) {
		try {
			jdbcTemplate.update(
					"INSERT INTO RESOURCE (`PROJECTID`, `FIRSTNAME`, `LASTNAME`, `EMPLOYEEID`, `COMPANYID`, "
							+ "`CLIENTID`, `EMAIL`, `PHONE_NUMBER`, `ROLE`, `WORK_LOCATION`, `PROJECT_JOINING_DATE`, `PLANNED_RELEASE_DATE`, "
							+ "`EXIT_DATE`, `RESOURCE_STATUS`)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[] { resource.getProjectID(), resource.getFirstName(), resource.getLastName(),
							resource.getEmployeeID(), resource.getCompanyID(), resource.getClientID(),
							resource.getEmail(), resource.getPhoneNumber(), resource.getRole(),
							resource.getWorkLocation(), resource.getProjectJoiningDate(),
							resource.getPlannedReleaseDate(), resource.getExitDate(), resource.getResourceStatus() });
		} catch (DataAccessException dae) {
			logger.error("Exception in resource creation");
			throw new ResourceManagementException("Exception in resource creation", dae);

		}
	}

	@Override
	public HashMap<String, Long> getResourcesCountByProject(int projectId) {
		String sql = "SELECT ROLE,COUNT(*) FROM RESOURCE WHERE PROJECTID =" + projectId + " group by role";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		HashMap<String, Long> countsList = new HashMap<>();
		for (Map<String, Object> row : list) {
			countsList.put((String) row.get("role"), (Long) row.get("count(*)"));
		}
		return countsList;
	}

	@Override
	public HashMap<Integer, BigDecimal> getResourceAttritionByQuarter(String attritionYear) {
		String sql = "SELECT QUARTER(PROJECT_JOINING_DATE) AS quarter, YEAR(PROJECT_JOINING_DATE) AS yr, "+
"(COUNT(RESOURCEID) *100/(SELECT count(*) FROM RESOURCE WHERE QUARTER(PROJECT_JOINING_DATE) = quarter)) "+ 
"AS attrition_percent FROM RESOURCE WHERE ING_AGREEMENT='N' and EXIT_DATE< PLANNED_RELEASE_DATE GROUP BY YEAR(PROJECT_JOINING_DATE),"+ 
"QUARTER(PROJECT_JOINING_DATE) ORDER BY yr DESC LIMIT 4";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		HashMap<Integer, BigDecimal> attritionList = new HashMap<>();
		for (Map<String, Object> row : list) {
			Integer quarter = (Integer) row.get("quarter");
			BigDecimal attritionRate = (BigDecimal) row.get("attrition_percent");
			attritionList.put(quarter,attritionRate);
		}
		return attritionList;
	}

	@Override
	public List<ProgramResourceCount> getResourcesCountByProgram(int programId) {
		String projectListQuery = "SELECT PROJECTID FROM PROJECT WHERE PROGRAM_ID = " + programId;
		List<Integer> projectIdList = jdbcTemplate.queryForList(projectListQuery, Integer.class);
		List<ProgramResourceCount> resourceCountList = new ArrayList<>();
		if (0 != projectIdList.size()) {
			for (int projectId : projectIdList) {
				String resourceCountListQuery = "select count(*) as count,PROJECTID, (select PROJECT_NAME from PROJECT where PROJECTID="
						+ projectId
						+ ") as project_name,WORK_LOCATION as location, RESOURCE_STATUS  from RESOURCE where PROJECTID = "
						+ projectId + " group by location,RESOURCE_STATUS";

				List<ProgramResourceCount> resultsList = jdbcTemplate.query(resourceCountListQuery,
						new BeanPropertyRowMapper<ProgramResourceCount>(ProgramResourceCount.class));
				resourceCountList.addAll(resultsList);
			}
		}
		return resourceCountList;
	}

	@Override
	public Double getResourcesPercentByPortfolio(int portfolioId) {
		String projectListQuery = "SELECT PROJECTID FROM PROJECT WHERE PROGRAM_ID IN(SELECT PROGRAMID FROM PROGRAM WHERE PORTFOLIO_ID="
				+ portfolioId + ")";
		List<Integer> projectIdList = jdbcTemplate.queryForList(projectListQuery, Integer.class);
		Double resourcePercent = 0.0;
		if (0 != projectIdList.size()) {
			String idList = projectIdList.toString();
			String idsCSV = idList.substring(1, idList.length() - 1).replace(", ", ",");
			Integer onshoreResourceCount = jdbcTemplate.queryForObject(
					"SELECT COUNT(*) FROM RESOURCE WHERE WORK_LOCATION = 'ONSITE' and PROJECTID IN (" + idsCSV + ")",
					Integer.class);
			Integer offshoreResourceCount = jdbcTemplate.queryForObject(
					"SELECT COUNT(*) FROM RESOURCE WHERE WORK_LOCATION = 'OFFSHORE' and PROJECTID IN (" + idsCSV + ")",
					Integer.class);
			resourcePercent = (double) ((offshoreResourceCount / (onshoreResourceCount + offshoreResourceCount)) * 100);
		}
		return resourcePercent;
	}
	
	@Override
	public Graph getOffshorePerQtPerProject(int projectId){
		
		Graph graph = new Graph();
		ArrayList<Double> graphData = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		String sql = "select QUARTER(PROJECT_JOINING_DATE) AS quarter, count(EMPLOYEEID) as count from RESOURCE where PROJECTID=? and WORK_LOCATION='OFFSHORE' "
				+ "group by QUARTER(PROJECT_JOINING_DATE) order by QUARTER(PROJECT_JOINING_DATE) DESC;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[] { projectId });
		
		for (Map<String, Object> row : list) {			
			String q =String.valueOf(row.get("quarter"));
			labels.add(q.equals("1")? "Q1":(q.equals("2") ? "Q2" : (q.equals("3") ? "Q3" :"Q4")));
			graphData.add( (Double.parseDouble(String.valueOf(row.get("count")))));
		}
		graph.setGraphData(graphData);
		graph.setLabels(labels);
		return graph;		
	}
	
	@Override
	public String getProgramManager(Integer programId)
	{
		String managerQuery = "SELECT CONCAT(FIRSTNAME,' ',LASTNAME) program_manager FROM RESOURCE WHERE EMPLOYEEID = (SELECT PROGRAM_MANANGER_ID FROM PROGRAM WHERE PROGRAMID="+programId+")";
		String programManagerName = (String) jdbcTemplate.queryForObject(managerQuery,String.class);
		return programManagerName;
	}
	
	@Override
	public String getProjectManager(Integer projectId)
	{
		String prjctManagerQuery = "SELECT CONCAT(FIRSTNAME,' ',LASTNAME) project_manager FROM RESOURCE WHERE EMPLOYEEID = (SELECT TECHNICAL_PROJECT_MANAGER_ID FROM PROJECT WHERE PROJECTID="+projectId+")";
		String prjctManagerName = (String) jdbcTemplate.queryForObject(prjctManagerQuery,String.class);
		return prjctManagerName;
	}
	
}
