package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.Map;

public class KanbanProDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int totalTickets;
	private Map<String, Integer> resolvedTicketsOfMonth;
	private int activeTickets;
	private Map<String, Integer> createdTicketsOfMonth;

	/**
	 * @return the totalTickets
	 */
	public int getTotalTickets() {
		return totalTickets;
	}

	/**
	 * @param totalTickets
	 *            the totalTickets to set
	 */
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	/**
	 * @return the resolvedTicketsOfMonth
	 */
	public Map<String, Integer> getResolvedTicketsOfMonth() {
		return resolvedTicketsOfMonth;
	}

	/**
	 * @param resolvedTicketsOfMonth
	 *            the resolvedTicketsOfMonth to set
	 */
	public void setResolvedTicketsOfMonth(Map<String, Integer> resolvedTicketsOfMonth) {
		this.resolvedTicketsOfMonth = resolvedTicketsOfMonth;
	}

	/**
	 * @return the activeTickets
	 */
	public int getActiveTickets() {
		return activeTickets;
	}

	/**
	 * @param activeTickets
	 *            the activeTickets to set
	 */
	public void setActiveTickets(int activeTickets) {
		this.activeTickets = activeTickets;
	}

	/**
	 * @return the createdTicketsOfMonth
	 */
	public Map<String, Integer> getCreatedTicketsOfMonth() {
		return createdTicketsOfMonth;
	}

	/**
	 * @param createdTicketsOfMonth
	 *            the createdTicketsOfMonth to set
	 */
	public void setCreatedTicketsOfMonth(Map<String, Integer> createdTicketsOfMonth) {
		this.createdTicketsOfMonth = createdTicketsOfMonth;
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
		result = prime * result + activeTickets;
		result = prime * result + ((createdTicketsOfMonth == null) ? 0 : createdTicketsOfMonth.hashCode());
		result = prime * result + ((resolvedTicketsOfMonth == null) ? 0 : resolvedTicketsOfMonth.hashCode());
		result = prime * result + totalTickets;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		KanbanProDetails other = (KanbanProDetails) obj;
		if (activeTickets != other.activeTickets)
			return false;
		if (createdTicketsOfMonth == null) {
			if (other.createdTicketsOfMonth != null)
				return false;
		} else if (!createdTicketsOfMonth.equals(other.createdTicketsOfMonth))
			return false;
		if (resolvedTicketsOfMonth == null) {
			if (other.resolvedTicketsOfMonth != null)
				return false;
		} else if (!resolvedTicketsOfMonth.equals(other.resolvedTicketsOfMonth))
			return false;
		if (totalTickets != other.totalTickets)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KanbanProDetails [totalTickets=" + totalTickets + ", resolvedTicketsOfMonth=" + resolvedTicketsOfMonth
				+ ", activeTickets=" + activeTickets + ", createdTicketsOfMonth=" + createdTicketsOfMonth + "]";
	}

}
