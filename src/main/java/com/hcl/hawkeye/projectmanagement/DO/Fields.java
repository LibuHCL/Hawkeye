package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class Fields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IssueType issuetype;
	private PriorityOfIssue priority;
	private String resolutiondate;
	private String created;
	private StatusOfIssue status;
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
	/**
	 * @return the resolutiondate
	 */
	public String getResolutiondate() {
		return resolutiondate;
	}
	/**
	 * @param resolutiondate the resolutiondate to set
	 */
	public void setResolutiondate(String resolutiondate) {
		this.resolutiondate = resolutiondate;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * @return the status
	 */
	public StatusOfIssue getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusOfIssue status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((issuetype == null) ? 0 : issuetype.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((resolutiondate == null) ? 0 : resolutiondate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fields other = (Fields) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (issuetype == null) {
			if (other.issuetype != null)
				return false;
		} else if (!issuetype.equals(other.issuetype))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (resolutiondate == null) {
			if (other.resolutiondate != null)
				return false;
		} else if (!resolutiondate.equals(other.resolutiondate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fields [issuetype=" + issuetype + ", priority=" + priority + ", resolutiondate=" + resolutiondate
				+ ", created=" + created + ", status=" + status + "]";
	}
	
	
	
}
