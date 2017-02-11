package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class VelocityOfProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sprintId;
	private String sprintName;
	private String sprintState;
	private double estimatedValue;
	private double completedValue;

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
	 * @return the sprintName
	 */
	public String getSprintName() {
		return sprintName;
	}

	/**
	 * @param sprintName
	 *            the sprintName to set
	 */
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	/**
	 * @return the sprintState
	 */
	public String getSprintState() {
		return sprintState;
	}

	/**
	 * @param sprintState
	 *            the sprintState to set
	 */
	public void setSprintState(String sprintState) {
		this.sprintState = sprintState;
	}

	/**
	 * @return the estimatedValue
	 */
	public double getEstimatedValue() {
		return estimatedValue;
	}

	/**
	 * @param estimatedValue
	 *            the estimatedValue to set
	 */
	public void setEstimatedValue(double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	/**
	 * @return the completedValue
	 */
	public double getCompletedValue() {
		return completedValue;
	}

	/**
	 * @param completedValue
	 *            the completedValue to set
	 */
	public void setCompletedValue(double completedValue) {
		this.completedValue = completedValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(completedValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(estimatedValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sprintId;
		result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
		result = prime * result + ((sprintState == null) ? 0 : sprintState.hashCode());
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
		VelocityOfProject other = (VelocityOfProject) obj;
		if (Double.doubleToLongBits(completedValue) != Double.doubleToLongBits(other.completedValue))
			return false;
		if (Double.doubleToLongBits(estimatedValue) != Double.doubleToLongBits(other.estimatedValue))
			return false;
		if (sprintId != other.sprintId)
			return false;
		if (sprintName == null) {
			if (other.sprintName != null)
				return false;
		} else if (!sprintName.equals(other.sprintName))
			return false;
		if (sprintState == null) {
			if (other.sprintState != null)
				return false;
		} else if (!sprintState.equals(other.sprintState))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VelocityOfProject [sprintId=" + sprintId + ", sprintName=" + sprintName + ", sprintState=" + sprintState
				+ ", estimatedValue=" + estimatedValue + ", completedValue=" + completedValue + "]";
	}

	
}
