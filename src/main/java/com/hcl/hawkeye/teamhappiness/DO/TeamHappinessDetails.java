/**
 * 
 */
package com.hcl.hawkeye.teamhappiness.DO;

import java.util.ArrayList;

import com.hcl.hawkeye.portfolio.DO.Project;

/**
 * @author HCL
 *
 */
public class TeamHappinessDetails {
	
	ArrayList<Integer> graphData;
	ArrayList<String> labels;

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

	
	public ArrayList<Integer> getGraphData() {
		return graphData;
	}

	public void setGraphData(ArrayList<Integer> graphData) {
		this.graphData = graphData;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public TeamHappinessDetails(Integer quarter, Project project, Integer average) {
		super();
		this.quarter = quarter;
		this.project = project;
		this.average = average;
	}

}
