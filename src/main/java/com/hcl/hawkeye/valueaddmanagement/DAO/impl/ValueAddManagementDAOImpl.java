package com.hcl.hawkeye.valueaddmanagement.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

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

@Repository
public class ValueAddManagementDAOImpl implements ValueAddManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(ValueAddManagementDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MessageSource messageSource;
	

	@Override
	public Value createValueAdd(Value value) {
		String createValueAddQuery = "INSERT INTO VALUEADD (PROJECTID, PROGRAMID, PORTFOLIOID, DESCRIPTION, PROPOSED_DATE, VALUEADD_STATUS, STATUS_UPDATE_DATE, ECONOMIC_VALUE) " +
									 "VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(createValueAddQuery,new Object[]{value.getProjectId(),
				value.getProgramId(),value.getPortfolioId(), value.getDescription(),HawkEyeUtils.getTimeStamp(value.getProposedDate()), value.getValueAddStatu()
				,HawkEyeUtils.getTimeStamp(value.getStatusUpdateDate()),value.getEconomicValue()});
		int valueId = getValueId();
	 logger.info("Portfolio added with portfolio id :"+ valueId);
		return HawkEyeUtils.populateValueId(value, valueId);
	}

	private int getValueId() {
		String highestValueIdQuery="SELECT MAX(VALUEID) FROM VALUEADD";
		Integer id=jdbcTemplate.queryForObject(highestValueIdQuery, Integer.class);
		if(id == null){
			return 1000;
		}
		return id+1;
	}

	@Override
	public ValueAdd getNumbersOfValueAdd() {
		logger.info("Request to get the data of number of Value Add");
		ValueAdd valueAdd = new ValueAdd();
		String numbersOfValueAddQuery = "SELECT PROJECTID, VALUEADD_STATUS, MONTHNAME(PROPOSED_DATE) as MON, YEAR(PROPOSED_DATE) AS YEARS," + 
				"QUARTER(PROPOSED_DATE) AS QUARTERS,COUNT(MONTHNAME(PROPOSED_DATE))" +
				"FROM VALUEADD" +
				"GROUP BY PROJECTID, VALUEADD_STATUS, MON, QUARTERS, YEARS;";
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
	public ValueAdd getValueAddByProgram(Integer programID) {
		logger.info("Request to get the data of number of Value Add at program level");
		ValueAdd valueAdd = new ValueAdd();
		String numbersOfValueAddProgQuery = "SELECT PROGRAMID, VALUEADD_STATUS, COUNT(1) AS TOTAL" +
				"FROM VALUEADD" +
				"WHERE PROGRAMID = ? AND" +
                "PROPOSED_DATE >= CURDATE() - INTERVAL DAYOFWEEK(CURDATE())+364 DAY AND" +
				"PROPOSED_DATE < CURDATE() - INTERVAL DAYOFWEEK(CURDATE())-1 DAY" +
				"GROUP BY PROJECTID, VALUEADD_STATUS;";
		try {
			valueAdd = jdbcTemplate.queryForObject(numbersOfValueAddProgQuery, VALUEADDROWMAPPER,new Object[]{programID});
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.getvalueadd", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new ValueAddDataRetrievalException(errorMsg, dae);
		}
		return valueAdd;
	}


}
