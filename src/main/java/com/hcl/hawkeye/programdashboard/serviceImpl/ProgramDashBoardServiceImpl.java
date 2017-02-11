/**
 * 
 */
package com.hcl.hawkeye.programdashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.programdashboard.DO.ProgramDashBoard;
import com.hcl.hawkeye.programdashboard.DO.ProgramTypes;
import com.hcl.hawkeye.programdashboard.DO.ProjectDetails;
import com.hcl.hawkeye.programdashboard.DO.ProjectStates;
import com.hcl.hawkeye.programdashboard.DO.ProjectSubTypes;
import com.hcl.hawkeye.programdashboard.service.ProgramDashBoardService;
import com.hcl.hawkeye.programmanagement.service.ProgramManagementService;

@Service
public class ProgramDashBoardServiceImpl implements ProgramDashBoardService {

	private static final Logger logger = LoggerFactory.getLogger(ProgramDashBoardServiceImpl.class);

	@Autowired
	ProgramManagementService progMgmtService;

	@Override
	public ProgramDashBoard getProgramDashBoard(int programId) {
		logger.info("Requested to get the programDasboard Data for Project ID: {}", programId);
		List<Project> projectsfromdb = progMgmtService.getProjectsPerProgramId(programId);
		
		ProgramDashBoard programdashboard = new ProgramDashBoard();
		List<ProgramTypes> proTypes = new ArrayList<>();
		Map<String, ProgramTypes> programTypeMap = new HashMap<>();
		
		List<Project> devProjects =new ArrayList<>();
		List<Project> asmProjects =new ArrayList<>();
		
		for (Project project : projectsfromdb) {
			if ("DEV".equals(project.getProjType())) {
				devProjects.add(project);
			} else if ("ASM".equals(project.getProjType())) {
				asmProjects.add(project);
			}
		}
		
		processProjectList(devProjects, programTypeMap, "DEV");
		processProjectList(asmProjects, programTypeMap, "ASM");
		
		for (String programTypes : programTypeMap.keySet()) {
			proTypes.add(programTypeMap.get(programTypes));
		}
		programdashboard.setResult(proTypes);
		return programdashboard;
	}

	private void processProjectList(List<Project> devProjects, Map<String, ProgramTypes> programTypeMap, String string) {
		Map<String, List<ProjectStates>> pStateMap = new HashMap<>();
		List<Project> activeList =new ArrayList<>();
		List<Project> closedList =new ArrayList<>();
		List<Project> upComingList =new ArrayList<>();
		if ("DEV".equals(string)) {
			for (Project project : devProjects) {
				if ("ACTIVE".equals(project.getStatus())) {
					activeList.add(project);
				} else if ("CLOSED".equals(project.getStatus())) {
					closedList.add(project);
				} else if ("UPCOMING".equals(project.getStatus())) {
					upComingList.add(project);
				}
			}
			
		} else if ("ASM".equals(string)) {
			for (Project project : devProjects) {
				if ("ACTIVE".equals(project.getStatus())) {
					activeList.add(project);
				} else if ("CLOSED".equals(project.getStatus())) {
					closedList.add(project);
				} else if ("UPCOMING".equals(project.getStatus())) {
					upComingList.add(project);
				}
			}
			processList(activeList, "ACTIVE", pStateMap);
			processList(closedList, "CLOSED", pStateMap);
			processList(upComingList, "UPCOMING", pStateMap);
		}
		
		processList(activeList, "ACTIVE", pStateMap);
		processList(closedList, "CLOSED", pStateMap);
		processList(upComingList, "UPCOMING", pStateMap);
		
		if ("DEV".equals(string)) {
			ProgramTypes pt = new ProgramTypes();
			List<ProjectStates> d = new ArrayList<>();
			
			for (String pSt : pStateMap.keySet()) {
				d.addAll(pStateMap.get(pSt));
			}
			pt.setProgramSubArr(d);
			pt.setProgramName("App Development");
			pt.setProgramId(1001);
			programTypeMap.put("DEV", pt);
		} else if ("ASM".equals(string)) {
			ProgramTypes pt = new ProgramTypes();
			List<ProjectStates> d = new ArrayList<>();
			
			for (String pSt : pStateMap.keySet()) {
				d.addAll(pStateMap.get(pSt));
			}
			pt.setProgramSubArr(d);
			pt.setProgramName("App Maintenance & Support");
			pt.setProgramId(1002);
			programTypeMap.put("ASM", pt);
		}
	}
	
