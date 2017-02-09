package com.hcl.hawkeye.valueaddmanagement.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueCreation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> _series;

	public ArrayList<String> getSeries() {
		return this._series;
	}

	public void setSeries(ArrayList<String> _series) {
		this._series = _series;
	}

	private ArrayList<ArrayList<Integer>> _linedata;

	public ArrayList<ArrayList<Integer>> getLinedata() {
		return this._linedata;
	}

	public void setLinedata(ArrayList<ArrayList<Integer>> _linedata) {
		this._linedata = _linedata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_linedata == null) ? 0 : _linedata.hashCode());
		result = prime * result + ((_series == null) ? 0 : _series.hashCode());
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
		ValueCreation other = (ValueCreation) obj;
		if (_linedata == null) {
			if (other._linedata != null)
				return false;
		} else if (!_linedata.equals(other._linedata))
			return false;
		if (_series == null) {
			if (other._series != null)
				return false;
		} else if (!_series.equals(other._series))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValueCreation [_series=" + _series + ", _linedata=" + _linedata + "]";
	}

}
