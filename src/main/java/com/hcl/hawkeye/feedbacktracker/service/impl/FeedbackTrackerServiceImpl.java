package com.hcl.hawkeye.feedbacktracker.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.feedbacktracker.DAO.FeedbackTrackerDAO;
import com.hcl.hawkeye.feedbacktracker.service.FeedbackTrackerService;
import com.hcl.hawkeye.portfolio.DO.Graph;

@Service
public class FeedbackTrackerServiceImpl implements FeedbackTrackerService {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackTrackerServiceImpl.class);
	
	@Autowired
	FeedbackTrackerDAO feedbkDao;
	@Override
	public int capFeedbackTrackDetails(FeedbackDetails feedbackdetails) {
		// TODO Auto-generated method stub
		
		logger.info("Inside capFeedbackTrackDetails method in FeedbackTrackerServiceImpl");
		return feedbkDao.capFeedbackTrackDetails(feedbackdetails);
	}
	@Override
	public FeedbackDetails getFeedbackPerProject(String reporterType, int projectId) {
		// TODO Auto-generated method stub
		logger.info("Inside getFeedbackPerQtProject method in FeedbackTrackerServiceImpl");
		return feedbkDao.getFeedbackPerProject(reporterType,projectId);
	}
	@Override
	public List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int programId) {
		// TODO Auto-generated method stub
		logger.info("Inside getFeedbackPerQtProject method in FeedbackTrackerServiceImpl");
		return feedbkDao.getFeedbackPerProgram(reporterType,programId);
		
	}
	
	@Override
	public Graph getnoofFeedBacksPerQtAtPerfolioLevel(int portfolioId,String reporterType) {
		logger.info("Inside getFeedbackPerQtProject method in FeedbackTrackerServiceImpl");
		return feedbkDao.getnoofFeedBacksPerQtAtPerfolioLevel( portfolioId, reporterType);
		
	}
	
	@Override
	public List<FeedbackDetails> getFeedBackCategoryId() {
		// TODO Auto-generated method stub
		logger.info("Inside getFeedBackCategoryId method in FeedbackTrackerServiceImpl");
		return feedbkDao.getFeedBackCategoryId();
		
	}
	@Override
	public List<FeedbackDetails> getFeedbackParameter(int category_Id) {
		// TODO Auto-generated method stub
		logger.info("Inside getFeedbackParameter method in FeedbackTrackerServiceImpl");
		return feedbkDao.getFeedbackParameter(category_Id);
	}

}
