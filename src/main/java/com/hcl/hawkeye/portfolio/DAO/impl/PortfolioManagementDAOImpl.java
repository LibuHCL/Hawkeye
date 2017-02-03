package com.hcl.hawkeye.portfolio.DAO.impl;

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

import com.hcl.hawkeye.Exceptions.PortfolioDataRetrievalException;
import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Cost;

@Repository
public class PortfolioManagementDAOImpl implements PortfolioManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public Cost getPortfolioData() {
		logger.info("Request to get the data of portfolio");
		Cost cost = null;
		String sql = "select id, username, city, email from at_portfolio where id='47e7d6d2-e93d-11e6-aaf4-3417eb808af4'";
		try {
			cost = jdbcTemplate.queryForObject(sql, FORTFOLIOROWMAPPER);
		} catch (DataAccessException dae) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.portfolio", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new PortfolioDataRetrievalException(errorMsg, dae);
		}
		return cost;
	}
	
	RowMapper<Cost> FORTFOLIOROWMAPPER = new RowMapper<Cost>() {

		@Override
		public Cost mapRow(ResultSet rSet, int arg1) throws SQLException {
			Cost cost = new Cost();
			cost.set_key(rSet.getString("id"));
			cost.set_value(rSet.getString("username"));
			cost.set_postfix(rSet.getString("city"));
			cost.set_symbol(rSet.getString("email"));
			return cost;
		}
	};

}
