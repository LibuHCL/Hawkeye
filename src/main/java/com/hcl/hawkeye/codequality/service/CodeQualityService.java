package com.hcl.hawkeye.codequality.service;

import java.util.List;

import com.hcl.hawkeye.codequality.DO.Resource;

public interface CodeQualityService {
	
	int getCodeQualityRAGStatus();
	
	void insertQualityDetails(List<Resource> resources);

}
