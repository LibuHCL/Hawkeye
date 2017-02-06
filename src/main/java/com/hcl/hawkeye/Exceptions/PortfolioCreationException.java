package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Failed to create the Portfolio")
public class PortfolioCreationException extends RuntimeException {
	
	public PortfolioCreationException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
