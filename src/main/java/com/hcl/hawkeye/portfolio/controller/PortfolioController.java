package com.hcl.hawkeye.portfolio.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.ProgramInfo;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PortfolioController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);	
	@Autowired
	public PortfolioManagementService portfolioService;
	
	
	// To add Portfolio
	@RequestMapping(value = "/addportfolio", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Portfolio>  addPortfolio(@RequestBody Portfolio portfolio) {
		System.out.println("portfolio:"+portfolio);
		logger.info("Inside addPortfolio method in Controller:"+portfolio);
		Portfolio portInsert= portfolioService.addPortfolio(portfolio);
		logger.info("portfolio inserted successfully: " +portInsert);
		return new ResponseEntity<>(portInsert, HttpStatus.CREATED);
	}
	
	// To add programs to Portfolio
	@RequestMapping(value = "/prog2portfolio", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public boolean addProgramToPortfolio(@RequestBody List<Program> progList) {
		logger.info("Inside addProjsToProgram method in Controller.");
		boolean status = portfolioService.addProgramsToPortfolio(progList);
		return status;
	}
	
	// get no.of programs per portfolio	
		@RequestMapping(value = "/noofprogramPerPortfolio/{portFoId}", method = RequestMethod.GET)			
		public ResponseEntity<Integer> noOfPrgmsPerPortFolio(@PathVariable("portFoId") Integer portFoId) {
			logger.info("Inside noOfPrgmsPerPortFolio method in Controller");
			Integer noOfPortFolios = portfolioService.noOfPrgmsPerPortFolio(portFoId);
			logger.info("noOfPortFolios==="+noOfPortFolios);
			return new ResponseEntity<Integer>(noOfPortFolios,HttpStatus.CREATED);
		}
		
		@RequestMapping(value = "/getAllPortfolioDetails", method = RequestMethod.GET)			
		public ResponseEntity<PortfolioDashboard> getAllPortfolioDetails() {
			logger.info("Inside getAllPortfolioDetails method in Controller");
			PortfolioDashboard portfolio = portfolioService.getAllPortfolioDashboardInfo();
			logger.info("getAllPortfolioDetails===");
			return new ResponseEntity<PortfolioDashboard>(portfolio,HttpStatus.OK);
		}
		
		@RequestMapping(value = "/getProgramsForPortfolio/{portfolioId}", method = RequestMethod.GET)			
		public ResponseEntity<List<ProgramInfo>> getProgramsForPortfolio(@PathVariable("portfolioId") Integer portfolioId) {
			logger.info("Inside getAllPortfolioDetails method in Controller");
			List<ProgramInfo> programsInfo = portfolioService.getProgramsForPortfolio(portfolioId);
			logger.info("getAllPortfolioDetails===");
			return new ResponseEntity<List<ProgramInfo>>(programsInfo,HttpStatus.OK);
		}

}
