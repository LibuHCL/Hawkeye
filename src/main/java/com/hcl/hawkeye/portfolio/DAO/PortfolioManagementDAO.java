package com.hcl.hawkeye.portfolio.DAO;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;

public interface PortfolioManagementDAO {
	
	Cost getPortfolioData();

	Portfolio addPortfolio(Portfolio portfolio);

	void addProgramsToPortfolio(List<Program> progList);

	Integer noOfPrgmsPerPortFolio(Integer portFoId);
	
}
