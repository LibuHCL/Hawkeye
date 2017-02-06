package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Failed to capture the escalation Details")
public class EscalationCaptureException extends RuntimeException {
	
	public EscalationCaptureException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
