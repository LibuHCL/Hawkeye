package com.hcl.hawkeye.batch.jira.DO;

import java.io.Serializable;

public class SprintIssues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int sprintId;
	private String issueId;
	private String issueTypeId;
	private String issueType;
	private String priorityId;
	private String priorityName;
	private int projectId;
	private int toolProjectId;
	private double storyPoint;
	private String issueStartDate;
	private String issueEndDate;
	private String IssueStatus;

	/**
	 * @return the sprintId
	 */
	public int getSprintId() {
		return sprintId;
	}

	/**
	 * @param sprintId
	 *            the sprintId to set
	 */
	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}

	/**
	 * @return the issueId
	 */
	public String getIssueId() {
		return issueId;
	}

	/**
	 * @param issueId
	 *            the issueId to set
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	/**
	 * @return the issueTypeId
	 */
	public String getIssueTypeId() {
		return issueTypeId;
	}

	/**
	 * @param issueTypeId
	 *            the issueTypeId to set
	 */
	public void setIssueTypeId(String issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}

	/**
	 * @param issueType
	 *            the issueType to set
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	/**
	 * @return the priorityId
	 */
	public String getPriorityId() {
		return priorityId;
	}

	/**
	 * @param priorityId
	 *            the priorityId to set
	 */
	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	/**
	 * @return the priorityName
	 */
	public String getPriorityName() {
		return priorityName;
	}

	/**
	 * @param priorityName
	 *            the priorityName to set
	 */
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the toolProjectId
	 */
	public int getToolProjectId() {
		return toolProjectId;
	}

	/**
	 * @param toolProjectId
	 *            the toolProjectId to set
	 */
	public void setToolProjectId(int toolProjectId) {
		this.toolProjectId = toolProjectId;
	}

	/**
	 * @return the storyPoint
	 */
	public double getStoryPoint() {
		return storyPoint;
	}

	/**
	 * @param d
	 *            the storyPoint to set
	 */
	public void setStoryPoint(double d) {
		this.storyPoint = d;
	}

	/**
	 * @return the issueStartDate
	 */
	public String getIssueStartDate() {
		return issueStartDate;
	}

	/**
	 * @param issueStartDate
	 *            the issueStartDate to set
	 */
	public void setIssueStartDate(String issueStartDate) {
		this.issueStartDate = issueStartDate;
	}

	/**
	 * @return the issueEndDate
	 */
	public String getIssueEndDate() {
		return issueEndDate;
	}

	/**
	 * @param issueEndDate
	 *            the issueEndDate to set
	 */
	public void setIssueEndDate(String issueEndDate) {
		this.issueEndDate = issueEndDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SprintIssues [sprintId=" + sprintId + ", issueId=" + issueId + ", issueTypeId=" + issueTypeId
				+ ", issueType=" + issueType + ", priorityId=" + priorityId + ", priorityName=" + priorityName
				+ ", projectId=" + projectId + ", toolProjectId=" + toolProjectId + ", storyPoint=" + storyPoint
				+ ", issueStartDate=" + issueStartDate + ", issueEndDate=" + issueEndDate + ", IssueStatus="
				+ IssueStatus + "]";
	}

	/**
	 * @return the issueStatus
	 */
	public String getIssueStatus() {
		return IssueStatus;
	}

	/**
	 * @param issueStatus
	 *            the issueStatus to set
	 */
	public void setIssueStatus(String issueStatus) {
		IssueStatus = issueStatus;
	}

}