	private void processList(List<Project> activeList, String type, Map<String, List<ProjectStates>> pStateMap) {
		Map<String, List<ProjectDetails>> devMap = new HashMap<>();
		for (Project project : activeList) {
			processDevProjects(project, devMap);
		}
		if ("ACTIVE".equals(type)) {
			getProjectTypes(devMap, pStateMap, type);
		} else if ("CLOSED".equals(type)) {
			getProjectTypes(devMap, pStateMap, type);
		} else if ("UPCOMING".equals(type)) {
			getProjectTypes(devMap, pStateMap, type);
		}
		
	}

	private void getProjectTypes(Map<String, List<ProjectDetails>> devMap, Map<String, List<ProjectStates>> pStateMap2, String type) {
		List<ProjectSubTypes> subTypeList =new ArrayList<>();
		List<ProjectStates> pStateList = new ArrayList<>();
		for (String key : devMap.keySet()) {
			if ("WEB".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setSteaamProjectCount(devMap.get(key).size());
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("DATA".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setSteaamProjectCount(devMap.get(key).size());
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("LEGACY".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setSteaamProjectCount(devMap.get(key).size());
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("MOBILE".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setSteaamProjectCount(devMap.get(key).size());
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			}
		}
		if ("ACTIVE".equals(type)) {
			ProjectStates activeState = new ProjectStates();
			activeState.setStream(subTypeList);
			activeState.setProgramStatus("Active Projects");
			activeState.setProgramCount(subTypeList.size());
			pStateList.add(activeState);
			pStateMap2.put("ACTIVE", pStateList);
		} else if ("CLOSED".equals(type)) {
			ProjectStates closedState = new ProjectStates();
			closedState.setStream(subTypeList);
			closedState.setProgramStatus("Closed Projects");
			closedState.setProgramCount(subTypeList.size());
			pStateList.add(closedState);
			pStateMap2.put("CLOSED", pStateList);
		} else if ("UPCOMING".equals(type)) {
			ProjectStates upcomingState = new ProjectStates();
			upcomingState.setStream(subTypeList);
			upcomingState.setProgramStatus("Forth Coming Projects");
			upcomingState.setProgramCount(subTypeList.size());
			pStateList.add(upcomingState);
			pStateMap2.put("UPCOMING", pStateList);
		} 
		
	}
	private void processDevProjects(Project project, Map<String, List<ProjectDetails>> devMap) {
		List<ProjectDetails> pDetailList = new ArrayList<>();
		if ("WEB".equals(project.getSubType())) {
			if (!devMap.containsKey("WEB")) {
				ProjectDetails pDet = processProject(project);
				pDetailList.add(pDet);
				devMap.put(project.getSubType(), pDetailList);
			} else {
				List<ProjectDetails> pDetList = devMap.get("WEB");
				ProjectDetails pDet = processProject(project);
				pDetList.add(pDet);
				devMap.put(project.getSubType(), pDetList);
			}

		} else if ("DATA".equals(project.getSubType())) {
			if (!devMap.containsKey("DATA")) {
				ProjectDetails pDet = processProject(project);
				pDetailList.add(pDet);
				devMap.put(project.getSubType(), pDetailList);
			} else {
				List<ProjectDetails> pDetList = devMap.get("DATA");
				ProjectDetails pDet = processProject(project);
				pDetList.add(pDet);
				devMap.put(project.getSubType(), pDetList);
			}

		} else if ("LEGACY".equals(project.getSubType())) {
			if (!devMap.containsKey("LEGACY")) {
				ProjectDetails pDet = processProject(project);
				pDetailList.add(pDet);
				devMap.put(project.getSubType(), pDetailList);
			} else {
				List<ProjectDetails> pDetList = devMap.get("LEGACY");
				ProjectDetails pDet = processProject(project);
				pDetList.add(pDet);
				devMap.put(project.getSubType(), pDetList);
			}

		} else if ("MOBILE".equals(project.getSubType())) {
			if (!devMap.containsKey("MOBILE")) {
				ProjectDetails pDet = processProject(project);
				pDetailList.add(pDet);
				devMap.put(project.getSubType(), pDetailList);
			} else {
				List<ProjectDetails> pDetList = devMap.get("MOBILE");
				ProjectDetails pDet = processProject(project);
				pDetList.add(pDet);
				devMap.put(project.getSubType(), pDetList);
			}

		}
	}

	private ProjectDetails processProject(Project project) {
		ProjectDetails pDetail = new ProjectDetails();
		pDetail.setProjectId(project.getProjectId());
		pDetail.setProjectName(project.getProjName());
		return pDetail;
	}

}
