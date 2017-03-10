package com.hcl.hawkeye.projectmetrics.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.buildmanagement.DAO.BuildMetricsManagementDAO;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;
import com.hcl.hawkeye.projectcost.DO.ProjectCostDetails;
import com.hcl.hawkeye.projectcost.service.ProjectCostService;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;
import com.hcl.hawkeye.projectmetrics.DAO.ProjectMetricsDAO;
import com.hcl.hawkeye.projectmetrics.DO.Metrics;
import com.hcl.hawkeye.projectmetrics.DO.MetricsValueCreation;
import com.hcl.hawkeye.projectmetrics.DO.ProjectDetails;
import com.hcl.hawkeye.projectmetrics.DO.ProjectMetricCost;
import com.hcl.hawkeye.projectmetrics.DO.ProjectMetricResults;
import com.hcl.hawkeye.projectmetrics.DO.ProjectMetrics;
import com.hcl.hawkeye.projectmetrics.service.ProjectMetricsService;
import com.hcl.hawkeye.resourcemanagement.service.ResourceManagementService;
import com.hcl.hawkeye.sonarmetrics.service.SonarMetricsManagementService;
import com.hcl.hawkeye.teamhappiness.service.TeamHappinessManagementService;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;
import com.hcl.hawkeye.valueaddmanagement.service.ValueAddManagementService;

