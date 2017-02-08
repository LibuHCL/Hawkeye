package com.hcl.hawkeye.feedbacktracker.service;

import java.util.List;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;

public interface FeedbackTrackerService {
	
	FeedbackDetails capFeedbackTrackDetails(FeedbackDetails feedbackdetails);

	FeedbackDetails getFeedbackPerProject(String reporterType, int projectId);

	List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int projectId);

}
