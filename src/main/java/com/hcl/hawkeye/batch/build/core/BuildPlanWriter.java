package com.hcl.hawkeye.batch.build.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hawkeye.batch.build.DAO.BuildBatchDAO;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;

public class BuildPlanWriter implements ItemWriter<List<BuildStatisticsDetails>> {

	private static final Logger logger = LoggerFactory.getLogger(BuildPlanWriter.class);

	// private StepExecution stepExecution;

	@Autowired
	BuildBatchDAO buildBatchDAO;

	private StepExecution stepExecution;

	@Override
	public void write(List<? extends List<BuildStatisticsDetails>> details) throws Exception {
		logger.info("Build statistics details from reader to write the data coming from the Reader");
		List<BuildStatisticsDetails> resultList = new ArrayList<>();
		if (null != details) {
			for (List<BuildStatisticsDetails> det : details) {
				for (BuildStatisticsDetails values : det) {
					BuildStatisticsDetails result = new BuildStatisticsDetails();
					result.setId(values.getId());
					result.setBuildTool(values.getBuildTool());
					result.setProjectId(values.getProjectId());
					result.setBuildNumber(values.getBuildNumber());
					result.setPlanName(values.getPlanName());
					result.setPlanShortName(values.getPlanShortName());
					result.setPlanKey(values.getPlanKey());
					result.setBuildResultKey(values.getBuildResultKey());
					result.setBuildState(values.getBuildState());
					result.setLifeCycleState(values.getLifeCycleState());
					result.setEnabled(values.getEnabled());
					result.setType(values.getType());
					result.setProjectKey(values.getProjectKey());
					result.setProjectName(values.getProjectName());
					result.setIsActive(values.getIsActive());
					result.setBuildName(values.getBuildName());
					result.setIsBuilding(values.getIsBuilding());
					result.setBuildStartedTime(values.getBuildStartedTime());
					result.setBuildCompletedTime(values.getBuildCompletedTime());
					result.setBuildDurationInSeconds(values.getBuildDurationInSeconds());
					result.setVcsrevisionKey(values.getVcsrevisionKey());
					result.setBuildTestSummary(values.getBuildTestSummary());
					result.setBuildReason(values.getBuildReason());
					result.setBuildId(values.getBuildId());
					resultList.add(result);
				}
			}
		}
		try {
			boolean insertStatus = buildBatchDAO.insertBuildStatisticsDetails(resultList);
			if (insertStatus) {
				logger.info("Inserted succesfully");
				ExecutionContext stepContext = this.stepExecution.getExecutionContext();
				stepContext.put("buildDetails", resultList);
			} else {
				logger.error("Already existing data present");
			}
		} catch (Exception e) {
			logger.info("unable to insert the data");
		}

	}

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
