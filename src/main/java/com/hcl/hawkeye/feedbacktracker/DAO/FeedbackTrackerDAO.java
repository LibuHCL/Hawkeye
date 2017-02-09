package com.hcl.hawkeye.feedbacktracker.DAO;

import java.util.List;

import com.hcl.hawkeye.FeedbackTrackerDO.FeedbackDetails;

public interface FeedbackTrackerDAO {

	FeedbackDetails capFeedbackTrackDetails(FeedbackDetails feedbackdetails);
		// TODO Auto-generated method stub
	FeedbackDetails getFeedbackPerProject(String reporterType, int projectId);
	List<FeedbackDetails> getFeedbackPerProgram(String reporterType, int projectId);
		
	}

