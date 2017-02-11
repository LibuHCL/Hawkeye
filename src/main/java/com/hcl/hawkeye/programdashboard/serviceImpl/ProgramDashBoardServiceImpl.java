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
		
		List<Project> activeList =new ArrayList<>();
		List<Project> closedList =new ArrayList<>();
		List<Project> upComingList =new ArrayList<>();
		
		for (Project project : projectsfromdb) {
			
			if ("DEV".equals(project.getProjType())) {
				if ("ACTIVE".equals(project.getStatus())) {
					activeList.add(project);
				} else if ("CLOSED".equals(project.getStatus())) {
					closedList.add(project);
				} else if ("UPCOMING".equals(project.getStatus())) {
					upComingList.add(project);
				}
				//processDevProjects(project, pDetailListDev, devMap);
			} else if ("ASM".equals(project.getProjType())) {
				//processDevProjects(project, pDetailListASM, asmMap);
			}
			
		}
		
		
		processList(activeList, programTypeMap, "ACTIVE");
		processList(closedList, programTypeMap, "CLOSED");
		processList(upComingList, programTypeMap, "UPCOMING");
		for (String programTypes : programTypeMap.keySet()) {
			proTypes.add(programTypeMap.get(programTypes));
		}
		programdashboard.setResult(proTypes);
		return programdashboard;
	}

	private void processList(List<Project> activeList, Map<String, ProgramTypes> programTypeMap, String type) {
		Map<String, List<ProjectDetails>> devMap = new HashMap<>();
		Map<String, List<ProjectStates>> pStateMap = new HashMap<>();
		List<ProjectStates> d = new ArrayList<>();
		ProgramTypes pt = new ProgramTypes();
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
		
		//getProjectTypes(devMap, pStateMap, type);
		for (String pSt : pStateMap.keySet()) {
			d.addAll(pStateMap.get(pSt));
		}
		pt.setProgramSubArr(d);
		pt.setProgramName("App Development");
		pt.setProgramId(1001);
		programTypeMap.put("DEV", pt);
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

	private void processActiveProjects(Project project, List<ProjectSubTypes> subTypeList,
			Map<String, List<ProjectSubTypes>> subMap, Map<String, List<ProjectDetails>> devMap, String string,
			Map<String, ProgramTypes> programTypeMap) {
		List<ProjectStates> pStateList = new ArrayList<>();
		
		if ("ACTIVE".equals(project.getStatus())) {
			getSubType(devMap, subTypeList, subMap, project.getStatus());
			ProjectStates pSubType = new ProjectStates();
			pSubType.setProgramStatus("Active Projects");
			pSubType.setStream(subMap.get(project.getStatus()));
			pSubType.setProgramCount(subMap.get(project.getStatus()).size());
			pStateList.add(pSubType);
		} else if ("CLOSED".equals(project.getStatus())) {
			getSubType(devMap, subTypeList, subMap, project.getStatus());
			ProjectStates pSubType = new ProjectStates();
			pSubType.setProgramStatus("Closed Projects");
			pSubType.setStream(subMap.get(project.getStatus()));
			pSubType.setProgramCount(subMap.get(project.getStatus()).size());
			pStateList.add(pSubType);
		} else if ("UPCOMING".equals(project.getStatus())) {
			//getSubType(devMap, subTypeList, subMap, project.getStatus());
			ProjectStates pSubType = new ProjectStates();
			pSubType.setProgramStatus("Forth Coming Projects");
			pSubType.setStream(subMap.get(project.getStatus()));
			pSubType.setProgramCount(subMap.get(project.getStatus()).size());
			pStateList.add(pSubType);
		}
		ProgramTypes type = new ProgramTypes();
		if ("DEV".equals(string)) {
			type.setProgramName("App Development");
			type.setProgramId(1001);
		} else if ("ASM".equals(string)) {
			type.setProgramName("App Maintenance & Support");
			type.setProgramId(1002);
		}
		type.setProgramSubArr(pStateList);
		programTypeMap.put(string, type);
	}

	private void getSubType(Map<String, List<ProjectDetails>> devMap, List<ProjectSubTypes> subTypeList,
			Map<String, List<ProjectSubTypes>> subMap, String string) {
		for (String key : devMap.keySet()) {
			if ("WEB".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("DATA".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("LEGACY".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			} else if ("MOBILE".equals(key)) {
				ProjectSubTypes subType = new ProjectSubTypes();
				subType.setStreamName(key);
				subType.setProjects(devMap.get(key));
				subTypeList.add(subType);
			}

		}
		subMap.put(string, subTypeList);
	}

	private ProjectDetails processProject(Project project) {
		ProjectDetails pDetail = new ProjectDetails();
		pDetail.setProjectId(project.getProjectId());
		pDetail.setProjectName(project.getProjName());
		return pDetail;
	}

}
