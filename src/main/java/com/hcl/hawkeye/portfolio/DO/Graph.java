package com.hcl.hawkeye.portfolio.DO;

import java.util.ArrayList;

public class Graph {	
	ArrayList<Double> graphData;
	ArrayList<String> labels;
	ArrayList<Integer> graphDataInt;
	public ArrayList<Integer> getGraphDataInt() {
		return graphDataInt;
	}
	public void setGraphDataInt(ArrayList<Integer> graphDataInt) {
		this.graphDataInt = graphDataInt;
	}
	public ArrayList<Double> getGraphData() {
		return graphData;
	}
	public void setGraphData(ArrayList<Double> graphData) {
		this.graphData = graphData;
	}
	public ArrayList<String> getLabels() {
		return labels;
	}
	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}	
}