@Service
@Configuration
@PropertySource("classpath:projmetric.properties")
public class ProjectMetricsServiceImpl implements ProjectMetricsService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectMetricsServiceImpl.class);
	
	@Autowired
	Environment env;
	
	@Autowired
	ProjectCostService projCostService;
	
	@Autowired
	ProgramManagementService progMgmtService;
	
	@Autowired
	ProjectManagementService projMgmtService;
	
	@Autowired
	ResourceManagementService resMgmtService;
	
	@Autowired
	ValueAddManagementService valMgmtseervice;
	
	@Autowired
	TeamHappinessManagementService teamHapService;
	
	@Autowired
	SonarMetricsManagementService sonMetService;
	
	@Autowired
	ProjectMetricsDAO projectMetricsDAO;
	
	@Autowired
	BuildMetricsManagementDAO buildDAO;

	@Override
	public ProjectMetrics getProjMetricsData(int projectId) {
		
		logger.info("Inside getProjMetricsData method in ProjectMetricsServiceImpl");
		
		List <ProjectMetricResults> pmResultsList = new ArrayList<ProjectMetricResults>();
		
		ProjectMetrics projMet = new ProjectMetrics();
		//Set Project Details
		addProject(projMet,projectId);
		
		//Set Project Metrics
		pmResultsList.add(addProjectMetrics(projectId));
		
		//Set Engineering Metrics
		pmResultsList.add(addEngineeringMetrics(projectId));
		
		//Set Cost
		pmResultsList.add(addCommercials(projectId));		
		
		//Set valuation index
		pmResultsList.add(addValueCreation(projectId));
		
		projMet.setResult(pmResultsList);
		
		return projMet;
	}

	private void addProject(ProjectMetrics projMet, int projectId) {
		logger.info("addProject: {}",projectId);
		//To Get project Details
		Project proj = progMgmtService.getProject(projectId);
		
		//To get Sprints
		SprintDetailsOfProject sprintDetails= projMgmtService.getProjectDetails(projectId);
		
		//To get Resources
		Map<String,Long>resMap=resMgmtService.getResourcesCountByProject(projectId);
		
		projMet.setProjectname(proj.getProjName());
		projMet.setEndDate(proj.getEndDate());
		projMet.setCurrentsprint(sprintDetails.getCurrentSprint());
		projMet.setTotalsptint(String.valueOf(sprintDetails.getNoOfSprintPerProject()));
		projMet.setTpmname("NULL");
		projMet.setPmname("NULL");
		projMet.setDevelopers(String.valueOf(resMap.get("DEVELOPER")));
		projMet.setTesters(String.valueOf(resMap.get("TESTER")));
		projMet.setUidev(String.valueOf(resMap.get("UIDEV")));
		
	}
	private ProjectMetricResults addProjectMetrics(int projectId) {
		
		logger.info("addProjectMetrics: {}",projectId);
		
		ArrayList<Metrics> metrics= new ArrayList<Metrics>();
		
		ProjectMetricResults pMetResults = new ProjectMetricResults();
		pMetResults.setProgramName(env.getProperty("metric.progname"));
		pMetResults.setProgramId(env.getProperty("metric.progId"));
		
		
		ArrayList<Double> graphdata = new ArrayList<Double>();
		
		//UAT Issues Metric
		Metrics uatMet = new Metrics();
		DefectTypes defType = projMgmtService.getDefectTypesOfProject(projectId);
		
		
		uatMet.setKey(env.getProperty("metric.UAT.progname"));		
		uatMet.setInternal_Defects(defType.getInternalDefects());
		uatMet.setValid_Internal_Defects(defType.getValidInternalDefects());
		uatMet.setUAT_Issues(defType.getUatDefects());
		uatMet.setLeaked_UAT_Issues(defType.getDefectLekage());
		uatMet.setLeakage(2.68);		
		graphdata.add(uatMet.getLeakage());
		graphdata.add(100-uatMet.getLeakage());
		uatMet.setGraphdata( graphdata);
		metrics.add(uatMet);
		
		//Team Hapiness Matrics
		Calendar now = Calendar.getInstance();
		Graph teamHapGrpah = teamHapService.getHappinessPerQtAtProject(projectId, now.get(Calendar.YEAR));
		Metrics teamHapMet = new Metrics();
		teamHapMet.setKey(env.getProperty("metric.teamhap.progname"));
		teamHapMet.setGraphdata(teamHapGrpah.getGraphData());
		teamHapMet.setLabels(teamHapGrpah.getLabels());		
		metrics.add(teamHapMet);
		
		//Offshore associates
		Metrics offAssMet = new Metrics();
		Graph offShoreGrpah = resMgmtService.getOffshorePerQtPerProject(projectId);
		offAssMet.setKey(env.getProperty("metric.offAss.progname"));
		offAssMet.setGraphdata(offShoreGrpah.getGraphData());
		offAssMet.setLabels(offShoreGrpah.getLabels());
		metrics.add(offAssMet);
		pMetResults.setMetrics(metrics);
		
		return pMetResults;
	}
	
	private ProjectMetricResults addEngineeringMetrics(int projectId) {
		
		logger.info("addEngineeringMetrics: {}",projectId);
		
		Map <String,Graph> engMap = sonMetService.getSonarMetricsData(projectId);
		
		List<Metrics> engMetrics= new ArrayList<Metrics>();
		
		ProjectMetricResults pMetResults = new ProjectMetricResults();
		pMetResults.setProgramName(env.getProperty("engmetric.progname"));
		pMetResults.setProgramId(env.getProperty("engmetric.progId"));
		
		
		// Code Complexity
		Metrics codeMet = new Metrics();
		
		codeMet.setKey(env.getProperty("metric.code.progname"));
		codeMet.setGraphdata(engMap.get(env.getProperty("metric.code.progname")).getGraphData());
		codeMet.setLabels(engMap.get(env.getProperty("metric.code.progname")).getLabels());		
		engMetrics.add(codeMet);
		
		// Blockers
		Metrics blockeMet = new Metrics();
		
		blockeMet.setKey(env.getProperty("metric.blockeMet.progname"));
		blockeMet.setGraphdata(engMap.get(env.getProperty("metric.blockeMet.progname")).getGraphData());
		blockeMet.setLabels(engMap.get(env.getProperty("metric.blockeMet.progname")).getLabels());		
		engMetrics.add(blockeMet);
		
		//Technical DEBT
		Metrics techDebtMet = new Metrics();
		String[] labels = env.getProperty("metric.debt.labels").split(",");
		ArrayList<String> labelsList = new ArrayList<String>();
		Collections.addAll(labelsList, labels);
		techDebtMet.setKey(env.getProperty("metric.techDebt.progname"));
		techDebtMet.setGraphdata(engMap.get(env.getProperty("metric.techDebt.progname")).getGraphData());
		techDebtMet.setLabels(labelsList);		
		engMetrics.add(techDebtMet);
		
		
		// Duplicate lines
		Metrics commnetMet = new Metrics();
		
		commnetMet.setKey(env.getProperty("metric.commnetMet.progname"));
		commnetMet.setGraphdata(engMap.get(env.getProperty("metric.commnetMet.progname")).getGraphData());
		commnetMet.setLabels(engMap.get(env.getProperty("metric.commnetMet.progname")).getLabels());		
		engMetrics.add(commnetMet);
		
		//Build Per day
		Metrics buildperday = new Metrics();
		buildperday.setKey(env.getProperty("buildkpi.name1"));
		Graph escDetails = buildDAO.getBuildsPerDay(projectId);
		if (null !=escDetails &&null != escDetails.getGraphData()) {
			buildperday.setGraphdata(escDetails.getGraphData());
			}else {
			buildperday.setGraphdata(null);
		}
		buildperday.setLabels(escDetails.getLabels());
		engMetrics.add(buildperday);
		
		//Builds Avg Per day
		Metrics buildAvgperday = new Metrics();
		buildAvgperday.setKey(env.getProperty("buildkpi.name2"));
		Graph escDetails2 = buildDAO.getAvgBuildDuration(projectId);
		if (null !=escDetails2 &&null != escDetails2.getGraphData()) {
			buildAvgperday.setGraphdata(escDetails2.getGraphData());
			}else {
				buildAvgperday.setGraphdata(null);
		}
		buildAvgperday.setLabels(escDetails2.getLabels());
		engMetrics.add(buildAvgperday);
		
		//Commits Per day
				Metrics commitsPerDay = new Metrics();
				commitsPerDay.setKey(env.getProperty("commitskpi.name"));
				Graph commitDetails = buildDAO.getCommitsPerDay(projectId);
				if (null !=commitDetails &&null != commitDetails.getGraphData()) {
					commitsPerDay.setGraphdata(commitDetails.getGraphData());
					}else {
						commitsPerDay.setGraphdata(null);
				}
				commitsPerDay.setLabels(commitDetails.getLabels());
				engMetrics.add(commitsPerDay);
		
		pMetResults.setMetrics(engMetrics);
		return pMetResults;
	}

	private ProjectMetricResults addCommercials( int projectId) {
		logger.info("getCostData: {}",projectId);
		
		List<ProjectMetricCost> pmCostList= new ArrayList<ProjectMetricCost>();
		
		ProjectMetricResults pMetResults = new ProjectMetricResults();
		pMetResults.setProgramName(env.getProperty("commercial.progname"));
		pMetResults.setProgramId(env.getProperty("commercial.progId"));
		
		ProjectCostDetails costDetails = projCostService.getProjectCostData(projectId);
		
		ProjectMetricCost pmCost = new ProjectMetricCost();
		//Planned Cost
		pmCost.setKey(env.getProperty("comm.plannedcost"));
		pmCost.setValue(costDetails.getPlannedCost());
		pmCost.setPostfix(env.getProperty("comm.postfix"));
		pmCost.setSymbol(env.getProperty("comm.symbol"));
		pmCostList.add(pmCost);
		
		//Actual Cost
		ProjectMetricCost pmCost1 = new ProjectMetricCost();
		pmCost1.setKey(env.getProperty("comm.actualcost"));
		pmCost1.setValue(costDetails.getActualCost());
		pmCost1.setPostfix(env.getProperty("comm.postfix"));
		pmCost1.setSymbol(env.getProperty("comm.symbol"));
		pmCostList.add(pmCost1);		
		//ROI
		ProjectMetricCost pmCost2 = new ProjectMetricCost();
		pmCost2.setKey(env.getProperty("comm.roi"));
		pmCost2.setRoiValue(costDetails.getRoi());
		pmCost2.setPostfix(env.getProperty("comm.postfix.roi"));
		pmCost2.setSymbol(env.getProperty("comm.symbol.roi"));
		pmCostList.add(pmCost2);
		
		pMetResults.setCost(pmCostList);
		return pMetResults;
	}

	private ProjectMetricResults addValueCreation(int projectId) {
		
		ValueCreation val =valMgmtseervice.getValueCreationByProjectId(projectId);
		
		ProjectMetricResults pMetResults = new ProjectMetricResults();
		
		MetricsValueCreation mValCreation = new MetricsValueCreation();
		mValCreation.setLinedata(val.getLinedata());
		mValCreation.setSeries(val.getSeries());
		
		pMetResults.setProgramName(env.getProperty("valcreation.progname"));
		pMetResults.setProgramId(env.getProperty("valcreation.progId"));
		pMetResults.setValueCreation(mValCreation);
		
		return pMetResults;
				
	}

	@Override
	public ProjectDetails getProjectByProgramId(Integer programId) {
		logger.info("Request in getProjectByProgramId of ProjectMetricsServiceImpl");
		return projectMetricsDAO.getProjectByProgramId(programId);
	}
	
}
