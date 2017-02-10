
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class PortfolioDashboard implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PortfolioDate> PortfolioDates;

	  public ArrayList<PortfolioDate> getPortfolioDates() { return this.PortfolioDates; }

	  public void setPortfolioDates(ArrayList<PortfolioDate> PortfolioDates) { this.PortfolioDates = PortfolioDates; }

	  private ArrayList<Quarter> quarters;

	  public ArrayList<Quarter> getQuarters() { return this.quarters; }

	  public void setQuarters(ArrayList<Quarter> quarters) { this.quarters = quarters; }

}
