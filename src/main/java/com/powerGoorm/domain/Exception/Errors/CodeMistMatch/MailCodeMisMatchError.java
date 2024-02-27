package com.powerGoorm.domain.Exception.Errors.CodeMistMatch;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MailCodeMisMatchError extends RuntimeException {
	private HttpStatus status;
	private CodeMisMatch codeMisMatch;

	public MailCodeMisMatchError(String message, HttpStatus status, CodeMisMatch codeMisMatch) {
		super(message);
		this.status = status;
		this.codeMisMatch = codeMisMatch;
	}

}
