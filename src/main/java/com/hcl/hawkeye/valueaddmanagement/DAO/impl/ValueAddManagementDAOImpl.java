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
		logger.info("Request to get Accepted ideas for program "+programId);
		String valueAddAcceptedQuery = "SELECT PROGRAMID, " + " QUARTER(PROPOSED_DATE) AS QUARTERS, "
				+ " SUM(CASE WHEN VALUEADD_STATUS = 'Implemented' THEN 1 ELSE 0 END)/SUM(CASE WHEN VALUEADD_STATUS != 'Implemented' THEN 1 ELSE 0 END) AS GRAPHDATA FROM VALUEADD "
				+ " WHERE YEAR(PROPOSED_DATE) = YEAR(NOW()) AND " + " PROGRAMID = ? " + "	GROUP BY PROJECTID; ";
		/*valueAddAcceptedIdeas = jdbcTemplate.queryForObject(valueAddAcceptedQuery, VALUEADDACCEPTEDROWMAPPER,
				new Object[] { programId });*/
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

}
