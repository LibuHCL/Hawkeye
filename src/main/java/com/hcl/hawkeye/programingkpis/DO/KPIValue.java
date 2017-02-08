package com.hcl.hawkeye.programingkpis.DO;

import java.io.Serializable;
import java.util.List;

public class KPIValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;
	private List<Integer> _graphdata;
	private List<String> _labels;

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @param _name
	 *            the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}

	/**
	 * @return the _graphdata
	 */
	public List<Integer> get_graphdata() {
		return _graphdata;
	}

	/**
	 * @param _graphdata
	 *            the _graphdata to set
	 */
	public void set_graphdata(List<Integer> _graphdata) {
		this._graphdata = _graphdata;
	}

	/**
	 * @return the _labels
	 */
	public List<String> get_labels() {
		return _labels;
	}

	/**
	 * @param _labels
	 *            the _labels to set
	 */
	public void set_labels(List<String> _labels) {
		this._labels = _labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_graphdata == null) ? 0 : _graphdata.hashCode());
		result = prime * result + ((_labels == null) ? 0 : _labels.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KPIValue [_name=" + _name + ", _graphdata=" + _graphdata + ", _labels=" + _labels + "]";
	}

}
