package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Failed to create Program")
public class ProgramCreationException extends RuntimeException {
	
	public ProgramCreationException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
