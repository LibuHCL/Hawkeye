package com.hcl.hawkeye.valueaddmanagement.DAO;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;

public interface ValueAddManagementDAO {

	ValueAdd getNumbersOfValueAdd();
	
	ValueAdd getValueAddByIds(Integer programId, Integer portfolioId);

	Value createValueAdd(Value value);

}
