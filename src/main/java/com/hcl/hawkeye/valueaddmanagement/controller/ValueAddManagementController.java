package com.hcl.hawkeye.valueaddmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreationQuarterly;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueIndex;
import com.hcl.hawkeye.valueaddmanagement.service.ValueAddManagementService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValueAddManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ValueAddManagementController.class);
	
	@Autowired
	public ValueAddManagementService valueAddManagementService;
	
	// Create Value
		@RequestMapping(value = "/createValueAdd", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Value>  createValue(@RequestBody Value value) {
			System.out.println("Value:"+value);
			logger.info("Inside createValue method in Controller:"+value);
			Value createdValue= valueAddManagementService.createValue(value);
			logger.info("Value Add inserted successfully: " +value);
			return new ResponseEntity<>(createdValue, HttpStatus.CREATED);
		}
	
	@RequestMapping(value="/getValueAddData", method = RequestMethod.GET)
	@ResponseBody
	public ValueAdd getNumberOfValueAdd() {
		logger.info("Requested to get the valueAdd information for Portfolio");
		ValueAdd valueAdd = new ValueAdd();
		valueAdd = valueAddManagementService.getNumberOfValueAdd();
		return valueAdd;
	}
	
	@RequestMapping(value="/getValueAddDataByProg/{programID}", method = RequestMethod.GET)
	@ResponseBody
	public ValueAdd getNumberOfValueAddByProg() {
		logger.info("Requested to get the valueAdd information for Program");
		ValueAdd valueAdd = new ValueAdd();
		valueAdd = valueAddManagementService.getNumberOfValueAdd();
		return valueAdd;
	}
	
	@RequestMapping(value="/getValueAddAccepted/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(@PathVariable("programId") Integer programId) {
		logger.info("Requested to get the valueAdd information for Program");
		ValueAddAcceptedIdeas valueAddAcceptedIdeas = new ValueAddAcceptedIdeas();
		valueAddAcceptedIdeas = valueAddManagementService.getValueAddByAcceptedIdeas(programId);
		return valueAddAcceptedIdeas;
	}
	
	@RequestMapping(value="/getValueCreationByProgramId/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public ValueCreation getValueCreationByProgramId(@PathVariable("programId") Integer programId) {
		logger.info("Requested to get the valueAdd information for Program or project");
		ValueCreation valueCreation = new ValueCreation();
		valueCreation = valueAddManagementService.getValueCreationByProgramId(programId);
		return valueCreation;
	}
	
	@RequestMapping(value="/getQuartValueByProjectId/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public ValueCreationQuarterly getQuartValueByProjectId(@PathVariable("projectId") Integer projectId) {
		logger.info("Requested to get the valueAdd information for project");
		ValueCreationQuarterly valueCreation = new ValueCreationQuarterly();
		valueCreation = valueAddManagementService.getQuarterlyValueByProjectId(projectId);
		return valueCreation;
	}
	
	@RequestMapping(value="/getQuartValueByProgramId/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public ValueCreationQuarterly getQartValueCreationByProgramId1(@PathVariable("programId") Integer programId) {
		logger.info("Requested to get the valueAdd information for Program");
		ValueCreationQuarterly valueCreation = new ValueCreationQuarterly();
		valueCreation = valueAddManagementService.getQuarterlyValueByProgramId(programId);
		return valueCreation;
	}
	
	@RequestMapping(value="/getValueIndexByPortfolio/{portfolioId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, ValueIndex> getValueAddByIds(@PathVariable("portfolioId") Integer portfolioId) {
		logger.info("Requested to get getValueAddByIds for portfolio");
		Map<Integer, ValueIndex> valueIndex = new HashMap<Integer, ValueIndex>();
		valueIndex = valueAddManagementService.getValueAddByIds(portfolioId);
		return valueIndex;
	}
	
	@RequestMapping(value="/getEconomicValueAdd/{programId}", method = RequestMethod.GET)
	@ResponseBody
	public ValueAddAcceptedIdeas getEconomicValueAdd(@PathVariable("programId") Integer programId) {
		logger.info("Requested to get getValueAddByIds for programId");
		ValueAddAcceptedIdeas economicValueAdd = new ValueAddAcceptedIdeas();
		economicValueAdd = valueAddManagementService.getEconomicValueAdd(programId);
		return economicValueAdd;
	}

}
