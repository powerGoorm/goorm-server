package com.powerGoorm.domain.Email;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckUserInput {
	@NotNull(message = "인증코드를 입력해주세요")
	private String userInput;
}
