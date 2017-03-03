package com.hcl.hawkeye.projectmanagement.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.Exceptions.NoProjectDetailsException;
import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.Issues;
import com.hcl.hawkeye.projectmanagement.DO.KanbanProDetails;
import com.hcl.hawkeye.projectmanagement.DO.ProjectIssues;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Sprints;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;
import com.hcl.hawkeye.utils.HawkEyeUtils;

/**
 * 
 * @author Haribabu
 *
 */
@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementServiceImpl.class);

	@Autowired
	ProjectManagementDAO pmDAO;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public SprintDetailsOfProject getProjectDetails(int projectId) {
		SprintDetailsOfProject proDetails = pmDAO.getProjectDetails(projectId);
		return proDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		DashBoardDetails dashBoardInfo = pmDAO.getDashBoardInfo();
		return dashBoardInfo;
	}

	@Override
	public int getVelocityOfProject(int projectId) {
		logger.info("Processing to get the velocity of project: {}", projectId );
		int count = 0;
		if (27 == projectId) {
			Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
			List<VelocityOfProject> velocityList = getVelocityList(vInfo);
			Double estimated = 0.0;
			Double completed = 0.0;
			if(!velocityList.isEmpty()){
				for (VelocityOfProject velocityOfProject : velocityList) {
					estimated +=  velocityOfProject.getEstimatedValue();
					completed +=  velocityOfProject.getCompletedValue();
				}
			}else{
				return 0;
			}
			count = HawkEyeUtils.getRAGStatus( (int)((completed/estimated)*100));
		} else {
			Random rm = new Random();
			int count1 = rm.nextInt(100-75)+75;
			count = HawkEyeUtils.getRAGStatus(count1);
		}
		return count;
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		Map<String, Integer> issues = pmDAO.getIssuesOfProject(projectId, issueType);
		return issues;
	}

	@Override
	public Map<String, Map<String, Integer>> getPriorityOfIssue(int projectId, String blockerType, String criticalType) {
		//Map<String, Map<String, Integer>> priorityIssue = pmDAO.getPriorityOfIssue(projectId, blockerType, criticalType);
		Map<String, Map<String, Integer>> priorityIssue = pmDAO.getBusinessContinuity(projectId, blockerType, criticalType);
		return priorityIssue;
	}

	@Override
	public List<VelocityOfProject> getVelocityOfSprint(int projectId) {
		//Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
		List<VelocityOfProject> velocityList = pmDAO.getProductivityOfProject(projectId);
		//List<VelocityOfProject> velocityList = getVelocityList(vInfo);
		Collections.sort(velocityList,new Comparator<VelocityOfProject>() {
	         @Override
	        public int compare(VelocityOfProject s1, VelocityOfProject s2) {
	                return s1.getSprintName().compareToIgnoreCase(s2.getSprintName());
	        }
	    });
		return velocityList;
	}
	
	private List<VelocityOfProject> getVelocityList(Velocityinfo vInfo) {
		List<VelocityOfProject> velocityList = new ArrayList<VelocityOfProject>();
		if (null != vInfo && !vInfo.getSprints().isEmpty()) {
			for ( Sprints sprint  : vInfo.getSprints()) {
				VelocityOfProject velocityOfProject = new VelocityOfProject();
				velocityOfProject.setSprintId(sprint.getId());
				velocityOfProject.setSprintName(sprint.getName());
				velocityOfProject.setSprintState(sprint.getState());
				Map<String, Map<String,Double>> sMap = vInfo.getVelocityStatEntries().get(Integer.toString(sprint.getId()));
				for (String key : sMap.keySet()) {
					if ("estimated".equals(key)) {
						Map<String,Double> inMap = sMap.get(key);
						for (String inVal : inMap.keySet()) {
							if ("value".equals(inVal)) {
								velocityOfProject.setEstimatedValue(Double.valueOf(inMap.get(inVal)));
							}
						}
					} if ("completed".equals(key)) {
						Map<String,Double> inMap = sMap.get(key);
						for (String inVal : inMap.keySet()) {
							if ("value".equals(inVal)) {
								velocityOfProject.setCompletedValue(Double.valueOf(inMap.get(inVal)));
							}
						}
					}
				}
				velocityList.add(velocityOfProject);
			}
		}
		return velocityList;
	}

	@Override
	public DefectTypes getDefectTypesOfProject(int projectId) {
		DefectTypes dTypes= pmDAO.getDefectTypesOfProject(projectId);
		return dTypes;
	}

	@Override
	public KanbanProDetails  getKanbanProjectDetails(int projectId) {
		logger.info("Requested to get the kanban project details with project Id: {}", projectId);
		int totalTickets = 0;
		int activeTickets = 0;
		int ticketsPerMonth = 0;
		int ticPerMonth = 0;
		Map<String, Integer> completedInMonth= new LinkedHashMap<>();
		Map<String, Integer> createdInMonth= new LinkedHashMap<>();
		ProjectIssues pIssues = pmDAO.getKanbanProjectDetails(projectId);
		KanbanProDetails kDetails = new KanbanProDetails();
		try {
			if (null != pIssues && null != pIssues.getIssues() && !pIssues.getIssues().isEmpty()) {
				for (Issues issue : pIssues.getIssues()) {
					totalTickets++;
					if (null != issue.getFields().getResolutiondate()) {
						String monthName = getMonthOfDate(getFormattedDate(issue.getFields().getResolutiondate()));
						if (!completedInMonth.containsKey(monthName)) {
							ticketsPerMonth++;
							completedInMonth.put(monthName, ticketsPerMonth);
						} else {
							ticketsPerMonth++;
							completedInMonth.put(monthName, ticketsPerMonth);
						}
					}
					
					if (null != issue.getFields().getCreated()) {
						String monthName = getMonthOfDate(getFormattedDate(issue.getFields().getCreated()));
						if (!createdInMonth.containsKey(monthName)) {
							ticPerMonth++;
							createdInMonth.put(monthName, ticPerMonth);
						} else {
							ticPerMonth++;
							createdInMonth.put(monthName, ticPerMonth);
						}
					}
					
					if (!"done".equals(issue.getFields().getStatus().getStatusCategory().getKey())) {
						activeTickets++;
					}
				}
			}
			kDetails.setTotalTickets(totalTickets);
			kDetails.setActiveTickets(activeTickets);
			kDetails.setResolvedTicketsOfMonth(completedInMonth);
			kDetails.setCreatedTicketsOfMonth(createdInMonth);
		} catch (Exception e) {
			Locale locale=new Locale("en", "IN");
			String errorMsg=messageSource.getMessage("error.get.defects", new Object[] {}, locale);
			logger.error(errorMsg, e);
			throw new NoProjectDetailsException(errorMsg, e);
		}
		
		return kDetails;
	}

	private String getFormattedDate(String time) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String date;
		Date d = simpleDateFormat.parse(time);
		date = new SimpleDateFormat("yyyy-MMM-dd").format(d);
		return date;
	}
	
	private String getMonthOfDate(String date) throws ParseException {
		int val = new SimpleDateFormat("yyyy-MMM-dd").parse(date).getMonth();
		return getMonthName(val);
	}
	
	
	private String getMonthName(int val) {
		switch (val) {
		case 0: return "Jan"; case 1: return "Feb"; case 2: return "Mar";
		case 3: return "Apr"; case 4: return "May"; case 5: return "Jun";
		case 6: return "Jul"; case 7: return "Aug"; case 8: return "Sep";
		case 9: return "Oct"; case 10: return "Nov"; case 11: return "Dec";

		default: return "Jan";
		}
	}
}
