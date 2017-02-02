package com.ing.hawkeye.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ing.hawkeye.DAO.PortfolioManagementDAO;
import com.ing.hawkeye.portfolio.data.Cost;

@Component(value = "PortfolioDAO")
public class PortfolioManagementDAOImpl implements PortfolioManagementDAO {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Cost getPortfolioData() {
		logger.info("Request to get the data of portfolio");
		Cost cost = null;
		String sql = "select id, username, city, email from at_portfolio where id='47e7d6d2-e93d-11e6-aaf4-3417eb808af4'";
		try {
			cost = jdbcTemplate.queryForObject(sql, FORTFOLIOROWMAPPER);
		} catch (Exception e) {
			logger.error("Exception while getting the fortfolio details from table", e);
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
