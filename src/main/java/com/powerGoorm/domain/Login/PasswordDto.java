package com.powerGoorm.domain.Login;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PasswordDto {


    @NotEmpty
    private String password;
}
