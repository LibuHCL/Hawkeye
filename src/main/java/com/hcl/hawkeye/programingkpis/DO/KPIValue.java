package com.hcl.hawkeye.programingkpis.DO;

import java.io.Serializable;
import java.util.List;

public class KPIValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String          name;
	private List<Integer[]> graphdata;
	private List<Integer>   graphdataOfVelocity;
	private List<Double>    graphdataOfIdeas;
	private List<String>    labels;
	private List<String>    series;
	private List<Long>      longraphData;
	private String          type;
	private List<String>    color;
	private List<String>    bgcolor;
	private String          xlabel;
	private String          ylabel;
	
	public List<String> getColor() {
		return color;
	}
	public void setColor(List<String> color) {
		this.color = color;
	}
	public List<String> getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(List<String> bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getXlabel() {
		return xlabel;
	}
	public void setXlabel(String xlabel) {
		this.xlabel = xlabel;
	}
	public String getYlabel() {
		return ylabel;
	}
	public void setYlabel(String ylabel) {
		this.ylabel = ylabel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the graphdata
	 */
	public List<Integer[]> getGraphdata() {
		return graphdata;
	}
	/**
	 * @param graphdata the graphdata to set
	 */
	public void setGraphdata(List<Integer[]> graphdata) {
		this.graphdata = graphdata;
	}
	/**
	 * @return the graphdataOfVelocity
	 */
	public List<Integer> getGraphdataOfVelocity() {
		return graphdataOfVelocity;
	}
	/**
	 * @param graphdataOfVelocity the graphdataOfVelocity to set
	 */
	public void setGraphdataOfVelocity(List<Integer> graphdataOfVelocity) {
		this.graphdataOfVelocity = graphdataOfVelocity;
	}
	/**
	 * @return the graphdataOfIdeas
	 */
	public List<Double> getGraphdataOfIdeas() {
		return graphdataOfIdeas;
	}
	/**
	 * @param graphdataOfIdeas the graphdataOfIdeas to set
	 */
	public void setGraphdataOfIdeas(List<Double> graphdataOfIdeas) {
		this.graphdataOfIdeas = graphdataOfIdeas;
	}
	/**
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	/**
	 * @return the series
	 */
	public List<String> getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(List<String> series) {
		this.series = series;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graphdata == null) ? 0 : graphdata.hashCode());
		result = prime * result + ((graphdataOfIdeas == null) ? 0 : graphdataOfIdeas.hashCode());
		result = prime * result + ((graphdataOfVelocity == null) ? 0 : graphdataOfVelocity.hashCode());
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KPIValue other = (KPIValue) obj;
		if (graphdata == null) {
			if (other.graphdata != null)
				return false;
		} else if (!graphdata.equals(other.graphdata))
			return false;
		if (graphdataOfIdeas == null) {
			if (other.graphdataOfIdeas != null)
				return false;
		} else if (!graphdataOfIdeas.equals(other.graphdataOfIdeas))
			return false;
		if (graphdataOfVelocity == null) {
			if (other.graphdataOfVelocity != null)
				return false;
		} else if (!graphdataOfVelocity.equals(other.graphdataOfVelocity))
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KPIValue [name=" + name + ", graphdata=" + graphdata + ", graphdataOfVelocity=" + graphdataOfVelocity
				+ ", graphdataOfIdeas=" + graphdataOfIdeas + ", labels=" + labels + ", series=" + series + "]";
	}
	/**
	 * @return the longraphData
	 */
	public List<Long> getLongraphData() {
		return longraphData;
	}
	/**
	 * @param longraphData the longraphData to set
	 */
	public void setLongraphData(List<Long> longraphData) {
		this.longraphData = longraphData;
	}
	
	
}
