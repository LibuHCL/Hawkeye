
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueCreation implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
