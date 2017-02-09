package com.hcl.hawkeye.valueaddmanagement.DAO;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;

public interface ValueAddManagementDAO {

	ValueAdd getNumbersOfValueAdd();
	
	ValueAdd getValueAddByIds(Integer programId, Integer portfolioId);

	Value createValueAdd(Value value);
	
	ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId);

}
