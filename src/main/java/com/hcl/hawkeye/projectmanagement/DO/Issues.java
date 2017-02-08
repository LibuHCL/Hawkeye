package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.List;

public class Issues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String expand;
	private String id;
	private String self;
	private String key;
	private Fields fields;

	/**
	 * @return the expand
	 */
	public String getExpand() {
		return expand;
	}

	/**
	 * @param expand
	 *            the expand to set
	 */
	public void setExpand(String expand) {
		this.expand = expand;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the self
	 */
	public String getSelf() {
		return self;
	}

	/**
	 * @param self
	 *            the self to set
	 */
	public void setSelf(String self) {
		this.self = self;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the fields
	 */
	public Fields getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(Fields fields) {
		this.fields = fields;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expand == null) ? 0 : expand.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Issues [expand=" + expand + ", id=" + id + ", self=" + self + ", key=" + key + ", fields=" + fields
				+ "]";
	}

	
	
}
