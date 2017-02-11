package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class Sprints implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int sequence;
	private String name;
	private String state;
	private int linkedPagesCount;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the linkedPagesCount
	 */
	public int getLinkedPagesCount() {
		return linkedPagesCount;
	}

	/**
	 * @param linkedPagesCount
	 *            the linkedPagesCount to set
	 */
	public void setLinkedPagesCount(int linkedPagesCount) {
		this.linkedPagesCount = linkedPagesCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + linkedPagesCount;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sequence;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Sprints other = (Sprints) obj;
		if (id != other.id)
			return false;
		if (linkedPagesCount != other.linkedPagesCount)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sequence != other.sequence)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sprints [id=" + id + ", sequence=" + sequence + ", name=" + name + ", state=" + state
				+ ", linkedPagesCount=" + linkedPagesCount + "]";
	}
	
	

}
