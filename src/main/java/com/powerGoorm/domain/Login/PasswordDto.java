package com.powerGoorm.domain.Login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PasswordDto {


    @NotNull
    private String password;
}
