package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class Fields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IssueType issuetype;
	private PriorityOfIssue priority;
	/**
	 * @return the issuetype
	 */
	public IssueType getIssuetype() {
		return issuetype;
	}
	/**
	 * @param issuetype the issuetype to set
	 */
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	/**
	 * @return the priorityIssues
	 */
	public PriorityOfIssue getPriorityIssues() {
		return priority;
	}
	/**
	 * @param priorityIssues the priorityIssues to set
	 */
	public void setPriorityIssues(PriorityOfIssue priorityIssues) {
		this.priority = priorityIssues;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((issuetype == null) ? 0 : issuetype.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fields [issuetype=" + issuetype + ", priorityIssues=" + priority + "]";
	}
	
	
	
}