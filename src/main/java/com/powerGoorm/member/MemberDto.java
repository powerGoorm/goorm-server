package com.powerGoorm.member;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberDto {


    @NotEmpty
    private String git;
    @NotNull
    private String introduction;

}
