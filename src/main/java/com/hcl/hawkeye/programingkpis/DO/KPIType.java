package com.hcl.hawkeye.programingkpis.DO;

import java.io.Serializable;
import java.util.List;

public class KPIType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _programName;
	private int _programId;
	private List<KPIValue> kpis;

	/**
	 * @return the _programName
	 */
	public String get_programName() {
		return _programName;
	}

	/**
	 * @param _programName
	 *            the _programName to set
	 */
	public void set_programName(String _programName) {
		this._programName = _programName;
	}

	/**
	 * @return the _programId
	 */
	public int get_programId() {
		return _programId;
	}

	/**
	 * @param _programId
	 *            the _programId to set
	 */
	public void set_programId(int _programId) {
		this._programId = _programId;
	}

	/**
	 * @return the kpis
	 */
	public List<KPIValue> getKpis() {
		return kpis;
	}

	/**
	 * @param kpis
	 *            the kpis to set
	 */
	public void setKpis(List<KPIValue> kpis) {
		this.kpis = kpis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _programId;
		result = prime * result + ((_programName == null) ? 0 : _programName.hashCode());
		result = prime * result + ((kpis == null) ? 0 : kpis.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "KPIType [_programName=" + _programName + ", _programId=" + _programId + ", kpis=" + kpis + "]";
	}

}
