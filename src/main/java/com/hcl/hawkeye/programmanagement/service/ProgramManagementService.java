package com.hcl.hawkeye.programmanagement.service;

import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface ProgramManagementService {

	Program addProgram(Program program);

	EscalationDetails noOfProgramsInQuarter();

	Project addProjectsToProgram(Project project);

	

}
