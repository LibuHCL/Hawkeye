package com.hcl.hawkeye.projectmanagement.DO;

public class Sprints {

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

}
