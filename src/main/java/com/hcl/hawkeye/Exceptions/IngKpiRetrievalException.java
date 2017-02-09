package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed ti retrieve the ing kpi details")
public class IngKpiRetrievalException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IngKpiRetrievalException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
