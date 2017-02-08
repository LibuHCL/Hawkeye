package com.hcl.hawkeye.projectmanagement.DO;

public class VelocityOfProject {

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
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (completedValue ^ (completedValue >>> 32));
		result = prime * result + (int) (estimatedValue ^ (estimatedValue >>> 32));
		result = prime * result + sprintId;
		result = prime * result + ((sprintName == null) ? 0 : sprintName.hashCode());
		result = prime * result + ((sprintState == null) ? 0 : sprintState.hashCode());
		return result;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VelocityOfProject [sprintId=" + sprintId + ", sprintName=" + sprintName + ", sprintState=" + sprintState
				+ ", estimatedValue=" + estimatedValue + ", completedValue=" + completedValue + "]";
	}

}
