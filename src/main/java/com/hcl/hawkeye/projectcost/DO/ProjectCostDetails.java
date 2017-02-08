package com.hcl.hawkeye.projectcost.DO;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

public class ProjectCostDetails {

	private BigInteger projectID;
	private Double plannedCost;
	private Double actualCost;
	private Timestamp captureDate;

	public BigInteger getProjectID() {
		return projectID;
	}

	public void setProjectID(BigInteger projectID) {
		this.projectID = projectID;
	}

	public Double getPlannedCost() {
		return plannedCost;
	}

	public void setPlannedCost(Double plannedCost) {
		this.plannedCost = plannedCost;
	}

	public Double getActualCost() {
		return actualCost;
	}

	public void setActualCost(Double actualCost) {
		this.actualCost = actualCost;
	}
	
	public Timestamp getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Timestamp captureDate) {
		this.captureDate = captureDate;
	}

}
