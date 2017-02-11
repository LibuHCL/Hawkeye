package com.hcl.hawkeye.valueaddmanagement.DAO.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreationQuarterly;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueIndex;

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
	public Map<Integer, ValueIndex> getValueAddByIds(Integer portfolioId) {
		logger.info("Request to get the data of number of Value Add by portfolio");
		Map<Integer, ValueIndex> valueIndex = new HashMap<Integer, ValueIndex>();
		updateValueIndex(valueIndex, portfolioId);
		return valueIndex;
	}

	private void updateValueIndex(Map<Integer, ValueIndex> valueIndex, Integer portfolioId) {
		String proposedIdeasQuery = " SELECT VALUE.PORTFOLIOID,  VALUE.YR, "
				+ "  SUM(IF(MON = 'January', VALUE.TOTAL, 0)) AS 'JAN',  "
				+ " SUM(IF(MON = 'February', VALUE.TOTAL, 0)) AS 'FEB',  "
				+ " SUM(IF(MON = 'March', VALUE.TOTAL, 0)) AS 'MAR',  "
				+ " SUM(IF(MON = 'April', VALUE.TOTAL, 0)) AS 'APR',  "
				+ " SUM(IF(MON = 'May', VALUE.TOTAL, 0)) AS 'MAY',  "
				+ " SUM(IF(MON = 'June', VALUE.TOTAL, 0)) AS 'JUN',  "
				+ " SUM(IF(MON = 'July', VALUE.TOTAL, 0)) AS 'Jul',  "
				+ " SUM(IF(MON = 'August', VALUE.TOTAL, 0)) AS 'AUG',  "
				+ " SUM(IF(MON = 'September', VALUE.TOTAL, 0)) AS 'SEP',  "
				+ " SUM(IF(MON = 'October', VALUE.TOTAL, 0)) AS 'OCT',  "
				+ " SUM(IF(MON = 'November', VALUE.TOTAL, 0)) AS 'NOV',  "
				+ " SUM(IF(MON = 'December', VALUE.TOTAL, 0)) AS 'DEC'  " + " FROM   "
				+ " ( SELECT PORTFOLIOID,  MONTHNAME(PROPOSED_DATE) as MON, YEAR(PROPOSED_DATE) AS YR,   "
				+ " COUNT(1) AS TOTAL   " + " FROM VALUEADD   " + " WHERE             " + " PORTFOLIOID = ?   "
				+ " GROUP BY PORTFOLIOID, YR, MON   " + " ) VALUE   " + " GROUP BY VALUE.PORTFOLIOID, VALUE.YR;";

		String acceptedIdeasQuery = "  SELECT VALUE.PORTFOLIOID,  VALUE.YR, "
				+ "  SUM(IF(MON = 'January', VALUE.TOTAL, 0)) AS 'JAN',  "
				+ "   SUM(IF(MON = 'February', VALUE.TOTAL, 0)) AS 'FEB',  "
				+ "   SUM(IF(MON = 'March', VALUE.TOTAL, 0)) AS 'MAR',  "
				+ "   SUM(IF(MON = 'April', VALUE.TOTAL, 0)) AS 'APR',  "
				+ "   SUM(IF(MON = 'May', VALUE.TOTAL, 0)) AS 'MAY',  "
				+ "  SUM(IF(MON = 'June', VALUE.TOTAL, 0)) AS 'JUN',  "
				+ "  SUM(IF(MON = 'July', VALUE.TOTAL, 0)) AS 'Jul',  "
				+ "  SUM(IF(MON = 'August', VALUE.TOTAL, 0)) AS 'AUG',  "
				+ "  SUM(IF(MON = 'September', VALUE.TOTAL, 0)) AS 'SEP',  "
				+ "  SUM(IF(MON = 'October', VALUE.TOTAL, 0)) AS 'OCT',  "
				+ "  SUM(IF(MON = 'November', VALUE.TOTAL, 0)) AS 'NOV',  "
				+ "   SUM(IF(MON = 'December', VALUE.TOTAL, 0)) AS 'DEC'  " + "   FROM  "
				+ "  ( SELECT PORTFOLIOID,  MONTHNAME(PROPOSED_DATE) as MON, YEAR(PROPOSED_DATE) AS YR,  "
				+ "  COUNT(1) AS TOTAL  " + "  	FROM VALUEADD  " + "  	WHERE             " + "   PORTFOLIOID = ? AND  "
				+ "   (VALUEADD_STATUS != 'Proposed' AND VALUEADD_STATUS != 'Rejected')  "
				+ "   GROUP BY PORTFOLIOID, YR, MON  " + "  ) VALUE  " + "  GROUP BY VALUE.PORTFOLIOID, VALUE.YR; ";
		List<Map<String, Object>> proposedList = jdbcTemplate.queryForList(proposedIdeasQuery,
				new Object[] { portfolioId });
		List<Map<String, Object>> acceptedList = jdbcTemplate.queryForList(acceptedIdeasQuery,
				new Object[] { portfolioId });
		for (Map<String, Object> row : proposedList) {
			ValueIndex index = new ValueIndex();
			ArrayList<Integer> proposedListData = new ArrayList<Integer>();
			ArrayList<Integer> acceptedListData = new ArrayList<Integer>();
			ArrayList<String> series = new ArrayList<String>();
			updateSeries(series);
			ArrayList<String> labels = new ArrayList<String>();
			updateLabels(labels);
			index.setLabels(labels);
			index.setSeries(series);
			updateListMonthwise(row, proposedListData);
			Integer year = (Integer) row.get("YR");
			for (Map<String, Object> acceptedListRow : acceptedList) {
				Integer acceptedYear = (Integer) acceptedListRow.get("YR");
				if (year.equals(acceptedYear)) {
					updateListMonthwise(acceptedListRow, acceptedListData);
				}
			}
			ArrayList<ArrayList<Integer>> lineData = new ArrayList<ArrayList<Integer>>();
			lineData.add(proposedListData);
			lineData.add(acceptedListData);
			index.setLinedata(lineData);
			valueIndex.put(year, index);
		}

	}

	private void updateLabels(ArrayList<String> labels) {
		labels.addAll(
				Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
	}

	@Override
	public ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId) {
		ValueAddAcceptedIdeas valueAddAcceptedIdeas = new ValueAddAcceptedIdeas();
		logger.info("Request to get Accepted ideas for program " + programId);
		String valueAddAcceptedQuery = "SELECT PROGRAMID, " + " QUARTER(PROPOSED_DATE) AS QUARTERS, "
				+ " SUM(CASE WHEN VALUEADD_STATUS = 'Implemented' THEN 1 ELSE 0 END)/SUM(CASE WHEN VALUEADD_STATUS != 'Implemented' THEN 1 ELSE 0 END) AS GRAPHDATA FROM VALUEADD "
				+ " WHERE YEAR(PROPOSED_DATE) = YEAR(NOW()) AND " + " PROGRAMID = ? " + "	GROUP BY PROGRAMID, QUARTERS; ";
		/*
		 * valueAddAcceptedIdeas =
		 * jdbcTemplate.queryForObject(valueAddAcceptedQuery,
		 * VALUEADDACCEPTEDROWMAPPER, new Object[] { programId });
		 */
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddAcceptedQuery, new Object[] { programId });
		ArrayList<Double> graphdata = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		for (Map<String, Object> row : list) {
			if(null != row.get("GRAPHDATA")){
			graphdata.add(((BigDecimal) row.get("GRAPHDATA")).doubleValue());}
			labels.add("Q" + ((Integer) row.get("QUARTERS")).toString());
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
		logger.info("Request to get Value Creation by Id  programId " + programId);
		ValueCreation valueCreation = new ValueCreation();
		Integer id = null;
		String queryCondition = null;
		ArrayList<String> series = new ArrayList<String>();
		if (0 != programId) {
			id = programId;
			queryCondition = "PROGRAMID";
		}
		updateSeries(series);
		valueCreation.setSeries(series);
		ArrayList<ArrayList<Integer>> lineData = updateLineDatabyId(id, queryCondition);
		valueCreation.setLinedata(lineData);
		return valueCreation;
	}

	private void updateSeries(ArrayList<String> series) {
		series.add("No of Proposed Ideas");
		series.add("No of Approved Ideas");
	}

	@Override
	public ValueCreation getValueCreationByProjectId(Integer projectId) {
		logger.info("Request to get Value Creation by Id  projectId " + projectId);
		ValueCreation valueCreation = new ValueCreation();
		Integer id = null;
		String queryCondition = null;
		ArrayList<String> series = new ArrayList<String>();
		if (0 != projectId) {
			id = projectId;
			queryCondition = "PROJECTID";
		}
		updateSeries(series);
		valueCreation.setSeries(series);
		ArrayList<ArrayList<Integer>> lineData = updateLineDatabyId(id, queryCondition);
		valueCreation.setLinedata(lineData);
		return valueCreation;
	}

	private ArrayList<ArrayList<Integer>> updateLineDatabyId(Integer id, String queryCondition) {
		ArrayList<ArrayList<Integer>> lineData = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> proposedList = getValueAddProposed(id, queryCondition);
		ArrayList<Integer> acceptedList = getValueAddAccepted(id, queryCondition);
		lineData.add(proposedList);
		lineData.add(acceptedList);
		return (lineData);
	}

	private ArrayList<Integer> getValueAddAccepted(Integer id, String queryCondition) {
		ArrayList<Integer> acceptedList = new ArrayList<Integer>();
		String valueAddAcceptedQuery = " SELECT VALUE."+queryCondition+", "
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
				+ " (SELECT "+queryCondition+", MONTHNAME(PROPOSED_DATE) as MON,  " + " COUNT(1) AS TOTAL  "
				+ " 	FROM VALUEADD  " + " 	WHERE " + queryCondition + " = ? " + " AND  "
				+ " 	VALUEADD_STATUS != 'Rejected' AND   " + "  PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)  "
				+ "  GROUP BY "+ queryCondition + ", MON ) VALUE  " + " GROUP BY VALUE."+queryCondition+"; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddAcceptedQuery, new Object[] { id });
		for (Map<String, Object> row : list) {
			updateListMonthwise(row, acceptedList);
		}
		return acceptedList;
	}

	private ArrayList<Integer> getValueAddProposed(Integer id, String queryCondition) {
		ArrayList<Integer> proposedList = new ArrayList<Integer>();
		String valueAddProposedQuery = " SELECT VALUE."+queryCondition+", "
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
				+ " (SELECT "+queryCondition+",  MONTHNAME(PROPOSED_DATE) as MON,  " + " COUNT(1) AS TOTAL  "
				+ " 	FROM VALUEADD  " + " 	WHERE " + queryCondition + " = ? AND  "
				+ "  PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)  " + "  GROUP BY "+queryCondition+  ", MON ) VALUE  "
				+ " GROUP BY VALUE."+queryCondition+"; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddProposedQuery, new Object[] { id });
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

	@Override
	public ValueCreationQuarterly getQuarterlyValueByProgramId(Integer program) {
		logger.info("Request to get Quarterly Value Creation by Id  program " + program);
		ValueCreationQuarterly valueCreation = new ValueCreationQuarterly();
		Integer id = null;
		String queryCondition = null;
		ArrayList<String> series = new ArrayList<String>();
		ArrayList<String> labels = new ArrayList<String>();
		if (0 != program) {
			id = program;
			queryCondition = "PROGRAMID";
		}
		updateQuarterlySeries(series);
		updateQuarterlyLabels(labels);
		valueCreation.setSeries(series);
		valueCreation.setLabels(labels);
		ArrayList<ArrayList<Integer>> lineData = updateQuarterlyLineDatabyId(id, queryCondition);
		valueCreation.setGraphdata(lineData);
		return valueCreation;
	}

	@Override
	public ValueCreationQuarterly getQuarterlyValueByProjectId(Integer projectId) {
		logger.info("Request to get Quarterly Value Creation by Id  projectId " + projectId);
		ValueCreationQuarterly valueCreation = new ValueCreationQuarterly();
		Integer id = null;
		String queryCondition = null;
		ArrayList<String> series = new ArrayList<String>();
		ArrayList<String> labels = new ArrayList<String>();
		if (0 != projectId) {
			id = projectId;
			queryCondition = "PROJECTID";
		}
		updateQuarterlySeries(series);
		updateQuarterlyLabels(labels);
		valueCreation.setSeries(series);
		ArrayList<ArrayList<Integer>> lineData = updateQuarterlyLineDatabyId(id, queryCondition);
		valueCreation.setGraphdata(lineData);
		return valueCreation;
	}

	private void updateQuarterlyLabels(ArrayList<String> labels) {
		labels.add("Q1");
		labels.add("Q2");
		labels.add("Q3");
		labels.add("Q4");
	}

	private void updateQuarterlySeries(ArrayList<String> series) {
		series.add("Ideas Implemented");
		series.add("Ideas Accepted");
	}

	private ArrayList<ArrayList<Integer>> updateQuarterlyLineDatabyId(Integer id, String queryCondition) {
		ArrayList<ArrayList<Integer>> lineData = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> implementedList = getImplValueAddQuarterly(id, queryCondition);
		ArrayList<Integer> approvedList = getApprovedValueAddQuarterly(id, queryCondition);
		lineData.add(implementedList);
		lineData.add(approvedList);
		return (lineData);
	}

	private ArrayList<Integer> getImplValueAddQuarterly(Integer id, String queryCondition) {
		ArrayList<Integer> implementedList = new ArrayList<Integer>();
		String valueAddImplementedQuery = " SELECT VALUE."+queryCondition+",  "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1', "
				+ "  SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2', "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3', "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4'  " + " FROM "
				+ " ( SELECT "+queryCondition+",  QUARTER(PROPOSED_DATE) as QUARTER,  " + " 	COUNT(1) AS TOTAL "
				+ " FROM VALUEADD " + " WHERE " + queryCondition + "= ? AND     "
				+ " PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR) " + " AND VALUEADD_STATUS = 'Implemented' "
				+ " GROUP BY "+queryCondition + ", QUARTER ) VALUE " + " GROUP BY VALUE."+queryCondition+"; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddImplementedQuery, new Object[] { id });
		for (Map<String, Object> row : list) {
			updateListQuarterly(row, implementedList);
		}

		return implementedList;
	}

	private ArrayList<Integer> getApprovedValueAddQuarterly(Integer id, String queryCondition) {
		ArrayList<Integer> approvedList = new ArrayList<Integer>();
		String valueAddImplementedQuery = " SELECT VALUE."+queryCondition+",  "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1',   "
				+ " SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2',   "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3',   "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4'      " + " FROM   "
				+ " ( SELECT "+queryCondition+",  QUARTER(PROPOSED_DATE) as QUARTER,    " + " COUNT(1) AS TOTAL   "
				+ " 		FROM VALUEADD   " + " WHERE " + queryCondition + "= ? AND        "
				+ "             PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)   "
				+ "             AND (VALUEADD_STATUS != 'Proposed' AND VALUEADD_STATUS != 'Rejected')   "
				+ "             GROUP BY "+queryCondition+  ", QUARTER ) VALUE   " + " GROUP BY VALUE."+queryCondition+"; ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(valueAddImplementedQuery, new Object[] { id });
		for (Map<String, Object> row : list) {
			updateListQuarterly(row, approvedList);
		}

		return approvedList;
	}

	private void updateListQuarterly(Map<String, Object> row, ArrayList<Integer> implementedList) {
		implementedList.add(((BigDecimal) row.get("Q1")).intValue());
		implementedList.add(((BigDecimal) row.get("Q2")).intValue());
		implementedList.add(((BigDecimal) row.get("Q3")).intValue());
		implementedList.add(((BigDecimal) row.get("Q4")).intValue());
	}

	@Override
	public ValueAddAcceptedIdeas getEconomicValueAdd(Integer programId) {
		ValueAddAcceptedIdeas economicValue = new ValueAddAcceptedIdeas();
		ArrayList<String> labels = new ArrayList<String>();
		Double h1Total = 0.0;
		Double h2Total = 0.0;
		labels.addAll(Arrays.asList("H1", "H2"));
		economicValue.setName("Economic value addition");
		economicValue.setLabels(labels);
		ArrayList<Double> graphdata = new ArrayList<Double>();
		String economicValueAddQuery = " SELECT VALUE.PROJECTID,  "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1',   "
				+ " SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2',   "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3',   "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4'      " + " FROM   "
				+ " ( SELECT PROJECTID,  QUARTER(PROPOSED_DATE) as QUARTER,  " + " SUM(ECONOMIC_VALUE) AS TOTAL  "
				+ " 		FROM VALUEADD  " + " 		WHERE PROGRAMID = ? AND   "
				+ "           PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)   "
				+ "           AND (VALUEADD_STATUS != 'Proposed' AND VALUEADD_STATUS != 'Rejected')   "
				+ "           GROUP BY PROJECTID,QUARTER   " + " 		) VALUE   " + " GROUP BY VALUE.PROJECTID;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(economicValueAddQuery, new Object[] { programId });
		for (Map<String, Object> row : list) {
			h1Total = (Double) row.get("Q1") + (Double) row.get("Q2");
			h2Total = (Double) row.get("Q3") + (Double) row.get("Q4");
		}
		graphdata.add(h1Total*100/(h1Total+h2Total));
		graphdata.add(h2Total*100/(h1Total+h2Total));
		economicValue.setGraphdata(graphdata);
		return economicValue;
	}
	
	@Override
	public ValueAddAcceptedIdeas getEconomicValueAddByPortfolio(Integer portfolioId) {
		ValueAddAcceptedIdeas economicValue = new ValueAddAcceptedIdeas();
		Double h1Total = 0.0;
		Double h2Total = 0.0;
		ArrayList<String> labels = new ArrayList<String>();
		labels.addAll(Arrays.asList("H1", "H2"));
		economicValue.setName("Economic value addition");
		economicValue.setLabels(labels);
		ArrayList<Double> graphdata = new ArrayList<Double>();
		String economicValueAddQuery = " SELECT VALUE.PORTFOLIOID,  "
				+ " SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1',   "
				+ " SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2',   "
				+ " SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3',   "
				+ " SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4'  FROM   "
				+ " ( SELECT PORTFOLIOID,  QUARTER(PROPOSED_DATE) as QUARTER, SUM(ECONOMIC_VALUE) AS TOTAL  "
				+ " FROM VALUEADD"
				+ "  WHERE "
				+ "  PORTFOLIOID=?"
				+ "  AND PROPOSED_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)   "
				+ "  AND (VALUEADD_STATUS != 'Proposed' AND VALUEADD_STATUS != 'Rejected')   "
				+ "  GROUP BY PORTFOLIOID,QUARTER) VALUE   GROUP BY VALUE.PORTFOLIOID;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(economicValueAddQuery, new Object[] { portfolioId });
		for (Map<String, Object> row : list) {
			h1Total = (Double) row.get("Q1") + (Double) row.get("Q2");
			h2Total = (Double) row.get("Q3") + (Double) row.get("Q4");
		}
		logger.info("h1total " + h1Total);
		logger.info("h2total " + h2Total);
		logger.info("h1% " + h1Total*100/(h1Total+h2Total));
		logger.info("h2% " + h2Total*100/(h1Total+h2Total));
		graphdata.add(h1Total*100/(h1Total+h2Total));
		graphdata.add(h2Total*100/(h1Total+h2Total));
		economicValue.setGraphdata(graphdata);
		return economicValue;
	}

}
