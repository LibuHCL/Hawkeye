
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;

public class PortfolioDate implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;

	  public String getKey() { return this.key; }

	  public void setKey(String key) { this.key = key; }

	  private int value;

	  public int getValue() { return this.value; }

	  public void setValue(int value) { this.value = value; }
	  
	  
}
