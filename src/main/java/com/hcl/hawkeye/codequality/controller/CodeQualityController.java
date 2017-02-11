package com.hcl.hawkeye.codequality.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.codequality.service.CodeQualityService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CodeQualityController {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeQualityController.class);

	@Autowired
	CodeQualityService codeQualityServiceImpl;

	@RequestMapping(value = "/codequality/ragstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> getCodeQualityRAGStaus() {

		int ragStatus = codeQualityServiceImpl.getCodeQualityRAGStatus();

		logger.debug("RAG Status: {}", ragStatus);

		return new ResponseEntity<Integer>(ragStatus, HttpStatus.OK);
	}

}
