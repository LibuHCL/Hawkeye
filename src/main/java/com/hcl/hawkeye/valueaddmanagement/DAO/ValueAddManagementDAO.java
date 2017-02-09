package com.hcl.hawkeye.valueaddmanagement.DAO;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;

public interface ValueAddManagementDAO {

	ValueAdd getNumbersOfValueAdd();
	
	ValueAdd getValueAddByIds(Integer programId, Integer portfolioId);

	Value createValueAdd(Value value);
	
	ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId);
	
	ValueCreation getValueCreationByProgramId(Integer programId);
	
	ValueCreation getValueCreationByProjectId(Integer projectId);

}
