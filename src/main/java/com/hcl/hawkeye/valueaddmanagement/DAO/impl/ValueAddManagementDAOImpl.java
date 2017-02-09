package com.hcl.hawkeye.valueaddmanagement.DAO.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.ValueAddDataRetrievalException;
import com.hcl.hawkeye.portfolio.DO.Result;
import com.hcl.hawkeye.utils.HawkEyeUtils;
import com.hcl.hawkeye.valueaddmanagement.DAO.ValueAddManagementDAO;
import com.hcl.hawkeye.valueaddmanagement.DO.Kpi;
import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;

@Repository
public class ValueAddManagementDAOImpl implements ValueAddManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(ValueAddManagementDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;

	@Override
	public Value createValueAdd(Value value) {
		String createValueAddQuery = "INSERT INTO VALUEADD (PROJECTID, PROGRAMID, PORTFOLIOID, DESCRIPTION, PROPOSED_DATE, VALUEADD_STATUS, STATUS_UPDATE_DATE, ECONOMIC_VALUE) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(createValueAddQuery,
				new Object[] { value.getProjectId(), value.getProgramId(), value.getPortfolioId(),
						value.getDescription(), HawkEyeUtils.getTimeStamp(value.getProposedDate()),
						value.getValueAddStatu(), HawkEyeUtils.getTimeStamp(value.getStatusUpdateDate()),
						value.getEconomicValue() });
		int valueId = getValueId();
		logger.info("Portfolio added with portfolio id :" + valueId);
		return HawkEyeUtils.populateValueId(value, valueId);
	}

	private int getValueId() {
		String highestValueIdQuery = "SELECT MAX(VALUEID) FROM VALUEADD";
		Integer id = jdbcTemplate.queryForObject(highestValueIdQuery, Integer.class);
		if (id == null) {
			return 1000;
		}
		return id;
	}

	@Override
	public ValueAdd getNumbersOfValueAdd() {
		logger.info("Request to get the data of number of Value Add");
		ValueAdd valueAdd = new ValueAdd();
		String numbersOfValueAddQuery = "SELECT PROJECTID, VALUEADD_STATUS, MONTHNAME(PROPOSED_DATE) as MON, YEAR(PROPOSED_DATE) AS YEARS, "
				+ "QUARTER(PROPOSED_DATE) AS QUARTERS,COUNT(MONTHNAME(PROPOSED_DATE))" + "FROM VALUEADD "
				+ "GROUP BY PROJECTID, VALUEADD_STATUS, MON, QUARTERS, YEARS; ";
		try {
			valueAdd = jdbcTemplate.queryForObject(numbersOfValueAddQuery, VALUEADDROWMAPPER);
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
		return valueAdd;
	}

	RowMapper<ValueAdd> VALUEADDROWMAPPER = new RowMapper<ValueAdd>() {

		@Override
		public ValueAdd mapRow(ResultSet rSet, int arg1) throws SQLException {
			ValueAdd valueAdd = new ValueAdd();
			Kpi kpi = new Kpi();
			Result result = new Result();
			return valueAdd;
		}
	};

	@Override
	public ValueAdd getValueAddByIds(Integer programId, Integer portfolioId) {
		logger.info("Request to get the data of number of Value Add at program level");
		ValueAdd valueAdd = new ValueAdd();

		try {
			if (0 != programId) {
				String numbersOfValueAddProgQuery = "SELECT PROGRAMID, VALUEADD_STATUS, MONTHNAME(PROPOSED_DATE) as MON, COUNT(1) AS TOTAL "
						+ "FROM VALUEADD " + "WHERE " + "PROGRAMID = ? "
						+ "AND PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR) "
						+ "GROUP BY PROJECTID, VALUEADD_STATUS, MON; ";
				valueAdd = jdbcTemplate.queryForObject(numbersOfValueAddProgQuery, VALUEADDROWMAPPER,
						new Object[] { programId });
			} else {
				String numbersOfValueAddProgQuery = "SELECT PORTFOLIOID, VALUEADD_STATUS, MONTHNAME(PROPOSED_DATE) as MON, COUNT(1) AS TOTAL "
						+ "FROM VALUEADD " + "WHERE " + "PORTFOLIOID = ? "
						+ "AND PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR) "
						+ "GROUP BY PORTFOLIOID, VALUEADD_STATUS, MON; ";
				valueAdd = jdbcTemplate.queryForObject(numbersOfValueAddProgQuery, VALUEADDROWMAPPER,
						new Object[] { portfolioId });
			}
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
		return valueAdd;
	}

	@Override
	public ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId) {
		ValueAddAcceptedIdeas valueAddAcceptedIdeas = new ValueAddAcceptedIdeas();
		logger.info("Request to get Accepted ideas for program " + programId);
		String valueAddAcceptedQuery = "SELECT PROGRAMID, " + " QUARTER(PROPOSED_DATE) AS QUARTERS, "
				+ " SUM(CASE WHEN VALUEADD_STATUS = 'Implemented' THEN 1 ELSE 0 END)/SUM(CASE WHEN VALUEADD_STATUS != 'Implemented' THEN 1 ELSE 0 END) AS GRAPHDATA FROM VALUEADD "
				+ " WHERE YEAR(PROPOSED_DATE) = YEAR(NOW()) AND " + " PROGRAMID = ? " + "	GROUP BY PROJECTID; ";
		/*
		 * valueAddAcceptedIdeas =
		 * jdbcTemplate.queryForObject(valueAddAcceptedQuery,
		 * VALUEADDACCEPTEDROWMAPPER, new Object[] { programId });
		 */
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddAcceptedQuery, new Object[] { programId });
		ArrayList<Double> graphdata = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		for (Map<String, Object> row : list) {
			graphdata.add(((BigDecimal) row.get("GRAPHDATA")).doubleValue());
			labels.add("Q" + ((Long) row.get("QUARTERS")).toString());
		}
		valueAddAcceptedIdeas.setName("Accepted ideas");
		valueAddAcceptedIdeas.setGraphdata(graphdata);
		valueAddAcceptedIdeas.setLabels(labels);
		return valueAddAcceptedIdeas;
	}

	RowMapper<ValueAddAcceptedIdeas> VALUEADDACCEPTEDROWMAPPER = new RowMapper<ValueAddAcceptedIdeas>() {
		@Override
		public ValueAddAcceptedIdeas mapRow(ResultSet rSet, int arg1) throws SQLException {
			ValueAddAcceptedIdeas valueAddAcceptedIdeas = new ValueAddAcceptedIdeas();
			valueAddAcceptedIdeas.getGraphdata().add(rSet.getDouble("GRAPHDATA"));
			valueAddAcceptedIdeas.getLabels().add(rSet.getString("QUARTERS"));
			return valueAddAcceptedIdeas;
		}
	};

	@Override
	public ValueCreation getValueCreationByProgramId(Integer programId) {
		ValueCreation valueCreation = new ValueCreation();
		Integer Id = null;
		String queryCondition = null;
		logger.info("Request to get Value Creation by Id  programId " + programId);
		ArrayList<String> series = new ArrayList<String>();
		if (0 != programId) {
			Id = programId;
			queryCondition = "PROGRAMID";
		}
		updateSeries(series);
		valueCreation.setSeries(series);
		ArrayList<ArrayList<Integer>> lineData = updateLineDatabyId(Id, queryCondition);
		valueCreation.setLinedata(lineData);
		return valueCreation;
	}

	private void updateSeries(ArrayList<String> series) {
		series.add("No of Proposed Ideas");
		series.add("No of Approved Ideas");
	}

	@Override
	public ValueCreation getValueCreationByProjectId(Integer projectId) {
		ValueCreation valueCreation = new ValueCreation();
		Integer Id = null;
		String queryCondition = null;
		logger.info("Request to get Value Creation by Id  projectId " + projectId);
		ArrayList<String> series = new ArrayList<String>();
		if (0 != projectId) {
			Id = projectId;
			queryCondition = "PROJECTID";
		}
		updateSeries(series);
		valueCreation.setSeries(series);
		ArrayList<ArrayList<Integer>> lineData = updateLineDatabyId(Id, queryCondition);
		valueCreation.setLinedata(lineData);
		return valueCreation;
	}

	private ArrayList<ArrayList<Integer>> updateLineDatabyId(Integer Id, String queryCondition) {
		ArrayList<ArrayList<Integer>> lineData = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> proposedList = getValueAddProposed(Id, queryCondition);
		ArrayList<Integer> acceptedList = getValueAddAccepted(Id, queryCondition);
		lineData.add(proposedList);
		lineData.add(acceptedList);
		return (lineData);
	}

	private ArrayList<Integer> getValueAddAccepted(Integer Id, String queryCondition) {
		ArrayList<Integer> acceptedList = new ArrayList<Integer>();
		String valueAddAcceptedQuery = " SELECT VALUE.PROGRAMID, "
				+ "  SUM(IF(MON = 'January', VALUE.TOTAL, 0)) AS 'JAN', "
				+ "  SUM(IF(MON = 'February', VALUE.TOTAL, 0)) AS 'FEB', "
				+ "   SUM(IF(MON = 'March', VALUE.TOTAL, 0)) AS 'MAR', "
				+ "  SUM(IF(MON = 'April', VALUE.TOTAL, 0)) AS 'APR', "
				+ "  SUM(IF(MON = 'May', VALUE.TOTAL, 0)) AS 'MAY', "
				+ " SUM(IF(MON = 'June', VALUE.TOTAL, 0)) AS 'JUN', "
				+ " SUM(IF(MON = 'July', VALUE.TOTAL, 0)) AS 'Jul', "
				+ " SUM(IF(MON = 'August', VALUE.TOTAL, 0)) AS 'AUG', "
				+ " SUM(IF(MON = 'September', VALUE.TOTAL, 0)) AS 'SEP', "
				+ " SUM(IF(MON = 'October', VALUE.TOTAL, 0)) AS 'OCT', "
				+ " SUM(IF(MON = 'November', VALUE.TOTAL, 0)) AS 'NOV', "
				+ " SUM(IF(MON = 'December', VALUE.TOTAL, 0)) AS 'DEC' " + "  FROM   "
				+ " (SELECT PROGRAMID,  MONTHNAME(PROPOSED_DATE) as MON,  " + " COUNT(1) AS TOTAL  "
				+ " 	FROM VALUEADD  " + " 	WHERE " + queryCondition + " = ? " + " AND  "
				+ " 	VALUEADD_STATUS != 'Rejected' AND   " + "  PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)  "
				+ "  GROUP BY PROGRAMID  " + " ) VALUE  " + " GROUP BY VALUE.PROGRAMID; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddAcceptedQuery, new Object[] { Id });
		for (Map<String, Object> row : list) {
			updateListMonthwise(row, acceptedList);
		}
		return acceptedList;
	}

	private ArrayList<Integer> getValueAddProposed(Integer Id, String queryCondition) {
		ArrayList<Integer> proposedList = new ArrayList<Integer>();
		String valueAddProposedQuery = " SELECT VALUE.PROGRAMID, "
				+ "  SUM(IF(MON = 'January', VALUE.TOTAL, 0)) AS 'JAN', "
				+ "  SUM(IF(MON = 'February', VALUE.TOTAL, 0)) AS 'FEB', "
				+ "   SUM(IF(MON = 'March', VALUE.TOTAL, 0)) AS 'MAR', "
				+ "  SUM(IF(MON = 'April', VALUE.TOTAL, 0)) AS 'APR', "
				+ "  SUM(IF(MON = 'May', VALUE.TOTAL, 0)) AS 'MAY', "
				+ " SUM(IF(MON = 'June', VALUE.TOTAL, 0)) AS 'JUN', "
				+ " SUM(IF(MON = 'July', VALUE.TOTAL, 0)) AS 'Jul', "
				+ " SUM(IF(MON = 'August', VALUE.TOTAL, 0)) AS 'AUG', "
				+ " SUM(IF(MON = 'September', VALUE.TOTAL, 0)) AS 'SEP', "
				+ " SUM(IF(MON = 'October', VALUE.TOTAL, 0)) AS 'OCT', "
				+ " SUM(IF(MON = 'November', VALUE.TOTAL, 0)) AS 'NOV', "
				+ " SUM(IF(MON = 'December', VALUE.TOTAL, 0)) AS 'DEC' " + "  FROM   "
				+ " (SELECT PROGRAMID,  MONTHNAME(PROPOSED_DATE) as MON,  " + " COUNT(1) AS TOTAL  "
				+ " 	FROM VALUEADD  " + " 	WHERE " + queryCondition + " = ? AND  "
				+ "  PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)  " + "  GROUP BY PROGRAMID  " + " ) VALUE  "
				+ " GROUP BY VALUE.PROGRAMID; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddProposedQuery, new Object[] { Id });
		for (Map<String, Object> row : list) {
			updateListMonthwise(row, proposedList);
		}

		return proposedList;
	}

	private void updateListMonthwise(Map<String, Object> row, ArrayList<Integer> proposedList) {
		proposedList.add(((BigDecimal) row.get("JAN")).intValue());
		proposedList.add(((BigDecimal) row.get("FEB")).intValue());
		proposedList.add(((BigDecimal) row.get("MAR")).intValue());
		proposedList.add(((BigDecimal) row.get("APR")).intValue());
		proposedList.add(((BigDecimal) row.get("MAY")).intValue());
		proposedList.add(((BigDecimal) row.get("JUN")).intValue());
		proposedList.add(((BigDecimal) row.get("JUL")).intValue());
		proposedList.add(((BigDecimal) row.get("AUG")).intValue());
		proposedList.add(((BigDecimal) row.get("SEP")).intValue());
		proposedList.add(((BigDecimal) row.get("OCT")).intValue());
		proposedList.add(((BigDecimal) row.get("NOV")).intValue());
		proposedList.add(((BigDecimal) row.get("DEC")).intValue());
	}

}
