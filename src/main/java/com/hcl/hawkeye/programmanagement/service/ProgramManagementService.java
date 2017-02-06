package com.hcl.hawkeye.programmanagement.service;

import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface ProgramManagementService {

	Program addProgram(Program program);

	Integer noOfProgramsInQuarter();

	Project addProjectsToProgram(Project project);

	

}
