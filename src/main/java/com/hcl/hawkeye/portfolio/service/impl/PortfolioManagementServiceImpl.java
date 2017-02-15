package com.hcl.hawkeye.portfolio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import com.hcl.hawkeye.portfolio.DO.ValueCreation;
import com.hcl.hawkeye.portfolio.service.PortfolioManagementService;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.utils.HawkEyeConstants;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueIndex;
import com.hcl.hawkeye.valueaddmanagement.service.ValueAddManagementService;

@Service
public class PortfolioManagementServiceImpl implements PortfolioManagementService {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioManagementServiceImpl.class);

	@Autowired
	PortfolioManagementDAO portfolioDAO;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProjectCostService projectCostService;
	@Autowired
	ValueAddManagementService valueAddService;

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
		Set<String> yearlySet = new HashSet<String>();
		LinkedHashMap<String, PortfolioInfo> portfoliosMap = new LinkedHashMap<>();
		List<PortfolioInfo> portfoliosList = projectCostService.getAllPortfolioDetails();
		ArrayList<PortfolioDate> portfolioDates = new ArrayList<>();
		if (0 != portfoliosList.size()) {
			for (int i = 0; i < portfoliosList.size(); i++) {
				PortfolioDate portfolioDate = new PortfolioDate();
				String yearlyKey = String.valueOf(portfoliosList.get(i).getYear());
				yearlySet.add(yearlyKey);
				portfolioDate.setKey(yearlyKey);
				portfolioDate.setValue(i);
				portfoliosMap.put(yearlyKey + " - " + portfoliosList.get(i).getPortfolioId(), portfoliosList.get(i));
				portfolioDates.add(portfolioDate);
			}
		}

		ArrayList<Quarter> qList = new ArrayList<>();
		for (String s : yearlySet) {
			Quarter quarter = new Quarter();
			ArrayList<PortfolioColl> colList = new ArrayList<>();
			for (String s1 : portfoliosMap.keySet()) {
				PortfolioColl portfolioDetail = new PortfolioColl();
				String keyString = s1.substring(0, 4);
				int projectsCount = 0;
				if (keyString.equalsIgnoreCase(s)) {
					Integer portfolioId = portfoliosMap.get(s1).getPortfolioId();
					logger.info("Fetchning details for portfolio id" + portfolioId);
					String portfolioName = portfolioDAO.getPortfolioNameById(portfolioId);
					Map<Integer, ValueIndex> valueAdds = valueAddService.getValueAddByIds(portfolioId);
					projectsCount = portfolioDAO.noOfProjectsPerPortFolio(portfolioId);
					portfolioDetail.setId(portfolioId);
					portfolioDetail.setName(portfolioName);
					portfolioDetail.setCount(projectsCount);
					ArrayList<Cost> costs = new ArrayList<>();
					Cost cost = new Cost();
					cost.setKey("Planned Cost");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_EURO);
					cost.setValue(String.valueOf(portfoliosMap.get(s1).getPlannedCost()));
					costs.add(cost);
					cost = new Cost();
					cost.setKey("Actual Cost");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_EURO);
					cost.setValue(String.valueOf(portfoliosMap.get(s1).getActualCost()));
					costs.add(cost);
					cost = new Cost();
					cost.setKey("ROI (" + HawkEyeConstants.SYMBOL_EURO + ")");
					cost.setPostfix("million");
					cost.setSymbol(HawkEyeConstants.SYMBOL_PERCENTAGE);
					cost.setValue(String.valueOf(portfoliosMap.get(s1).getRoi()));
					costs.add(cost);
					portfolioDetail.setCost(costs);
					ValueIndex v = valueAdds.get(portfoliosMap.get(s1).getYear().intValue());
					ValueCreation value = new ValueCreation();
					if (null != v && null != v.getLabels()) {
						value.setLabels(v.getLabels());
						value.setLinedata(v.getLinedata());
						value.setSeries(v.getSeries());
						portfolioDetail.setValueCreation(value);
						colList.add(portfolioDetail);
					}
				}
			}
			quarter.setPortfolioColl(colList);
			qList.add(quarter);
		}
		dashboard.setQuarters(qList);
		dashboard.setPortfolioDates(portfolioDates);
		logger.info("returning all dashboard info successfully");
		return dashboard;

	}
}