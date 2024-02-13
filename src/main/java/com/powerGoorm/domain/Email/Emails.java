package com.powerGoorm.domain.Email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Emails {

    @NotEmpty
    @Email
    private String email;


}
