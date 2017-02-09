package com.hcl.hawkeye.programingkpis.DO;

import java.io.Serializable;
import java.util.List;

public class KPIValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;
	private List<Integer[]> _graphdata;
	private List<Integer> _graphdataOfVelocity;
	private List<Double> _graphdataOfIdeas;
	private List<String> _labels;
	private List<String> _series;
	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}
	/**
	 * @param _name the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}
	/**
	 * @return the _graphdata
	 */
	public List<Integer[]> get_graphdata() {
		return _graphdata;
	}
	/**
	 * @param _graphdata the _graphdata to set
	 */
	public void set_graphdata(List<Integer[]> _graphdata) {
		this._graphdata = _graphdata;
	}
	/**
	 * @return the _graphdataOfVelocity
	 */
	public List<Integer> get_graphdataOfVelocity() {
		return _graphdataOfVelocity;
	}
	/**
	 * @param _graphdataOfVelocity the _graphdataOfVelocity to set
	 */
	public void set_graphdataOfVelocity(List<Integer> _graphdataOfVelocity) {
		this._graphdataOfVelocity = _graphdataOfVelocity;
	}
	/**
	 * @return the _graphdataOfIdeas
	 */
	public List<Double> get_graphdataOfIdeas() {
		return _graphdataOfIdeas;
	}
	/**
	 * @param _graphdataOfIdeas the _graphdataOfIdeas to set
	 */
	public void set_graphdataOfIdeas(List<Double> _graphdataOfIdeas) {
		this._graphdataOfIdeas = _graphdataOfIdeas;
	}
	/**
	 * @return the _labels
	 */
	public List<String> get_labels() {
		return _labels;
	}
	/**
	 * @param _labels the _labels to set
	 */
	public void set_labels(List<String> _labels) {
		this._labels = _labels;
	}
	/**
	 * @return the _series
	 */
	public List<String> get_series() {
		return _series;
	}
	/**
	 * @param _series the _series to set
	 */
	public void set_series(List<String> _series) {
		this._series = _series;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_graphdata == null) ? 0 : _graphdata.hashCode());
		result = prime * result + ((_graphdataOfIdeas == null) ? 0 : _graphdataOfIdeas.hashCode());
		result = prime * result + ((_graphdataOfVelocity == null) ? 0 : _graphdataOfVelocity.hashCode());
		result = prime * result + ((_labels == null) ? 0 : _labels.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		result = prime * result + ((_series == null) ? 0 : _series.hashCode());
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
		if (_graphdata == null) {
			if (other._graphdata != null)
				return false;
		} else if (!_graphdata.equals(other._graphdata))
			return false;
		if (_graphdataOfIdeas == null) {
			if (other._graphdataOfIdeas != null)
				return false;
		} else if (!_graphdataOfIdeas.equals(other._graphdataOfIdeas))
			return false;
		if (_graphdataOfVelocity == null) {
			if (other._graphdataOfVelocity != null)
				return false;
		} else if (!_graphdataOfVelocity.equals(other._graphdataOfVelocity))
			return false;
		if (_labels == null) {
			if (other._labels != null)
				return false;
		} else if (!_labels.equals(other._labels))
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		if (_series == null) {
			if (other._series != null)
				return false;
		} else if (!_series.equals(other._series))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KPIValue [_name=" + _name + ", _graphdata=" + _graphdata + ", _graphdataOfVelocity="
				+ _graphdataOfVelocity + ", _graphdataOfIdeas=" + _graphdataOfIdeas + ", _labels=" + _labels
				+ ", _series=" + _series + "]";
	}
	
	
	
}
