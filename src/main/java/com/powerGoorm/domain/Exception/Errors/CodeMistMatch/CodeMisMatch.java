package com.powerGoorm.domain.Exception.Errors.CodeMistMatch;

import lombok.Data;

@Data
public class CodeMisMatch {

	private String userentercode;

	public CodeMisMatch(String code) {
		this.userentercode = code;
	}
}
