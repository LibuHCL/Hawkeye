package com.ing.hawkeye.portfolio.data;

import java.util.List;

public class Result {

	private List<Dates> dates;
	private Quaters quaters;

	/**
	 * @return the dates
	 */
	public List<Dates> getDates() {
		return dates;
	}

	/**
	 * @param dates
	 *            the dates to set
	 */
	public void setDates(List<Dates> dates) {
		this.dates = dates;
	}

	/**
	 * @return the quaters
	 */
	public Quaters getQuaters() {
		return quaters;
	}

	/**
	 * @param quaters
	 *            the quaters to set
	 */
	public void setQuaters(Quaters quaters) {
		this.quaters = quaters;
	}

}
