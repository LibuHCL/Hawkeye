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
	
	//Here i am just taking the Cost object as example to return the data.
	// And i used here postgresql DB connection. change to Mysql or whatever it may be
	@Override
	public Cost getPortfolioData() {
		logger.info("Request to get the data of portfolio");
		Cost cost = null;
		//String sql = "select key,value,postfix,symbo from at_portfolio";
		String sql = "select id, username, city, email from at_user where id='286eff81-bf0f-4377-b135-97a3636aa6a2'::uuid";
		try {
			cost = jdbcTemplate.queryForObject(sql, FORTFOLIOROWMAPPER);
		} catch (Exception e) {
			logger.error("Exception while getting the fortfolio details from table");
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
