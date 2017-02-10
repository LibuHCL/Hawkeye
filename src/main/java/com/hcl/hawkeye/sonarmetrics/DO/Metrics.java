/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.DO;

import java.util.ArrayList;

/**
 * @author HCL
 *
 */
public class Metrics {
	private ArrayList<Object> graphdata;

	private String key;

	private ArrayList<String> labels;

	public ArrayList<Object> getGraphdata() {
		return graphdata;
	}

	public String getKey() {
		return key;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setGraphdata(ArrayList<Object> graphdata) {
		this.graphdata = graphdata;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

}
