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

@Repository
public class FeedbackTrackerDAOImpl implements FeedbackTrackerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackTrackerDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public FeedbackDetails capFeedbackTrackDetails(FeedbackDetails fbk) {
		// TODO Auto-generated method stub
		logger.info("Getting the feedbackTrack details:"+fbk);
		FeedbackDetails feebkdetail = null;
		String 	feedBack_SQL = "INSERT INTO FEEDBACK_TRACKER(FEEDBACKID,PROJECTID,PARAMETERID,FEEDBACK_VALUE,WEIGHTAGE,FEEDBACK_DATE,"
				+ "REPORTER_RESOURCE_ID,REPORTER_COMPANY_ID,REPORTER_TYPE,REPORTEE_ID) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		
		try{
			jdbcTemplate.update(feedBack_SQL,new Object[] {fbk.getFeedbackId(),fbk.getProjectId(),fbk.getFeedback_value(),fbk.getWeightage(),
					fbk.getFeedback_Date(),fbk.getReporter_resource_Id(),fbk.getReporter_company_Id(),fbk.getReporter_Type(),fbk.getReportee()});
		  }
		catch (DataAccessException dae) {
			logger.error("Exception in capFeedbackTrackDetails");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		return feebkdetail;

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
		String getFeedBackperProject_SQL ="SELECT * FROM hawkeye_schema.FEEDBACK_TRACKER WHERE REPORTER_TYPE = ? AND PROJECTID = ?";
		try{
			feebkdetail = jdbcTemplate.queryForObject(getFeedBackperProject_SQL, new Object[] {reporterType,projectId}, rowMapper);
		   }
		
		catch (DataAccessException dae) {
			logger.error("Exception in capFeedbackTrackDetails");
			throw new FeedbackTrackException("Exception in Feedbacktrack Details", dae);
		
	     }
		System.out.println(feebkdetail.getFeedbackId());
		System.out.println(feebkdetail.getFeedback_Date());
		return feebkdetail;
	}

	@Override
	public List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int projectId) {
		// TODO Auto-generated method stub
		List<FeedbackDetails> feebkdetailList = new ArrayList<FeedbackDetails>();
		String getFeedBackperProject_SQL ="Select f.* from FeedbackTracker f, Project p where f.projectId = p.projectId and p.programId=? and f.reporterType=?";
		try{
			List<Map<String, Object>> feedbackList = jdbcTemplate.queryForList(getFeedBackperProject_SQL,new Object[] {reporterType,projectId});
			
			if(feedbackList  != null && feedbackList.size() >0)  
			{
				for (Map<String, Object> row : feedbackList) {
					
					FeedbackDetails feedback = new FeedbackDetails();
					logger.info(" FeedbackDetails:"+row.get("projectId"));
		            feedback.setFeedbackId((Integer)row.get("feedbackId"));
					feedback.setParamaterId((Integer)row.get("paramaterId"));
					feedback.setFeedback_value((Integer)row.get("feedback_value"));
					feedback.setWeightage((Integer)row.get("weightage"));
					feedback.setFeedback_Date((String)row.get("feedback_Date"));
					feedback.setReporter_resource_Id((Integer)row.get("reporter_resource_Id"));
					feedback.setReporter_company_Id((Integer)row.get("reporter_company_Id"));
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

}
