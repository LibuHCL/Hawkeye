package com.hcl.hawkeye.valueaddmanagement.service;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;

public interface ValueAddManagementService {

	ValueAdd getNumberOfValueAdd();

	ValueAdd getValueAddByIds(Integer programId, Integer portfolioId);
	
	Value createValue(Value value);
}
