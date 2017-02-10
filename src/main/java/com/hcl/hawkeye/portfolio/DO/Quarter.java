
package com.hcl.hawkeye.portfolio.DO;

import java.io.Serializable;
import java.util.ArrayList;

public class Quarter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PortfolioColl> PortfolioColl;

	  public ArrayList<PortfolioColl> getPortfolioColl() { return this.PortfolioColl; }

	  public void setPortfolioColl(ArrayList<PortfolioColl> PortfolioColl) { this.PortfolioColl = PortfolioColl;

}
}
