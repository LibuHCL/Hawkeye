package com.hcl.hawkeye.projectmetrics.DO;

public class ProjectMetricCost {

	private String _key;
	private Double _value;
	private String _postfix;
	private String _symbol;

	/**
	 * @return the _key
	 */
	public String get_key() {
		return _key;
	}

	/**
	 * @param _key
	 *            the _key to set
	 */
	public void set_key(String _key) {
		this._key = _key;
	}

	

	public Double get_value() {
		return _value;
	}

	public void set_value(Double _value) {
		this._value = _value;
	}

	/**
	 * @return the _postfix
	 */
	public String get_postfix() {
		return _postfix;
	}

	/**
	 * @param _postfix
	 *            the _postfix to set
	 */
	public void set_postfix(String _postfix) {
		this._postfix = _postfix;
	}

	/**
	 * @return the _symbol
	 */
	public String get_symbol() {
		return _symbol;
	}

	/**
	 * @param _symbol
	 *            the _symbol to set
	 */
	public void set_symbol(String _symbol) {
		this._symbol = _symbol;
	}

}
