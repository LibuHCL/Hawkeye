package com.hcl.hawkeye.projectmetrics.DO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetricsValueCreation {
	
	private ArrayList<String> series;
	
	private ArrayList<ArrayList<Integer>>  linedata;
	
	public ArrayList<String> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<String> series) {
		this.series = series;
	}


	public ArrayList<ArrayList<Integer>>  getLinedata() {
		return linedata;
	}
	public void setLinedata(ArrayList<ArrayList<Integer>>  linedata) {
		this.linedata = linedata;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [linedata = "+linedata+", series = "+series+"]";
    }

}
