package com.hcl.hawkeye.portfolio.DAO;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;

public interface PortfolioManagementDAO {

	Portfolio addPortfolio(Portfolio portfolio);

	void addProgramsToPortfolio(List<Program> progList);

	Integer noOfPrgmsPerPortFolio(Integer portFoId);
	
	String getPortfolioNameById(Integer portfolioId);
	Integer noOfProjectsPerPortFolio(Integer portFolioId);
	
}
