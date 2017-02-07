package com.hcl.hawkeye.programmanagement.DAO;

import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface ProgramManagementDAO {

	Program addProgram(Program prog);

	Project addProjectsToProgram(Project project);

	EscalationDetails noOfProgramsInQuarter();

}
