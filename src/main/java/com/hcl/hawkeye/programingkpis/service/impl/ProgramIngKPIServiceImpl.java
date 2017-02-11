package com.hcl.hawkeye.programingkpis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import com.hcl.hawkeye.escalationmanagement.service.EscalationManagementService;
import com.hcl.hawkeye.feedbacktracker.service.impl.FeedbackTrackerServiceImpl;
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
public class ProgramIngKPIServiceImpl implements ProgramIngKPIService{
	
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
	
	@Override
	public Result getOperationalKpiResults(int projectId) {
		Result res = new Result();
		try {
			KPIType kp = new KPIType();
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
		
		return res;
	}
	
	private List<KPIValue> getListOfKpi(int projectId) {
		List<KPIValue> kVList = new ArrayList<KPIValue>();
		int[] intVal = {1,2,3,4}; 
		
		for (int i : intVal) {
			if(i == 1) {
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
			
			if(i == 2) {
				Map<String, Integer> priorityHighVal = pmService.getPriorityOfIssue(projectId, env.getProperty("project.priority.high"));
				Map<String, Integer> priorityCriVal = pmService.getPriorityOfIssue(projectId, env.getProperty("project.priority.critical"));
				KPIValue kv2 = new KPIValue();
				List<Integer[]> grapData = new ArrayList<>();
				List<Integer> grapIntData1 = new ArrayList<>();
				List<Integer> grapIntData2 = new ArrayList<>();
				List<String> serires = new ArrayList<>();
				serires.add(env.getProperty("incident.p1"));
				serires.add(env.getProperty("incident.p2"));
				List<String> labelData = new ArrayList<>();
				
				for (String key : priorityCriVal.keySet()) {
					labelData.add(key.replace(" ", ""));
					grapIntData1.add(priorityCriVal.get(key));
				}
				
				for (String key1 : priorityHighVal.keySet()) {
					grapIntData2.add(priorityHighVal.get(key1));
				}
				
				grapData.add(grapIntData1.toArray(new Integer[grapIntData1.size()]));
				grapData.add(grapIntData2.toArray(new Integer[grapIntData2.size()]));
				kv2.setSeries(serires);
				kv2.setLabels(labelData);
				kv2.setGraphdata(grapData);
				
				
				kv2.setName(env.getProperty("kpi.name2"));
				kVList.add(kv2);
			}
			
			if(i == 3) {
				ValueAddAcceptedIdeas acceptedIdeas = vmService.getValueAddByAcceptedIdeas(Integer.parseInt(env.getProperty("program.program.ideaNum")));
				KPIValue kv2 = new KPIValue();
				List<Double> graphData = acceptedIdeas.getGraphdata();
				graphData.add(0.0);
				kv2.setGraphdataOfIdeas(graphData);
				kv2.setLabels(acceptedIdeas.getLabels());
				kv2.setName(acceptedIdeas.getName());
				kVList.add(kv2);
			}
			
			if(i == 4) {
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
				kv2.setGraphdataOfVelocity(grapIntData);
				kv2.setLabels(labelData);
				kv2.setName(env.getProperty("kpi.name4"));
				kVList.add(kv2);
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
				ValueCreationQuarterly valueForQuater = vmService.getQuarterlyValueByProgramId(Integer.parseInt(env.getProperty("program.program.ideaNum")));
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
				StrkVList.add(kv1);
			}			
			if(i == 2) {
				Graph feedDetails = feedBackService.getnoofFeedBacksPerQtAtPerfolioLevel(portfolioId, "STAKEHOLDER");
				
				KPIValue kv2 = new KPIValue();
				kv2.setName(env.getProperty("strategicalkpi.name2"));
				kv2.setGraphdataOfIdeas(feedDetails.getGraphData());
				kv2.setLabels(labelsList);		
				StrkVList.add(kv2);
			}			
			if(i == 3) {
				Graph feedDetails = feedBackService.getnoofFeedBacksPerQtAtPerfolioLevel(portfolioId, "VENDOR");
				KPIValue kv2 = new KPIValue();

				kv2.setName(env.getProperty("strategicalkpi.name3"));
				kv2.setGraphdataOfIdeas(feedDetails.getGraphData());
				kv2.setLabels(labelsList);
				StrkVList.add(kv2);
			}
			
			if(i == 4) {
				ValueAddAcceptedIdeas valueForQuater = vmService.getEconomicValueAddByPortfolio(portfolioId);
				KPIValue kv2 = new KPIValue();
				kv2.setName(env.getProperty("strategicalkpi.name4"));

				kv2.setGraphdataOfIdeas(valueForQuater.getGraphdata());
				kv2.setLabels(valueForQuater.getLabels());
				StrkVList.add(kv2);
			}
		}
		return StrkVList;
	}
}
