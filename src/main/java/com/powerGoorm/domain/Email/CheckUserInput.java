package com.powerGoorm.domain.Email;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckUserInput {
    @NotNull
    private String userinput;
}
