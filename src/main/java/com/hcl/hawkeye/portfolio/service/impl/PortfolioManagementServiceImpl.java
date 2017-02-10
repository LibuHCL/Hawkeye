package com.hcl.hawkeye.portfolio.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.PortfolioCreationException;
import com.hcl.hawkeye.portfolio.DAO.PortfolioManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Cost;
import com.hcl.hawkeye.portfolio.DO.Portfolio;
import com.hcl.hawkeye.portfolio.DO.PortfolioColl;
import com.hcl.hawkeye.portfolio.DO.PortfolioDashboard;
import com.hcl.hawkeye.portfolio.DO.PortfolioDate;
import com.hcl.hawkeye.portfolio.DO.PortfolioInfo;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Quarter;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.utils.HawkEyeConstants;

@Service
public class PortfolioManagementServiceImpl implements PortfolioManagementService {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementServiceImpl.class);

	@Autowired
	PortfolioManagementDAO portfolioDAO;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProjectCostService projectCostService;

	@Override
	public Portfolio addPortfolio(Portfolio portfolio) {
		logger.info("Inside addPortfolio method in PortFolioMangementServiceImpl");

		try {
			return portfolioDAO.addPortfolio(portfolio);
		} catch (DataAccessException dae) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.create.porfolio", new Object[] {}, locale);
			logger.error(errorMsg, dae);
			throw new PortfolioCreationException(errorMsg, dae);
		}
	}

	@Override
	public void addProgramsToPortfolio(List<Program> progList) {
		logger.info("Inside addProgramsToPortfolio method in PortFolioMangementServiceImpl=="
				+ progList.get(0).getPortfolioId());

		try {
			portfolioDAO.addProgramsToPortfolio(progList);
		} catch (DataAccessException e) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.addprog.porfolio", new Object[] {}, locale);
			logger.error(errorMsg, e);
		}

	}

	@Override
	public Integer noOfPrgmsPerPortFolio(Integer portFoId) {
		logger.info("Inside noOfPrgmsPerPortFolio method in PortFolioMangementServiceImpl: " + portFoId);

		try {
			return portfolioDAO.noOfPrgmsPerPortFolio(portFoId);
		} catch (DataAccessException e) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.get.noofprogram", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new PortfolioCreationException(errorMsg, e);
		}
	}

	@Override
	public PortfolioDashboard getAllPortfolioDashboardInfo() {
		logger.info("Inside getAllPortfolioDashboardInfo method in PortFolioMangementServiceImpl: ");
		PortfolioDashboard dashboard = new PortfolioDashboard();
		Set<String> quartersSet = new HashSet<String>();
		HashMap<String, PortfolioInfo> portfoliosMap = new HashMap<>();
		List<PortfolioInfo> portfoliosList = projectCostService.getAllPortfolioDetails();
		if (0 != portfoliosList.size()) {
			for (int i = 0; i < portfoliosList.size(); i++) {
				String quartersKey = "Q" + portfoliosList.get(i).getQuarter() + " - " + portfoliosList.get(i).getYear();
				quartersSet.add(quartersKey);
				portfoliosMap.put(quartersKey + portfoliosList.get(i).getPortfolioId(), portfoliosList.get(i));
			}
		}
		ArrayList<PortfolioDate> portfolioDates = new ArrayList<>();
		Quarter quarter = new Quarter();
		ArrayList<PortfolioColl> colList = new ArrayList<>();
		for (String s : quartersSet) {
			PortfolioDate portfolioDate = new PortfolioDate();
			portfolioDate.setKey(s);
			portfolioDate.setValue(Integer.parseInt(s.substring(1, 2)));
			portfolioDates.add(portfolioDate);

			for (int j = 0; j < portfoliosList.size(); j++) {
				PortfolioColl portfolioDetail = new PortfolioColl();
				String currentKey = "Q" + portfoliosList.get(j).getQuarter() + " - " + portfoliosList.get(j).getYear();
				if (currentKey.equalsIgnoreCase(s)) {
					String portfolioName = portfolioDAO.getPortfolioNameById(portfoliosList.get(j).getPortfolioId());
					portfolioDetail.setId(portfoliosList.get(j).getPortfolioId());
					portfolioDetail.setName(portfolioName);
					ArrayList<Cost> costs = new ArrayList<>();
					Cost cost = new Cost();
					cost.setKey("Planned Cost");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_EURO);
					cost.setValue(String.valueOf(portfoliosList.get(j).getPlannedCost()));
					costs.add(cost);
					cost = new Cost();
					cost.setKey("Actual Cost");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_EURO);
					cost.setValue(String.valueOf(portfoliosList.get(j).getActualCost()));
					costs.add(cost);
					cost = new Cost();
					cost.setKey("ROI (" + HawkEyeConstants.SYMBOL_EURO + ")");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_PERCENTAGE);
					cost.setValue(String.valueOf(portfoliosList.get(j).getRoi()));
					costs.add(cost);
					portfolioDetail.setCost(costs);
					colList.add(portfolioDetail);

				}
			}

		}
		ArrayList<Quarter> qList = new ArrayList<>();
		quarter.setPortfolioColl(colList);
		qList.add(quarter);
		dashboard.setQuarters(qList);
		dashboard.setPortfolioDates(portfolioDates);
		logger.info("returning all dashboard info successfully");
		return dashboard;
	}

}
