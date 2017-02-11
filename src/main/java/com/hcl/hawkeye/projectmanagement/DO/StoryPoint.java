package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class StoryPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long value;
	private long text;

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * @return the text
	 */
	public long getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(long text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (text ^ (text >>> 32));
		result = prime * result + (int) (value ^ (value >>> 32));
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
		StoryPoint other = (StoryPoint) obj;
		if (text != other.text)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StoryPoint [value=" + value + ", text=" + text + "]";
	}

	
}
