
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class PortfolioColl implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	  public String getName() { return this.name; }

	  public void setName(String name) { this.name = name; }

	  private int id;

	  public int getId() { return this.id; }

	  public void setId(int id) { this.id = id; }

	  private int count;

	  public int getCount() { return this.count; }

	  public void setCount(int count) { this.count = count; }

	  private ArrayList<Cost> cost;

	  public ArrayList<Cost> getCost() { return this.cost; }

	  public void setCost(ArrayList<Cost> cost) { this.cost = cost; }

	  private ValueCreation valueCreation;

	  public ValueCreation getValueCreation() { return this.valueCreation; }

	  public void setValueCreation(ValueCreation valueCreation) { this.valueCreation = valueCreation; }


}
