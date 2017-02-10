/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.DO;

import java.util.ArrayList;

/**
 * @author HCL
 *
 */
public class Result {

	private ArrayList<Metrics> metrics;

	private String programId;

	private String programName;

	public ArrayList<Metrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(ArrayList<Metrics> metrics) {
		this.metrics = metrics;
	}

	public String get_programId() {
		return programId;
	}

	public void set_programId(String programId) {
		this.programId = programId;
	}

	public String get_programName() {
		return programName;
	}

	public void set_programName(String programName) {
		this.programName = programName;
	}

}
