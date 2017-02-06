package com.hcl.hawkeye.projectmanagement.DO;

import java.util.List;

public class ProjectDetails {

	private int maxResults;
	private int startAt;
	private boolean isLast;
	private List<ProjectValues> values;

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
	 * @return the isLast
	 */
	public boolean isLast() {
		return isLast;
	}

	/**
	 * @param isLast
	 *            the isLast to set
	 */
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	/**
	 * @return the values
	 */
	public List<ProjectValues> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<ProjectValues> values) {
		this.values = values;
	}

}
