package com.hcl.hawkeye.batch.build.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hcl.hawkeye.batch.build.DAO.BuildBatchDAO;
import com.hcl.hawkeye.batch.build.DO.BambooPlan;
import com.hcl.hawkeye.batch.build.DO.BambooResultsDO;
import com.hcl.hawkeye.batch.build.DO.BuildManageConfig;
import com.hcl.hawkeye.batch.build.DO.Result;
import com.hcl.hawkeye.batch.build.service.BuildBatchService;
import com.hcl.hawkeye.buildmanagement.DO.BuildStatisticsDetails;

@Repository
@Configuration
@PropertySource("classpath:config.properties")
public class BuildBatchServiceImpl implements BuildBatchService {

	@Autowired
	BuildBatchDAO buildBatchDAO;

	@Autowired
	Environment confProperties;

	@Override
	public List<BuildManageConfig> getBuildConfig() {
		return buildBatchDAO.getBuildConfig();
	}

	@Override
	public void getRestDataFromTool(BuildManageConfig buildManConf) {
		if(buildManConf.getToolname().equals("BAMBOO")){
			getBambooRestDetails(buildManConf);
			restCallToPopulateBambooData(buildManConf);
		}
	}
	
	

	public void restCallToPopulateBambooData(BuildManageConfig buildManConf) {
		List<BuildStatisticsDetails> buildstatsList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		for(double i= buildManConf.getHawkeyeBuildNo()+1 ;i<=buildManConf.getBuildNoLatest();i++){
			StringBuilder bambooRestUrl = new StringBuilder();
			bambooRestUrl.append(buildManConf.getToolurl()).append(buildManConf.getToolhost())
			.append("/").append(confProperties.getProperty("bamboo.resturl")).append("/").append(buildManConf.getToolProjectId()).append("-").append(i).append(".json");
			BambooResultsDO specificPlanresult = restTemplate.getForObject(bambooRestUrl.toString(), BambooResultsDO.class);
			if(null!=specificPlanresult){
				BuildStatisticsDetails buildStatisticsDetails = new BuildStatisticsDetails();
				buildStatisticsDetails.setProjectId((int) buildManConf.getProjectid());
				buildStatisticsDetails.setPlanKey(specificPlanresult.getPlan().getKey());
				buildStatisticsDetails.setBuildTool(buildManConf.getToolname());
				buildStatisticsDetails.setBuildNumber(Integer.valueOf(specificPlanresult.getBuildNumber()));
				buildStatisticsDetails.setBuildId(Integer.valueOf(specificPlanresult.getId()));
				buildStatisticsDetails.setPlanName(specificPlanresult.getPlan().getName());
				buildStatisticsDetails.setPlanShortName(specificPlanresult.getPlan().getShortName());
				buildStatisticsDetails.setBuildResultKey(specificPlanresult.getBuildResultKey());
				buildStatisticsDetails.setBuildState(specificPlanresult.getBuildState());
				buildStatisticsDetails.setLifeCycleState(specificPlanresult.getLifeCycleState());
				buildStatisticsDetails.setEnabled(specificPlanresult.getPlan().getEnabled());  
				buildStatisticsDetails.setType(specificPlanresult.getPlan().getType());
				buildStatisticsDetails.setPlanShortName(specificPlanresult.getPlan().getShortName());
				buildStatisticsDetails.setProjectName(specificPlanresult.getProjectName());
				buildStatisticsDetails.setIsBuilding("false");
				buildStatisticsDetails.setIsActive("false");
				buildStatisticsDetails.setBuildName(specificPlanresult.getProjectName());
				buildStatisticsDetails.setBuildStartedTime(specificPlanresult.getPrettyBuildStartedTime().replace("T", " ")); 
				buildStatisticsDetails.setBuildCompletedTime(specificPlanresult.getPrettyBuildCompletedTime().replace("T", " "));  
				buildStatisticsDetails.setBuildDurationInSeconds(Integer.valueOf(specificPlanresult.getBuildDurationInSeconds()));
				buildStatisticsDetails.setVcsrevisionKey(specificPlanresult.getVcsRevisionKey());
				buildStatisticsDetails.setBuildTestSummary(specificPlanresult.getBuildTestSummary());
				buildStatisticsDetails.setBuildReason(specificPlanresult.getBuildReason().toString());
				buildstatsList.add(buildStatisticsDetails);
			}
		}

	}

	public void getBambooRestDetails(BuildManageConfig buildManConf) {
		// get the latest buildplan details for the given project by restcall http://52.24.89.48/bamboo/rest/api/latest/result/
		StringBuilder bambooRestUrl = new StringBuilder();
		bambooRestUrl.append(buildManConf.getToolurl()).append(buildManConf.getToolhost())
		.append("/").append(confProperties.getProperty("bamboo.resturl")).append(".json");
		RestTemplate restTemplate = new RestTemplate();
		BambooPlan latestPlanresults = restTemplate.getForObject(bambooRestUrl.toString(), BambooPlan.class);
		//updating the latestBuildNo as per Rest data for the specific plan.
		if(null!=latestPlanresults){
			for(Result bambooresult : latestPlanresults.getResults().getResult()){
				if(bambooresult.getPlan().getKey().equals(buildManConf.getToolProjectId())){
					buildManConf.setBuildNoLatest(Double.valueOf(bambooresult.getNumber()));
				}
			}
		}
	}


}
