package com.powerGoorm.member;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data

public class Member {
    @NotEmpty
    private  String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private  String password;
    @NotEmpty
    private  String giturl;
    private  String intro;

}

