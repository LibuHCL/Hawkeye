package com.hcl.hawkeye.batch.jira.DO;

import java.io.Serializable;

public class SprintIssues implements Serializable{

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
	private int storyPoint;
	/**
	 * @return the sprintId
	 */
	public int getSprintId() {
		return sprintId;
	}
	/**
	 * @param sprintId the sprintId to set
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
	 * @param string the issueId to set
	 */
	public void setIssueId(String string) {
		this.issueId = string;
	}
	/**
	 * @return the issueTypeId
	 */
	public String getIssueTypeId() {
		return issueTypeId;
	}
	/**
	 * @param string the issueTypeId to set
	 */
	public void setIssueTypeId(String string) {
		this.issueTypeId = string;
	}
	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}
	/**
	 * @param issueType the issueType to set
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
	 * @param string the priorityId to set
	 */
	public void setPriorityId(String string) {
		this.priorityId = string;
	}
	/**
	 * @return the priorityName
	 */
	public String getPriorityName() {
		return priorityName;
	}
	/**
	 * @param priorityName the priorityName to set
	 */
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getToolProjectId() {
		return toolProjectId;
	}
	public void setToolProjectId(int toolProjectId) {
		this.toolProjectId = toolProjectId;
	}
	
	
	public int getStoryPoint() {
		return storyPoint;
	}
	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SprintIssues [sprintId=" + sprintId + ", issueId=" + issueId + ", issueTypeId=" + issueTypeId
				+ ", issueType=" + issueType + ", priorityId=" + priorityId
				+ ", priorityName=" + priorityName + ", toolProjectId=" + toolProjectId+", storyPoint=" + storyPoint + "]";
	}
	
	

}
