package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.List;

public class DashBoardDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxResults;
	private int startAt;
	private boolean isLast;
	private List<DashBoardValues> values;

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
	public List<DashBoardValues> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<DashBoardValues> values) {
		this.values = values;
	}

}
