package com.hcl.hawkeye.valueaddmanagement.service;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;

public interface ValueAddManagementService {

	ValueAdd getNumberOfValueAdd();

	ValueAdd getValueAddByIds(Integer programId, Integer portfolioId);
	
	Value createValue(Value value);
	
	ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId);
	
	ValueCreation getValueCreationByProgramId(Integer programId);
	
	ValueCreation getValueCreationByProjectId(Integer projectId);
}
