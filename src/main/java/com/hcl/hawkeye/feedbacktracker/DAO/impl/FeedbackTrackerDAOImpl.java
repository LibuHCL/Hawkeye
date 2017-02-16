package com.hcl.hawkeye.feedbacktracker.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.hawkeye.Exceptions.FeedbackTrackException;
import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.feedbacktracker.DAO.FeedbackTrackerDAO;
import com.hcl.hawkeye.portfolio.DO.Graph;

@Repository
public class FeedbackTrackerDAOImpl implements FeedbackTrackerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackTrackerDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int capFeedbackTrackDetails(FeedbackDetails fbk) {
		// TODO Auto-generated method stub
		logger.info("Getting the feedbackTrack details: {}", fbk);
		int count = 0;
		FeedbackDetails feebkdetail = new FeedbackDetails();
		feebkdetail.setFeedbackId(fbk.getFeedbackId());
		feebkdetail.setProjectId(fbk.getProjectId());
		feebkdetail.setParamaterId(fbk.getParamaterId());
		feebkdetail.setFeedback_value(fbk.getFeedback_value());
		feebkdetail.setWeightage(fbk.getWeightage());
		feebkdetail.setFeedback_Date(fbk.getFeedback_Date());
		feebkdetail.setReporter_resource_Id(fbk.getReporter_resource_Id());
		feebkdetail.setReporter_company_Id(fbk.getReporter_company_Id());
		feebkdetail.setReporter_Type(fbk.getReporter_Type());
		feebkdetail.setReportee(fbk.getReportee());
		
