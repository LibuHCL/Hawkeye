package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class SprintDetailsOfProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currentSprint;
	private int noOfSprintPerProject;

	/**
	 * @return the currentSprint
	 */
	public String getCurrentSprint() {
		return currentSprint;
	}

	/**
	 * @param currentSprint
	 *            the currentSprint to set
	 */
	public void setCurrentSprint(String currentSprint) {
		this.currentSprint = currentSprint;
	}

	/**
	 * @return the noOfSprintPerProject
	 */
	public int getNoOfSprintPerProject() {
		return noOfSprintPerProject;
	}

	/**
	 * @param noOfSprintPerProject
	 *            the noOfSprintPerProject to set
	 */
	public void setNoOfSprintPerProject(int noOfSprintPerProject) {
		this.noOfSprintPerProject = noOfSprintPerProject;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentSprint == null) ? 0 : currentSprint.hashCode());
		result = prime * result + noOfSprintPerProject;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SprintDetailsOfProject [currentSprint=" + currentSprint + ", noOfSprintPerProject="
				+ noOfSprintPerProject + "]";
	}

	

	
}
