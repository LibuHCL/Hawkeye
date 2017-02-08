package com.hcl.hawkeye.resourcemanagement.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.ResourceManagementException;
import com.hcl.hawkeye.portfolio.DO.Resource;
import com.hcl.hawkeye.resourcemanagement.DAO.ResourceManagementDAO;

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
			resource = jdbcTemplate.queryForObject(sql,resourceRowMapper);
		}  catch (DataAccessException dae) {
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
		}  catch (DataAccessException dae) {
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
					+ "`EXIT_DATE`, `RESOURCE_STATUS`)" 
					+"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			                new Object[] { resource.getProjectID(),resource.getFirstName(),resource.getLastName(),resource.getEmployeeID(),
			                		resource.getCompanyID(),resource.getClientID(),resource.getEmail(),resource.getPhoneNumber(),resource.getRole(),
			                		resource.getWorkLocation(),resource.getProjectJoiningDate(),resource.getPlannedReleaseDate(),resource.getExitDate(),resource.getResourceStatus()
			                		});
		}  catch (DataAccessException dae) {
			logger.error("Exception in resource creation");
			throw new ResourceManagementException("Exception in resource creation", dae);
		
		}
	}

	@Override
	public HashMap<String, Long> getResourcesCountByProject(String projectId) {
		String sql = "SELECT ROLE,COUNT(*) FROM RESOURCE WHERE PROJECTID ='"+projectId+"' group by role";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		HashMap<String,Long> countsList = new HashMap<>();
		for (Map<String, Object> row : list) {
			countsList.put((String)row.get("role"), (Long)row.get("count(*)"));
		}
		return countsList;
	}

	@Override
	public HashMap<String, Long> getResourceAttritionByQuarter(String attritionYear) {
		String sql = "SELECT QUARTER(PROJECT_JOINING_DATE) AS quarter, COUNT(RESOURCEID) AS count FROM RESOURCE WHERE ING_AGREEMENT='Y' and YEAR(PROJECT_JOINING_DATE)='"+attritionYear+"' and EXIT_DATE< PLANNED_RELEASE_DATE GROUP BY YEAR(PROJECT_JOINING_DATE), QUARTER(PROJECT_JOINING_DATE) ORDER BY YEAR(PROJECT_JOINING_DATE), QUARTER(PROJECT_JOINING_DATE)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		HashMap<String,Long> attritionList = new HashMap<>();
		for (Map<String, Object> row : list) {
			attritionList.put((String)row.get("quarter"), (Long)row.get("count"));
		}
		return attritionList;
	}
}
