package com.hcl.hawkeye.feedbacktracker.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.feedbacktracker.DAO.FeedbackTrackerDAO;
import com.hcl.hawkeye.feedbacktracker.service.FeedbackTrackerService;

@Service
public class FeedbackTrackerServiceImpl implements FeedbackTrackerService {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackTrackerServiceImpl.class);
	
	@Autowired
	FeedbackTrackerDAO feedbkDao;
	@Override
	public FeedbackDetails capFeedbackTrackDetails(FeedbackDetails feedbackdetails) {
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
	public List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int projectId) {
		// TODO Auto-generated method stub
		logger.info("Inside getFeedbackPerQtProject method in FeedbackTrackerServiceImpl");
		return feedbkDao.getFeedbackPerProgram(reporterType,projectId);
		
	}

}
