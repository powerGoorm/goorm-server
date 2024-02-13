package com.powerGoorm.domain.Login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Login {


    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}
