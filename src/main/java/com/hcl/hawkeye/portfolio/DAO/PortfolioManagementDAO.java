package com.hcl.hawkeye.portfolio.DAO;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.ProgramInfo;

public interface PortfolioManagementDAO {

	Portfolio addPortfolio(Portfolio portfolio);

	boolean addProgramsToPortfolio(List<Program> progList);

	Integer noOfPrgmsPerPortFolio(Integer portFoId);
	
	String getPortfolioNameById(Integer portfolioId);
	Integer noOfProjectsPerPortFolio(Integer portFolioId);
	
	List<ProgramInfo> getProgramDetailsByPortfolio(Integer portfolioId);
	
}
