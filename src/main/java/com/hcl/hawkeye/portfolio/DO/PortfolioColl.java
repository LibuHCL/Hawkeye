package com.hcl.hawkeye.portfolio.DO;

import java.util.List;

public class PortfolioColl {

	private String _name;
	private int _id;
	private int _count;
	private List<Cost> _cost;
	private ValueCreation _valueCreation;

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @param _name
	 *            the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}

	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * @return the _count
	 */
	public int get_count() {
		return _count;
	}

	/**
	 * @param _count
	 *            the _count to set
	 */
	public void set_count(int _count) {
		this._count = _count;
	}

	/**
	 * @return the _cost
	 */
	public List<Cost> get_cost() {
		return _cost;
	}

	/**
	 * @param _cost
	 *            the _cost to set
	 */
	public void set_cost(List<Cost> _cost) {
		this._cost = _cost;
	}

	/**
	 * @return the _valueCreation
	 */
	public ValueCreation get_valueCreation() {
		return _valueCreation;
	}

	/**
	 * @param _valueCreation
	 *            the _valueCreation to set
	 */
	public void set_valueCreation(ValueCreation _valueCreation) {
		this._valueCreation = _valueCreation;
	}

}
