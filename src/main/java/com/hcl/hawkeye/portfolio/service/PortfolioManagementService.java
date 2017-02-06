package com.hcl.hawkeye.portfolio.service;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;

public interface PortfolioManagementService {
	
	Cost getConsolidatedPortfolioData();

	Portfolio addPortfolio(Portfolio portfolio);

	Integer noOfPrgmsPerPortFolio(Integer portFoId);

	void addProgramsToPortfolio(List<Program> progList);

}
