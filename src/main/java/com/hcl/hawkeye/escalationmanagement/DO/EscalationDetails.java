package com.hcl.hawkeye.escalationmanagement.DO;

import java.util.ArrayList;

public class EscalationDetails {
	
	ArrayList<Integer> graphData;
	ArrayList<String> labels;
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
}
