package com.hcl.hawkeye.utils;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.valueaddmanagement.DO.Value;


public class HawkEyeUtils {
	
	@Autowired	
	static JdbcTemplate jdbcTemplate;

	public static Portfolio populatePortfolioWithPortfolioId(Portfolio ptfolio,
			int portFolioId) {
		Portfolio createdpfolio=new Portfolio();
		BeanUtils.copyProperties(ptfolio, createdpfolio);
		createdpfolio.setPortfolioId(portFolioId);
		return createdpfolio;
	}
	
	 public static Timestamp getTimeStamp(String date){
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(date);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
		}
		return timestamp;
	}

	public static Program populateProgWithProgId(Program prog, int progId) {
		Program createdprog=new Program();
		BeanUtils.copyProperties(prog, createdprog);
		createdprog.setPortfolioId(progId);
		return createdprog;
	}
	
	
	public static Date getFirstDayOfQuarter(java.util.Date date) {
		
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return (Date) cal.getTime();
	}

	public static Date getLastDayOfQuarter(java.util.Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return (Date) cal.getTime();
	}
	public static int geMaxId(String table) {
		String highestPortIdQuery = null ;
		if(table.equals("PROGRAM")){
			highestPortIdQuery ="SELECT MAX(PROGRAMID) FROM PROGRAM";
		}else if(table.equals("PROJECT")){
			highestPortIdQuery ="SELECT MAX(PROJECTID) FROM PROJECT";
		}
		
		if(highestPortIdQuery != null){
			 return jdbcTemplate.queryForObject(highestPortIdQuery, Integer.class);
		}
		else return 0;
		
	}

	public static Value populateValueId(Value value, int valueId) {
		//Value createdValue=new Value();
		//BeanUtils.copyProperties(value, createdValue);
		//createdpfolio.setPortfolioId(portFolioId);
		value.setValueId(valueId);
		return value;
	}

	public static Escalation populateEscalationWithescId(Escalation esc,
			int escId) {
		Escalation createdEsc=new Escalation();
		BeanUtils.copyProperties(esc, createdEsc);
		createdEsc.setEscalationId(escId);
		return createdEsc;
	}

	public static ProjectCostDetails populateProjectCostWithProjectID(ProjectCostDetails cost,
			BigInteger projectID) {
			ProjectCostDetails projectCost = new ProjectCostDetails();
			BeanUtils.copyProperties(cost, projectCost);
			projectCost.setProjectID(projectID);
			return projectCost;
	}
	
	public static int getRAGStatus(int value){
		
		int retVal = 0;
		if (value == HawkEyeConstants.GREEN) {
			retVal = 1;
		} else if (value <= HawkEyeConstants.AMBER) {
			retVal = 0;
		} else if (value <= HawkEyeConstants.RED) {
			retVal =-1;
		}
		
		return retVal;
	}
}
