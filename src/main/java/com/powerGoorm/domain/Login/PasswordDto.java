package com.powerGoorm.domain.Login;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PasswordDto {


    @NotEmpty(message = "업데이트할 비밀번호를 넣어주세요")
    private String password;
}
