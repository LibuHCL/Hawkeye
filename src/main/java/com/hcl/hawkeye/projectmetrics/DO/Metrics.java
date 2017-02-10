package com.hcl.hawkeye.projectmetrics.DO;

import java.util.ArrayList;
import java.util.List;

public class Metrics {
	
	private String key;	

    private ArrayList<Double> graphdata;
    
    private ArrayList<Integer> graphdata1;
    
    private Integer Internal_Defects;
    
    private Integer Valid_Internal_Defects;
    
    private Integer UAT_Issues;
    
    private Integer Leaked_UAT_Issues;

    private Double Leakage;
    
    private ArrayList<String> labels;
    
    private ArrayList<Integer>series;
    
    private String value;
    
    private String symbol;
    
    private String manDaysCount;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ArrayList<Double> getGraphdata() {
		return graphdata;
	}

	public void setGraphdata(ArrayList<Double> graphdata) {
		this.graphdata = graphdata;
	}

	public Integer getInternal_Defects() {
		return Internal_Defects;
	}

	public void setInternal_Defects(Integer internal_Defects) {
		Internal_Defects = internal_Defects;
	}

	public Integer getValid_Internal_Defects() {
		return Valid_Internal_Defects;
	}

	public void setValid_Internal_Defects(Integer valid_Internal_Defects) {
		Valid_Internal_Defects = valid_Internal_Defects;
	}

	public Integer getUAT_Issues() {
		return UAT_Issues;
	}

	public void setUAT_Issues(Integer uAT_Issues) {
		UAT_Issues = uAT_Issues;
	}

	public Integer getLeaked_UAT_Issues() {
		return Leaked_UAT_Issues;
	}

	public void setLeaked_UAT_Issues(Integer leaked_UAT_Issues) {
		Leaked_UAT_Issues = leaked_UAT_Issues;
	}

	public Double getLeakage() {
		return Leakage;
	}

	public void setLeakage(Double leakage) {
		Leakage = leakage;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public ArrayList<Integer> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Integer> series) {
		this.series = series;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getManDaysCount() {
		return manDaysCount;
	}

	public void setManDaysCount(String manDaysCount) {
		this.manDaysCount = manDaysCount;
	}

	public ArrayList<Integer> getGraphdata1() {
		return graphdata1;
	}

	public void setGraphdata1(ArrayList<Integer> graphdata1) {
		this.graphdata1 = graphdata1;
	}
    
    
}
