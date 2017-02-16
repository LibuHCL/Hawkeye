package com.hcl.hawkeye.feedbacktracker.service;

import java.util.List;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;
import com.hcl.hawkeye.portfolio.DO.Graph;

public interface FeedbackTrackerService {
	
	int capFeedbackTrackDetails(FeedbackDetails feedbackdetails);

	FeedbackDetails getFeedbackPerProject(String reporterType, int projectId);

	List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int programId);

	Graph getnoofFeedBacksPerQtAtPerfolioLevel(int portfolioId,	String reporterType);
	
	List<FeedbackDetails> getFeedBackCategoryId();

	List<FeedbackDetails> getFeedbackParameter(int category_Id);

}
