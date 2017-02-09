/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DO;

import com.hcl.hawkeye.portfolio.DO.Project;

/**
 * @author HCL
 *
 */
public class TeamHappinessDetails {

	private Integer quarter;
	private Project project;
	private Integer average;

	public TeamHappinessDetails() {

	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getAverage() {
		return average;
	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public TeamHappinessDetails(Integer quarter, Project project, Integer average) {
		super();
		this.quarter = quarter;
		this.project = project;
		this.average = average;
	}

}
