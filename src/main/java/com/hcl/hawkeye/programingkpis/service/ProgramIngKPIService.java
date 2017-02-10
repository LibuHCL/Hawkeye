package com.hcl.hawkeye.programingkpis.service;

import com.hcl.hawkeye.programingkpis.DO.Result;

public interface ProgramIngKPIService {
	
	Result getOperationalKpiResults(int projectId);

	Result getTacticalKpiResults(int projectId);

}
