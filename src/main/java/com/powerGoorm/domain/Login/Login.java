package com.powerGoorm.domain.Login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Login {
    private String username;

    private String password;



}
