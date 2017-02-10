package com.hcl.hawkeye.programingkpis.DO;

import java.io.Serializable;
import java.util.List;

public class KPIType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String programName;
	private int programId;
	private List<KPIValue> kpis;
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the programId
	 */
	public int getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	/**
	 * @return the kpis
	 */
	public List<KPIValue> getKpis() {
		return kpis;
	}
	/**
	 * @param kpis the kpis to set
	 */
	public void setKpis(List<KPIValue> kpis) {
		this.kpis = kpis;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kpis == null) ? 0 : kpis.hashCode());
		result = prime * result + programId;
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KPIType other = (KPIType) obj;
		if (kpis == null) {
			if (other.kpis != null)
				return false;
		} else if (!kpis.equals(other.kpis))
			return false;
		if (programId != other.programId)
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KPIType [programName=" + programName + ", programId=" + programId + ", kpis=" + kpis + "]";
	}

	
}
