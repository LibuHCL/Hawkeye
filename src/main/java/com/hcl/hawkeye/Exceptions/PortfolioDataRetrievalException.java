package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed to retrieve the portfolio information from db")
public class PortfolioDataRetrievalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PortfolioDataRetrievalException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
