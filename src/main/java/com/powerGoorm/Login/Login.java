package com.powerGoorm.Login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Login {


    @NotNull
    private String id;
    @NotNull
    private String password;
}
