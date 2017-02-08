package com.hcl.hawkeye.codequality.DO;

import java.io.Serializable;

public class CodeQuality implements Serializable {
	
	private long blockerViolations;
	private int technicalDebt;
	private int redAmberGreenStatus;
	
	public long getBlockerViolations() {
		return blockerViolations;
	}
	public void setBlockerViolations(long blockerViolations) {
		this.blockerViolations = blockerViolations;
	}
	public int getTechnicalDebt() {
		return technicalDebt;
	}
	public void setTechnicalDebt(int technicalDebt) {
		this.technicalDebt = technicalDebt;
	}
	public int getRedAmberGreenStatus() {
		return redAmberGreenStatus;
	}
	public void setRedAmberGreenStatus(int redAmberGreenStatus) {
		this.redAmberGreenStatus = redAmberGreenStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (blockerViolations ^ (blockerViolations >>> 32));
		result = prime * result + redAmberGreenStatus;
		result = prime * result + technicalDebt;
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
		CodeQuality other = (CodeQuality) obj;
		if (blockerViolations != other.blockerViolations)
			return false;
		if (redAmberGreenStatus != other.redAmberGreenStatus)
			return false;
		if (technicalDebt != other.technicalDebt)
			return false;
		return true;
	}
	
	
	

}