		String 	feedBack_SQL = "INSERT INTO FEEDBACK_TRACKER(FEEDBACKID,PROJECTID,PARAMETERID,FEEDBACK_VALUE,WEIGHTAGE,FEEDBACK_DATE,"
				+ "REPORTER_RESOURCE_ID,REPORTER_COMPANY_ID,REPORTER_TYPE,REPORTEE_ID) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			count = jdbcTemplate.update(feedBack_SQL,new Object[] {fbk.getFeedbackId(),fbk.getProjectId(),fbk.getParamaterId(),fbk.getFeedback_value(),fbk.getWeightage(),
					fbk.getFeedback_Date(),fbk.getReporter_resource_Id(),fbk.getReporter_company_Id(),fbk.getReporter_Type(),fbk.getReportee()});
		  }
		catch (DataAccessException dae) {
			logger.error("Exception in capFeedbackTrackDetails");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		return count;

	}

	RowMapper<FeedbackDetails> rowMapper = new RowMapper<FeedbackDetails>() {
		@Override
		public FeedbackDetails mapRow(ResultSet rSet, int arg1) throws SQLException {
			FeedbackDetails feedback = new FeedbackDetails();
			feedback.setFeedbackId(rSet.getInt("FEEDBACKID"));
			feedback.setParamaterId(rSet.getInt("PARAMETERID"));
			feedback.setFeedback_value(rSet.getInt("FEEDBACK_VALUE"));
			feedback.setWeightage(rSet.getInt("WEIGHTAGE"));
			feedback.setFeedback_Date(rSet.getString("FEEDBACK_DATE"));
			feedback.setReporter_resource_Id(rSet.getInt("REPORTER_RESOURCE_ID"));
			feedback.setReporter_company_Id(rSet.getInt("REPORTER_COMPANY_ID"));
			
			return feedback;
		}
		
	};

	@Override
	public FeedbackDetails getFeedbackPerProject(String reporterType, int projectId) {
		// TODO Auto-generated method stub
		FeedbackDetails feebkdetail = null;
		String getFeedBackperProject_SQL ="SELECT * FROM FEEDBACK_TRACKER WHERE REPORTER_TYPE = ? AND PROJECTID = ?";
		try{
			feebkdetail = jdbcTemplate.queryForObject(getFeedBackperProject_SQL, new Object[] {reporterType,projectId}, rowMapper);
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in capFeedbackTrackDetails");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		logger.info("get the result getFeedbackPerProject METHOD");
		return feebkdetail;
	}

	@Override
	public List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int programId) {
		// TODO Auto-generated method stub
		List<FeedbackDetails> feebkdetailList = new ArrayList<FeedbackDetails>();
		String getFeedBackperProject_SQL = "SELECT F. * FROM FEEDBACK_TRACKER F, PROJECT P WHERE F.PROJECTID = P.PROJECTID AND P.PROGRAM_ID = ? AND F.REPORTER_TYPE = ?"; 
		try{
			List<Map<String, Object>> feedbackList = jdbcTemplate.queryForList(getFeedBackperProject_SQL,new Object[] {programId,reporterType});
			if(feedbackList  != null && feedbackList.size() >0)  
			{
				for (Map<String, Object> row : feedbackList) {
					
					FeedbackDetails feedback = new FeedbackDetails();
					feedback.setFeedbackId(Integer.valueOf(row.get("FEEDBACKID").toString()));
					feedback.setParamaterId(Integer.valueOf(row.get("PARAMETERID").toString()));
					feedback.setFeedback_value(Integer.valueOf(row.get("FEEDBACK_VALUE").toString()));
					feedback.setWeightage(Integer.valueOf(row.get("WEIGHTAGE").toString()));
					//feedback.setFeedback_Date((String)row.get("FEEDBACK_DATE"));//need to change this
					feedback.setReporter_resource_Id(Integer.valueOf(row.get("REPORTER_RESOURCE_ID").toString()));
					feedback.setReporter_company_Id(Integer.valueOf(row.get("REPORTER_COMPANY_ID").toString()));
					feebkdetailList.add(feedback);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in capFeedbackTrackDetails");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		return feebkdetailList;
	}

	@Override
	public Graph getnoofFeedBacksPerQtAtPerfolioLevel(int portfolioId, String repType){
		ArrayList<Double> graphData = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		
		Graph escDetList = new Graph();
		String sql_noofesc = "SELECT  VALUE.PORTFOLIO_ID ,"
			 +" SUM(IF(QUARTER = '1', VALUE.TOTAL, 0)) AS 'Q1', "
			 +" SUM(IF(QUARTER = '2', VALUE.TOTAL, 0)) AS 'Q2'," 
			 +" SUM(IF(QUARTER = '3', VALUE.TOTAL, 0)) AS 'Q3', " 
			 +" SUM(IF(QUARTER = '4', VALUE.TOTAL, 0)) AS 'Q4' FROM " 
			 +" (SELECT esc.FEEDBACKID,pf.PORTFOLIO_ID, QUARTER(esc.FEEDBACK_DATE) as QUARTER,  COUNT(1) AS TOTAL" 
			 +" FROM FEEDBACK_TRACKER esc, PROJECT proj,PROGRAM prog, PORTFOLIO pf "
			 +" WHERE pf.PORTFOLIO_ID=prog.PORTFOLIO_ID "
			 +" AND  prog.PROGRAMID = proj.PROGRAM_ID "
             +" AND  proj.PROJECTID =esc.PROJECTID"
			 +" AND  pf.PORTFOLIO_ID=? AND REPORTER_TYPE=?" 
			 +" AND esc.FEEDBACK_DATE >= DATE_SUB(NOW(),INTERVAL 1 YEAR)"
			+" GROUP BY esc.FEEDBACKID, QUARTER) VALUE GROUP BY VALUE.PORTFOLIO_ID";
               
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql_noofesc, new Object[] { portfolioId,repType });
		for (Map<String, Object> row : list) {
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q1"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q2"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q3"))));
			graphData.add(Double.parseDouble(String.valueOf(row.get("Q4"))));
		}
		updateQuarterlyLabels(labels);
		escDetList.setGraphData(graphData);
		escDetList.setLabels(labels);
		return escDetList;
	}
	
	private void updateQuarterlyLabels(ArrayList<String> labels) {
		labels.add("Q1");
		labels.add("Q2");
		labels.add("Q3");
		labels.add("Q4");
	}
	
	
	@Override
	public List<FeedbackDetails> getFeedBackCategoryId() {
		// TODO Auto-generated method stub
		List<FeedbackDetails> feebkdetailList = new ArrayList<FeedbackDetails>();
		String sql = "SELECT CATEGORY_ID,CATEGORY_NAME FROM FEEDBACK_CATEGORY";
		try{
		List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(sql);
		System.out.println("categoryListcategoryListcategoryListcategoryListcategoryListcategoryList:"+categoryList);
		if(categoryList  != null && categoryList.size() >0)  
		{
			for (Map<String, Object> row : categoryList) {
				
				FeedbackDetails category = new FeedbackDetails();
				category.setCategory_Id(Integer.valueOf(row.get("CATEGORY_ID").toString()));
				category.setCategory_Name((String)row.get("CATEGORY_NAME"));
				feebkdetailList.add(category);
				
	        } 
		}
		}
		catch (DataAccessException dae) {
			logger.error("Exception in getFeedBackCategoryId method");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		return feebkdetailList;
		
	}

	@Override
	public List<FeedbackDetails> getFeedbackParameter(int category_Id) {
		// TODO Auto-generated method stub
		List<FeedbackDetails> parameterList = new ArrayList<FeedbackDetails>();
		String getFeedBackperProject_SQL = "SELECT * FROM FEEDBACK_PARAMETER WHERE CATEGORYID = ? ";
		try{
			List<Map<String, Object>> prmList = jdbcTemplate.queryForList(getFeedBackperProject_SQL,new Object[] {category_Id});
			System.out.println("prmListprmListprmListprmListprmList:"+prmList);
			if(prmList  != null && prmList.size() >0)  
			{
				for (Map<String, Object> row : prmList) {
					
					FeedbackDetails feedback = new FeedbackDetails();
					feedback.setParamaterId(Integer.valueOf(row.get("PARAMETERID").toString()));
					feedback.setParameterName((String)row.get("PARAM_NAME"));
					parameterList.add(feedback);
					
		        } 
			}
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in getFeedbackParameter");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		return parameterList;
	}
}
