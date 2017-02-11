package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.List;

public class ProjectDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLast ? 1231 : 1237);
		result = prime * result + maxResults;
		result = prime * result + startAt;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		ProjectDetails other = (ProjectDetails) obj;
		if (isLast != other.isLast)
			return false;
		if (maxResults != other.maxResults)
			return false;
		if (startAt != other.startAt)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectDetails [maxResults=" + maxResults + ", startAt=" + startAt + ", isLast=" + isLast + ", values="
				+ values + "]";
	}

	
}
