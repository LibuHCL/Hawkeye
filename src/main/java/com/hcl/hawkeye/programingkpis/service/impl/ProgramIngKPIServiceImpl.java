package com.hcl.hawkeye.programingkpis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.IngKpiRetrievalException;
import com.hcl.hawkeye.MetricDataDO.MetricDataDO;
import com.hcl.hawkeye.escalationmanagement.service.EscalationManagementService;
import com.hcl.hawkeye.feedbacktracker.service.impl.FeedbackTrackerServiceImpl;
import com.hcl.hawkeye.metric.service.MetricDataService;
import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.programingkpis.DO.KPIType;
import com.hcl.hawkeye.programingkpis.DO.KPIValue;
import com.hcl.hawkeye.programingkpis.DO.Result;
import com.hcl.hawkeye.programingkpis.service.ProgramIngKPIService;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;
import com.hcl.hawkeye.resourcemanagement.service.ResourceManagementService;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreationQuarterly;
import com.hcl.hawkeye.valueaddmanagement.service.ValueAddManagementService;

@Service
@PropertySource("classpath:ingkpi.properties")
public class ProgramIngKPIServiceImpl implements ProgramIngKPIService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProgramIngKPIServiceImpl.class);

	@Autowired
	Environment env;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ProjectManagementService pmService;
	
	@Autowired
	ValueAddManagementService vmService;
	
	@Autowired
	ResourceManagementService rmService ;
	
	@Autowired
	EscalationManagementService emService;
	
	@Autowired
	FeedbackTrackerServiceImpl feedBackService;
	
	@Autowired
	MetricDataService merticdataservice;
	
	
	@Override
	public Result getOperationalKpiResults(int projectId) {
		Result res = new Result();
		Date d1 = new Date();
		Date d2 = new Date();
		try {
			
			KPIType kp = new KPIType();
			logger.info("starting Date - {}",d1.getSeconds());
			logger.info("starting Date - {}",new Date());
			List<KPIValue> kVList = getListOfKpi(projectId);
			kp.setKpis(kVList);
			kp.setProgramId(Integer.parseInt(env.getProperty("program.programid")));
			kp.setProgramName(env.getProperty("program.programname"));
			res.setResult(kp);
			
		
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.ingkpi", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new IngKpiRetrievalException(errorMsg, e);
		}
		logger.info("ending Date - {}",d2.getSeconds());
		logger.info("starting Date - {}",new Date());
		return res;
	}
	
	private List<KPIValue> getListOfKpi(int projectId) {
		List<KPIValue> kVList = new ArrayList<KPIValue>();
		int[] intVal = {1,2}; 
		
		for (int i : intVal) {
			/*if(i == 1) {
				KPIValue kv1 = new KPIValue();
				List<Integer> grapIntData = new ArrayList<>();
				grapIntData.add(75); grapIntData.add(72); grapIntData.add(81); grapIntData.add(74);
				List<String> labelData = new ArrayList<>();
				labelData.add("Sprint1"); labelData.add("Sprint2");  labelData.add("Sprint3"); labelData.add("Sprint4");
				kv1.setGraphdataOfVelocity(grapIntData);
				kv1.setLabels(labelData);
				kv1.setName(env.getProperty("kpi.name1"));
				kVList.add(kv1);
			}
			*/
			if(i == 1) {
				logger.info("starting Date first kpi - {}",new Date());
				Map<String, Map<String, Integer>> priorityBlockerVal = pmService.getPriorityOfIssue(projectId, env.getProperty("project.priority.blocker"), env.getProperty("project.priority.critical"));
				KPIValue kv2 = new KPIValue();
				List<Integer[]> grapData = new ArrayList<>();
				List<Integer> grapIntData1 = new ArrayList<>();
				List<Integer> grapIntData2 = new ArrayList<>();
				List<String> serires = new ArrayList<>();
				serires.add(env.getProperty("incident.p1"));
				serires.add(env.getProperty("incident.p2"));
				List<String> labelData = new ArrayList<>();
				
				for (String string : priorityBlockerVal.keySet()) {
					if (env.getProperty("project.priority.blocker").equals(string)) {
						for (String key1 : priorityBlockerVal.get(string).keySet()) {
							labelData.add(key1);
							grapIntData1.add(priorityBlockerVal.get(string).get(key1));
						}
					}
					
					if (env.getProperty("project.priority.critical").equals(string)) {
						for (String key1 : priorityBlockerVal.get(string).keySet()) {
							grapIntData2.add(priorityBlockerVal.get(string).get(key1));
						}
					}
				}
				MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("operationalkpi.continuity"));
				kv2.setType(graph.getGraph_Type());	
				grapData.add(grapIntData1.toArray(new Integer[grapIntData1.size()]));
				grapData.add(grapIntData2.toArray(new Integer[grapIntData2.size()]));
				kv2.setSeries(serires);
				kv2.setLabels(labelData);
				kv2.setGraphdata(grapData);
				kv2.setXlabel(env.getProperty("Continuity.xlabel"));	
				kv2.setYlabel(env.getProperty("Continuity.ylabel"));
				ArrayList<String> series = new ArrayList<String>();
	            ArrayList<String> color = new ArrayList<String>();
				continuitySeries(series);
				continuityColor(color);
				kv2.setSeries(series);
				kv2.setColor(color);
				kv2.setName(env.getProperty("kpi.name2"));
				kVList.add(kv2);
				logger.info("Ending Date first kpi - {}",new Date());
			}
			/*
			if(i == 3) {
				ValueAddAcceptedIdeas acceptedIdeas = vmService.getValueAddByAcceptedIdeas(projectId);
				KPIValue kv2 = new KPIValue();
				List<Double> graphData = acceptedIdeas.getGraphdata();
				graphData.add(0.0);
				kv2.setGraphdataOfIdeas(graphData);
				kv2.setLabels(acceptedIdeas.getLabels());
				kv2.setName(acceptedIdeas.getName());
				kVList.add(kv2);
			}*/
			
			if(i == 2) {
				logger.info("starting Date second kpi - {}",new Date());
				List<VelocityOfProject> priorityHighVal = pmService.getVelocityOfSprint(projectId);
				KPIValue kv2 = new KPIValue();
				List<String> labelData = new ArrayList<>();
				List<Integer> grapIntData = new ArrayList<>();
				for (VelocityOfProject string : priorityHighVal) {
					if (!(0 == (int)string.getCompletedValue())) {
						labelData.add(string.getSprintName().replace(" ", ""));
						grapIntData.add((int)string.getCompletedValue());
					}
				}
				MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("operationalkpi.productivity"));
				kv2.setType(graph.getGraph_Type());	
				kv2.setGraphdataOfVelocity(grapIntData);
				kv2.setLabels(labelData);
				kv2.setName(env.getProperty("kpi.name4"));
				kv2.setXlabel(env.getProperty("Productivity.xlabel"));	
				kv2.setYlabel(env.getProperty("Productivity.ylabel"));
				ArrayList<String> series = new ArrayList<String>();
	            ArrayList<String> color = new ArrayList<String>();
				productivitySeries(series);
				productivityColor(color);
				kv2.setSeries(series);
				kv2.setColor(color);
				kVList.add(kv2);
				logger.info("ending Date second kpi - {}",new Date());
			}
		}
		return kVList;
		
	}

	@Override
	public Result getTacticalKpiResults( int projectId) {
		Result res = new Result();
		try {
			KPIType kp = new KPIType();
			List<KPIValue> kVList = getListOfTacticalKpi(projectId);
			kp.setKpis(kVList);
			kp.setProgramId(Integer.parseInt(env.getProperty("program.tacticalProgramId")));
			kp.setProgramName(env.getProperty("program.tacticalProgramName"));
			res.setResult(kp);
		
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.ingkpi", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new IngKpiRetrievalException(errorMsg, e);
		}
		
		return res;
	}

	private List<KPIValue> getListOfTacticalKpi(int projectId) {
		List<KPIValue> kVList = new ArrayList<KPIValue>();
		int[] intVal = {1,2,3,4}; 
		
		for (int i : intVal) {
			if(i == 1) {
				KPIValue kv1 = new KPIValue();
				List<Long> grapIntData = new ArrayList<>();
				List<String> labelData = new ArrayList<>();
				Map<Integer, BigDecimal> mp = rmService.getResourceAttritionByQuarter("2017");
				for (Integer string : mp.keySet()) {
					if (1==string) {
						labelData.add("Q1");
					} else if (2 == string) {
						labelData.add("Q2");
					} else if (3 == string) {
						labelData.add("Q3");
					} else if (4 == string) {
						labelData.add("Q4");
					}
					grapIntData.add(mp.get(string).longValue());
				}
				kv1.setLongraphData(grapIntData);
				kv1.setLabels(labelData);
				kv1.setName(env.getProperty("tacticalKpi.name1"));
				kVList.add(kv1);
			}
			
			if(i == 2) {
				Graph eDetails = emService.noOfEscAtProject(projectId);
				KPIValue kv2 = new KPIValue();
				kv2.setLabels(eDetails.getLabels());
				List<Double> grahData = eDetails.getGraphData();
						grahData.add(0.0);
				kv2.setGraphdataOfIdeas(grahData);
				
				kv2.setName(env.getProperty("tacticalKpi.name2"));
				kVList.add(kv2);
			}
			
			if(i == 3) {
				List<VelocityOfProject> sprintVelocity = pmService.getVelocityOfSprint(projectId);
				
				List<String> labelData = new ArrayList<>();
				List<Integer> graphData = new ArrayList<>();
				graphData.add(0);
				KPIValue kv2 = new KPIValue();
				for (VelocityOfProject velocityOfProject : sprintVelocity) {
					labelData.add(velocityOfProject.getSprintName().replace(" ", ""));
					Double completed = velocityOfProject.getCompletedValue();
					Double estimated= velocityOfProject.getEstimatedValue();
					Integer data = 0;
					if (!estimated.equals(0.0)) {
						data = (int) ((completed/estimated)*100);
					}
					graphData.add(data);
				}
				
				kv2.setGraphdataOfVelocity(graphData);
				kv2.setLabels(labelData);
				kv2.setName(env.getProperty("tacticalKpi.name3"));
				kVList.add(kv2);
			}
			
			if(i == 4) {
				ValueCreationQuarterly valueForQuater = vmService.getQuarterlyValueByProgramId(projectId);
				KPIValue kv2 = new KPIValue();
				List<Integer[]> grapData = new ArrayList<>();
				for (ArrayList<Integer> integer : valueForQuater.getGraphdata()) {
					grapData.add(integer.toArray(new Integer[integer.size()]));
					
				}
				kv2.setGraphdata(grapData);
				kv2.setLabels(valueForQuater.getLabels());
				kv2.setSeries(valueForQuater.getSeries());
				kv2.setName(env.getProperty("tacticalKpi.name4"));
				kVList.add(kv2);
			}
		}
		return kVList;
		
	}

	@Override
	public Result getStrategicalKpiResults(int portfolioId) {
		Result res = new Result();
		try {
			KPIType kp = new KPIType();
			kp.setProgramName(env.getProperty("strategicalkpi.programname"));
			kp.setProgramId(Integer.parseInt(env.getProperty("strategicalkpi.programId")));
			List<KPIValue> kVList = getListOfStrategicalKpi(portfolioId);
			kp.setKpis(kVList);
			
			
			res.setResult(kp);
		
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.ingkpi", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new IngKpiRetrievalException(errorMsg, e);
		}
		
		return res;
	}

	private List<KPIValue> getListOfStrategicalKpi(int portfolioId) {
		List<KPIValue> StrkVList = new ArrayList<KPIValue>();
		int[] intVal = {1,2,3,4}; 
		
		for (int i : intVal) {
			String[] labels = env.getProperty("strategicalkpi.satakeholders").split(",");
			ArrayList<String> labelsList = new ArrayList<String>();
			Collections.addAll(labelsList, labels);
			if(i == 1) {
				KPIValue kv1 = new KPIValue();
				kv1.setName(env.getProperty("strategicalkpi.name1"));
				Graph escDetails = emService.noOfEscAtPortfolioLevelPerQt(portfolioId);
				kv1.setGraphdataOfIdeas(escDetails.getGraphData());
				kv1.setLabels(escDetails.getLabels());
				ArrayList<String> series = new ArrayList<String>();
				ArrayList<String> color = new ArrayList<String>();
				ecoseries(series);		
				MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("strategicalkpi.Ecoscreen"));
				kv1.setType(graph.getGraph_Type());	
				ecocolor(color);
				kv1.setSeries(series);
				kv1.setXlabel(env.getProperty("Strglxlabel"));	
				kv1.setYlabel(env.getProperty("Strglylabel"));
				kv1.setColor(color);
				StrkVList.add(kv1);
			}			
			if(i == 2) {
				Graph feedDetails = feedBackService.getnoofFeedBacksPerQtAtPerfolioLevel(portfolioId, "STAKEHOLDER");
				
				KPIValue kv2 = new KPIValue();
				kv2.setName(env.getProperty("strategicalkpi.name2"));
				kv2.setGraphdataOfIdeas(feedDetails.getGraphData());
				MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("strategicalkpi.Stakeholder"));
				ArrayList<String> series = new ArrayList<String>();
				ArrayList<String> color = new ArrayList<String>();
				stakeseries(series);
				Stakecolor(color);
				kv2.setType(graph.getGraph_Type());
				kv2.setLabels(labelsList);
				kv2.setSeries(series);	
				kv2.setXlabel(env.getProperty("Stakeholder.xlabel"));	
				kv2.setYlabel(env.getProperty("Stakeholder.ylabel"));
				kv2.setColor(color);
				StrkVList.add(kv2);
			}			
			if(i == 3) {
				Graph feedDetails = feedBackService.getnoofFeedBacksPerQtAtPerfolioLevel(portfolioId, "VENDOR");
				KPIValue kv2 = new KPIValue();
                MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("strategicalkpi.Partner"));
                ArrayList<String> series = new ArrayList<String>();
                ArrayList<String> color = new ArrayList<String>();
				ArrayList<String> bgcolor = new ArrayList<String>();
				partnerseries(series);
				Partnercolor(color);
				kv2.setSeries(series);
				kv2.setType(graph.getGraph_Type());
				kv2.setName(env.getProperty("strategicalkpi.name3"));
				kv2.setGraphdataOfIdeas(feedDetails.getGraphData());
				kv2.setLabels(labelsList);
				kv2.setXlabel(env.getProperty("Partner.xlabel"));	
				kv2.setYlabel(env.getProperty("Partner.ylabel"));
				kv2.setColor(color);
				kv2.setBgcolor(bgcolor);
				StrkVList.add(kv2);
			}
			
			if(i == 4) {
				ValueAddAcceptedIdeas valueForQuater = vmService.getEconomicValueAddByPortfolio(portfolioId);
				KPIValue kv2 = new KPIValue();
				kv2.setName(env.getProperty("strategicalkpi.name4"));
                MetricDataDO graph = merticdataservice.getMetricGraph(env.getProperty("strategicalkpi.Economic"));
                ArrayList<String> series = new ArrayList<String>();
                ArrayList<String> color = new ArrayList<String>();
				economicseries(series);
				Ecocolor(color);
				kv2.setSeries(series);
				kv2.setType(graph.getGraph_Type());
				kv2.setGraphdataOfIdeas(valueForQuater.getGraphdata());
				kv2.setLabels(valueForQuater.getLabels());
				kv2.setXlabel(env.getProperty("Economic.xlabel"));	
				kv2.setYlabel(env.getProperty("Economic.ylabel"));
				kv2.setColor(color);
				StrkVList.add(kv2);
			}
		}
		return StrkVList;
	}
	private void ecoseries(ArrayList<String> series) {
		series.add(env.getProperty("Ecosystem.series"));
		
	}
	
	private void stakeseries(ArrayList<String> series) {
		series.add(env.getProperty("Stakeholder.series"));
		
	}
	
	private void partnerseries(ArrayList<String> series) {
		series.add(env.getProperty("Partner.series"));
		
	}
	
	private void economicseries(ArrayList<String> series) {
		series.add(env.getProperty("Economic.series"));
		
	}
	private  void ecocolor(ArrayList<String> series) {
		series.add(env.getProperty("StrategicalKPI.color"));
		
	}
	
	private void productivitySeries(ArrayList<String> series){
		series.add(env.getProperty("Productivity.series"));
	}
	
	private void productivityColor(ArrayList<String> color){
		color.add(env.getProperty("Productivity.color"));
	}
	
	private void continuitySeries(ArrayList<String> series){
		series.add(env.getProperty("Continuity.series"));
	}
	
	private void continuityColor(ArrayList<String> color){
		color.add(env.getProperty("Continuity.color"));
	}
	private  void Stakecolor(ArrayList<String> series) {
		series.add(env.getProperty("Stakeholder.color"));
		
	}
		
	private  void Partnercolor(ArrayList<String> series) {
		series.add(env.getProperty("Partner.color"));
		
	}
	
	
	private  void Ecocolor(ArrayList<String> series) {
		series.add(env.getProperty("Economic.color"));
		
	}
	
	
	
	
}
