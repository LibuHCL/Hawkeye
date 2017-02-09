package com.hcl.hawkeye.valueaddmanagement.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueCreationQuarterly implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	  public String getName() { return this.name; }

	  public void setName(String name) { this.name = name; }

	  private ArrayList<String> series;

	  public ArrayList<String> getSeries() { return this.series; }

	  public void setSeries(ArrayList<String> series) { this.series = series; }

	  private ArrayList<String> labels;

	  public ArrayList<String> getLabels() { return this.labels; }

	  public void setLabels(ArrayList<String> labels) { this.labels = labels; }

	  private ArrayList<ArrayList<Integer>> graphdata;

	  public ArrayList<ArrayList<Integer>> getGraphdata() { return this.graphdata; }

	  public void setGraphdata(ArrayList<ArrayList<Integer>> graphdata) { this.graphdata = graphdata; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graphdata == null) ? 0 : graphdata.hashCode());
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueCreationQuarterly other = (ValueCreationQuarterly) obj;
		if (graphdata == null) {
			if (other.graphdata != null)
				return false;
		} else if (!graphdata.equals(other.graphdata))
			return false;
		if (labels == null) {
			if (other.labels != null)
				return false;
		} else if (!labels.equals(other.labels))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
		} else if (!series.equals(other.series))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValueCreationQuarterly [name=" + name + ", series=" + series + ", labels=" + labels + ", graphdata="
				+ graphdata + "]";
	}
	
	  

}
