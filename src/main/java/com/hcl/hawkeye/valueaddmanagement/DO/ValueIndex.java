package com.hcl.hawkeye.valueaddmanagement.DO;

import java.util.ArrayList;

public class ValueIndex {
	
	private ArrayList<String> series;

	  public ArrayList<String> getSeries() { return this.series; }

	  public void setSeries(ArrayList<String> series) { this.series = series; }

	  private ArrayList<ArrayList<Integer>> linedata;

	  public ArrayList<ArrayList<Integer>> getLinedata() { return this.linedata; }

	  public void setLinedata(ArrayList<ArrayList<Integer>> linedata) { this.linedata = linedata; }

	  private ArrayList<String> labels;

	  public ArrayList<String> getLabels() { return this.labels; }

	  public void setLabels(ArrayList<String> labels) { this.labels = labels; }

}
