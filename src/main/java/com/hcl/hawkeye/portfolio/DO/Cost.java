
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;

public class Cost implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;

	  public String getKey() { return this.key; }

	  public void setKey(String key) { this.key = key; }

	  private String value;

	  public String getValue() { return this.value; }

	  public void setValue(String value) { this.value = value; }

	  private String postfix;

	  public String getPostfix() { return this.postfix; }

	  public void setPostfix(String postfix) { this.postfix = postfix; }

	  private String symbol;

	  public String getSymbol() { return this.symbol; }

	  public void setSymbol(String symbol) { this.symbol = symbol; }

}
