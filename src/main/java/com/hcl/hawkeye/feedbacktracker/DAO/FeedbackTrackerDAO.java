package com.hcl.hawkeye.feedbacktracker.DAO;

import java.util.List;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.portfolio.DO.Graph;

public interface FeedbackTrackerDAO {

	int capFeedbackTrackDetails(FeedbackDetails feedbackdetails);
		// TODO Auto-generated method stub
	FeedbackDetails getFeedbackPerProject(String reporterType, int projectId);
	List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int projectId);
	Graph getnoofFeedBacksPerQtAtPerfolioLevel(int portfolioId, String repType);		
	}


