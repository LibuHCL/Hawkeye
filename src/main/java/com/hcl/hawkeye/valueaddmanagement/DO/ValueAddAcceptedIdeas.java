package com.hcl.hawkeye.valueaddmanagement.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueAddAcceptedIdeas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _name;

	  public String getName() { return this._name; }

	  public void setName(String _name) { this._name = _name; }

	  private ArrayList<Double> _graphdata;

	  public ArrayList<Double> getGraphdata() { return this._graphdata; }

	  public void setGraphdata(ArrayList<Double> _graphdata) { this._graphdata = _graphdata; }

	  private ArrayList<String> _labels;

	  public ArrayList<String> getLabels() { return this._labels; }

	  public void setLabels(ArrayList<String> _labels) { this._labels = _labels; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_graphdata == null) ? 0 : _graphdata.hashCode());
		result = prime * result + ((_labels == null) ? 0 : _labels.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
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
		ValueAddAcceptedIdeas other = (ValueAddAcceptedIdeas) obj;
		if (_graphdata == null) {
			if (other._graphdata != null)
				return false;
		} else if (!_graphdata.equals(other._graphdata))
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
		return true;
	}

	@Override
	public String toString() {
		return "ValueAddAcceptedIdeas [_name=" + _name + ", _graphdata=" + _graphdata + ", _labels=" + _labels + "]";
	}
	  
	  
}
