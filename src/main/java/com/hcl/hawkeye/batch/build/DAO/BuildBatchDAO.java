package com.hcl.hawkeye.batch.build.DAO;

import java.util.List;

import com.hcl.hawkeye.batch.build.DO.BuildManageConfig;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;

public interface BuildBatchDAO {

	List<BuildManageConfig> getBuildConfig();
	boolean insertBuildStatisticsDetails(List<BuildStatisticsDetails> buildDetails);
}
