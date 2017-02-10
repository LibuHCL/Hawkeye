package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;

public class PortfolioInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long quarter;
	private Long year;
	private double actualCost;
	private double plannedCost;
	private double roi;
	private Integer portfolioId;
	
	public Long getQuarter() {
		return quarter;
	}
	public void setQuarter(Long quarter) {
		this.quarter = quarter;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
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
	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	
}
