package com.hcl.hawkeye.valueaddmanagement.DO;

import java.util.ArrayList;

public class Kpi {

	  private String _name;

	  public String getName() { return this._name; }

	  public void setName(String _name) { this._name = _name; }

	  private ArrayList<Object> _graphdata;

	  public ArrayList<Object> getGraphdata() { return this._graphdata; }

	  public void setGraphdata(ArrayList<Object> _graphdata) { this._graphdata = _graphdata; }

	  private ArrayList<String> _labels;

	  public ArrayList<String> getLabels() { return this._labels; }

	  public void setLabels(ArrayList<String> _labels) { this._labels = _labels; }

	  private ArrayList<String> _series;

	  public ArrayList<String> getSeries() { return this._series; }

	  public void setSeries(ArrayList<String> _series) { this._series = _series; }

}
