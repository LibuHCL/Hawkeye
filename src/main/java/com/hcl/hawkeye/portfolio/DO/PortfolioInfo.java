package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;

public class PortfolioInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quarter;
	private String year;
	private double actualCost;
	private double plannedCost;
	private double roi;
	
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public double getActualCost() {
		return actualCost;
	}
	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}
	public double getPlannedCost() {
		return plannedCost;
	}
	public void setPlannedCost(double plannedCost) {
		this.plannedCost = plannedCost;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	
	
	
}
