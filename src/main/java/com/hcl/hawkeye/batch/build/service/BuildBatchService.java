package com.hcl.hawkeye.batch.build.service;

import java.util.List;

import com.hcl.hawkeye.batch.build.DO.BuildManageConfig;

public interface BuildBatchService {
	List<BuildManageConfig> getBuildConfig();

	void getRestDataFromTool(BuildManageConfig buildManConf);
}
