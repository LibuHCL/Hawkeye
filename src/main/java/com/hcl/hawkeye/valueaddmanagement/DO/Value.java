package com.hcl.hawkeye.valueaddmanagement.DO;

import java.io.Serializable;

public class Value implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long valueId;
	
	private long projectId;
	
	private long programId;
	
	private long portfolioId;
	
	private String description;
	
	private String proposedDate;
	
	private String valueAddStatu;
	
	private String statusUpdateDate;
	
	private Double economicValue;

	public long getValueId() {
		return valueId;
	}

	public void setValueId(long valueId) {
		this.valueId = valueId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProposedDate() {
		return proposedDate;
	}

	public void setProposedDate(String proposedDate) {
		this.proposedDate = proposedDate;
	}

	public String getValueAddStatu() {
		return valueAddStatu;
	}

	public void setValueAddStatu(String valueAddStatu) {
		this.valueAddStatu = valueAddStatu;
	}

	public String getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(String statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public Double getEconomicValue() {
		return economicValue;
	}

	public void setEconomicValue(Double economicValue) {
		this.economicValue = economicValue;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (valueId ^ (valueId >>> 32));
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
		Value other = (Value) obj;
		if (valueId != other.valueId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Value [valueId=" + valueId + ", projectId=" + projectId + ", programId=" + programId + ", portfolioId="
				+ portfolioId + ", description=" + description + ", proposedDate=" + proposedDate + ", valueAddStatu="
				+ valueAddStatu + ", statusUpdateDate=" + statusUpdateDate + ", economicValue=" + economicValue
				+ ", getValueId()=" + getValueId() + ", getProjectId()=" + getProjectId() + ", getProgramId()="
				+ getProgramId() + ", getPortfolioId()=" + getPortfolioId() + ", getDescription()=" + getDescription()
				+ ", getProposedDate()=" + getProposedDate() + ", getValueAddStatu()=" + getValueAddStatu()
				+ ", getStatusUpdateDate()=" + getStatusUpdateDate() + ", getEconomicValue()=" + getEconomicValue()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
