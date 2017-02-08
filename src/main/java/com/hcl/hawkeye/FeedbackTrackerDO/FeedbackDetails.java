package com.hcl.hawkeye.FeedbackTrackerDO;

public class FeedbackDetails {
	private int feedbackId;
	private int projectId;
	private int paramaterId;
	private int feedback_value;
	private int weightage;
	private String feedback_Date;
	private int reporter_resource_Id;
	private int reporter_company_Id;
	private String reporter_Type;
	private int reportee;
	
	public FeedbackDetails(int feedbackId, int projectId, int paramaterId, int feedback_value, int weightage,
			String feedback_Date, int reporter_resource_Id, int reporter_company_Id, String reporter_Type,
			int reportee) {
		super();
		this.feedbackId = feedbackId;
		this.projectId = projectId;
		this.paramaterId = paramaterId;
		this.feedback_value = feedback_value;
		this.weightage = weightage;
		this.feedback_Date = feedback_Date;
		this.reporter_resource_Id = reporter_resource_Id;
		this.reporter_company_Id = reporter_company_Id;
		this.reporter_Type = reporter_Type;
		this.reportee = reportee;
	}
	
	public FeedbackDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getParamaterId() {
		return paramaterId;
	}

	public void setParamaterId(int paramaterId) {
		this.paramaterId = paramaterId;
	}

	public int getFeedback_value() {
		return feedback_value;
	}

	public void setFeedback_value(int feedback_value) {
		this.feedback_value = feedback_value;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

	public String getFeedback_Date() {
		return feedback_Date;
	}

	public void setFeedback_Date(String feedback_Date) {
		this.feedback_Date = feedback_Date;
	}

	public int getReporter_resource_Id() {
		return reporter_resource_Id;
	}

	public void setReporter_resource_Id(int reporter_resource_Id) {
		this.reporter_resource_Id = reporter_resource_Id;
	}

	public int getReporter_company_Id() {
		return reporter_company_Id;
	}

	public void setReporter_company_Id(int reporter_company_Id) {
		this.reporter_company_Id = reporter_company_Id;
	}

	public String getReporter_Type() {
		return reporter_Type;
	}

	public void setReporter_Type(String reporter_Type) {
		this.reporter_Type = reporter_Type;
	}

	public int getReportee() {
		return reportee;
	}

	public void setReportee(int reportee) {
		this.reportee = reportee;
	}
	
	
	

}
