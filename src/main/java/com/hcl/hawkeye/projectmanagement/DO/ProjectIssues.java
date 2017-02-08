package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.List;

public class ProjectIssues implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String expand;
	private int startAt;
	private int maxResults;
	private int total;
	private List<Issues> issues;

	/**
	 * @return the expand
	 */
	public String getExpand() {
		return expand;
	}

	/**
	 * @param expand
	 *            the expand to set
	 */
	public void setExpand(String expand) {
		this.expand = expand;
	}

	/**
	 * @return the startAt
	 */
	public int getStartAt() {
		return startAt;
	}

	/**
	 * @param startAt
	 *            the startAt to set
	 */
	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the issues
	 */
	public List<Issues> getIssues() {
		return issues;
	}

	/**
	 * @param issues
	 *            the issues to set
	 */
	public void setIssues(List<Issues> issues) {
		this.issues = issues;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expand == null) ? 0 : expand.hashCode());
		result = prime * result + ((issues == null) ? 0 : issues.hashCode());
		result = prime * result + maxResults;
		result = prime * result + startAt;
		result = prime * result + total;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectIssues [expand=" + expand + ", startAt=" + startAt + ", maxResults=" + maxResults + ", total="
				+ total + ", issues=" + issues + "]";
	}


}
