package com.hcl.hawkeye.portfolio.service;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.ProgramInfo;

public interface PortfolioManagementService {

	Portfolio addPortfolio(Portfolio portfolio);

	Integer noOfPrgmsPerPortFolio(Integer portFoId);

	boolean addProgramsToPortfolio(List<Program> progList);
	
	PortfolioDashboard getAllPortfolioDashboardInfo();
	
	List<ProgramInfo> getProgramsForPortfolio(Integer portfolioId);

}
