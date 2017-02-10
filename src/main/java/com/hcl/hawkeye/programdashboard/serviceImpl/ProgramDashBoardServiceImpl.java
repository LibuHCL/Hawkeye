/**
 * 
 */
package com.hcl.hawkeye.programdashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

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
		List <Project> projectsfromdb = progMgmtService.getProjectsPerProgramId(programId);
		ProgramDashBoard programdashboard = new ProgramDashBoard();
		List<ProgramTypes> pTypeList = new ArrayList<>();
		ProgramTypes pDevType = new ProgramTypes();
		ProgramTypes pASMType = new ProgramTypes();
		for(Project project : projectsfromdb){
					
			if ("DEV".equals(project.getProjType())) {
				pDevType.setProgramName(project.getProjType());
				pDevType.setProgramId(project.getProgId());
				List<ProjectStates> pStateList = new ArrayList<>();
				ProjectStates ps1 = new ProjectStates();
				ProjectStates ps2 = new ProjectStates();
				ProjectStates ps3 = new ProjectStates();
				if ("ACTIVE".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					ps1.setProgramStatus(project.getStatus());
					
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					if ("WEB".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType2.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType3.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps1.setProgramCount(count);
					
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps1.setStream(psTypeList);
					pStateList.add(ps1);
				}
				if("CLOSED".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					ps2.setProgramStatus(project.getStatus());
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					if ("WEB".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps2.setProgramCount(count);
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps2.setStream(psTypeList);
					pStateList.add(ps2);
				}
				
				if("UPCOMING".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					ps3.setProgramStatus(project.getStatus());
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					if ("WEB".equals(project.getSubType())) {
						sType1.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType2.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType3.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps3.setProgramCount(count);
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps3.setStream(psTypeList);
					pStateList.add(ps3);
					
				}
				pDevType.setProgramSubArr(pStateList);
				
			}
			if ("ASM".equals(project.getProjType())) {
				pASMType.setProgramName(project.getProjType());
				pASMType.setProgramId(project.getProgId());
				List<ProjectStates> pStateList = new ArrayList<>();
				ProjectStates ps1 = new ProjectStates();
				ProjectStates ps2 = new ProjectStates();
				ProjectStates ps3 = new ProjectStates();
				if ("ACTIVE".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					ps1.setProgramStatus(project.getStatus());
					
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					
					if ("WEB".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps1.setProgramCount(count);
					
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps1.setStream(psTypeList);
					pStateList.add(ps1);
					
				}
				if("CLOSED".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					
					ps2.setProgramStatus(project.getStatus());
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					if ("WEB".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps2.setProgramCount(count);
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps2.setStream(psTypeList);
					pStateList.add(ps2);
				}
				
				if("UPCOMING".equals(project.getStatus())) {
					List<ProjectSubTypes> psTypeList = new ArrayList<>();
					ProjectSubTypes sType1 = new ProjectSubTypes();
					ProjectSubTypes sType2 = new ProjectSubTypes();
					ProjectSubTypes sType3 = new ProjectSubTypes();
					ProjectSubTypes sType4 = new ProjectSubTypes();
					
					ps3.setProgramStatus(project.getStatus());
					List<ProjectDetails> pWebList = new ArrayList<>();
					List<ProjectDetails> pDataList = new ArrayList<>();
					List<ProjectDetails> pLegacyList = new ArrayList<>();
					List<ProjectDetails> pMobileList = new ArrayList<>();
					if ("WEB".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("WEB", project);
						pWebList.add(pst);
					}
					if ("DATA".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("DATA", project);
						pDataList.add(pst);
					}
					if ("LEGACY".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("LEGACY", project);
						pLegacyList.add(pst);
					}
					if ("MOBILE".equals(project.getSubType())) {
						sType4.setStreamName(project.getSubType());
						ProjectDetails pst= getSubTypes("MOBILE", project);
						pMobileList.add(pst);
					}
					int count = pWebList.size()+pDataList.size()+pLegacyList.size()+pMobileList.size();
					ps3.setProgramCount(count);
					sType1.setProjects(pWebList);
					sType2.setProjects(pDataList);
					sType3.setProjects(pLegacyList);
					sType4.setProjects(pMobileList);
					psTypeList.add(sType1);psTypeList.add(sType3);
					psTypeList.add(sType2);psTypeList.add(sType4);
					ps3.setStream(psTypeList);
					pStateList.add(ps3);
				}
				pASMType.setProgramSubArr(pStateList);
			}
		
		}
		pTypeList.add(pDevType);pTypeList.add(pASMType);
		programdashboard.setResult(pTypeList);
		return programdashboard;
	}
	
	private ProjectDetails getSubTypes(String type, Project project) {
		ProjectDetails pDetails = new ProjectDetails();
		if (type.equals(project.getSubType())) {
			pDetails.setProjectName(project.getProjName());
			pDetails.setProjectId(project.getProjectId());
		}
		return pDetails;
	}

}
