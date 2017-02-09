package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class DefectTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int internalDefects;
	private int validInternalDefects;
	private int uatDefects;
	private int defectLekage;
	/**
	 * @return the internalDefects
	 */
	public int getInternalDefects() {
		return internalDefects;
	}
	/**
	 * @param internalDefects the internalDefects to set
	 */
	public void setInternalDefects(int internalDefects) {
		this.internalDefects = internalDefects;
	}
	/**
	 * @return the validInternalDefects
	 */
	public int getValidInternalDefects() {
		return validInternalDefects;
	}
	/**
	 * @param validInternalDefects the validInternalDefects to set
	 */
	public void setValidInternalDefects(int validInternalDefects) {
		this.validInternalDefects = validInternalDefects;
	}
	/**
	 * @return the uatDefects
	 */
	public int getUatDefects() {
		return uatDefects;
	}
	/**
	 * @param uatDefects the uatDefects to set
	 */
	public void setUatDefects(int uatDefects) {
		this.uatDefects = uatDefects;
	}
	/**
	 * @return the defectLekage
	 */
	public int getDefectLekage() {
		return defectLekage;
	}
	/**
	 * @param defectLekage the defectLekage to set
	 */
	public void setDefectLekage(int defectLekage) {
		this.defectLekage = defectLekage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + defectLekage;
		result = prime * result + internalDefects;
		result = prime * result + uatDefects;
		result = prime * result + validInternalDefects;
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
		DefectTypes other = (DefectTypes) obj;
		if (defectLekage != other.defectLekage)
			return false;
		if (internalDefects != other.internalDefects)
			return false;
		if (uatDefects != other.uatDefects)
			return false;
		if (validInternalDefects != other.validInternalDefects)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DefectTypes [internalDefects=" + internalDefects + ", validInternalDefects=" + validInternalDefects
				+ ", uatDefects=" + uatDefects + ", defectLekage=" + defectLekage + "]";
	}

	
}
