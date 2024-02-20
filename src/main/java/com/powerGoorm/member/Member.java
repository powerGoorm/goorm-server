package com.powerGoorm.member;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Entity
public class Member {



    @Id
    @NotEmpty
    private String id;
    @NotEmpty
    @Column(name="name",nullable=false)
    private String name;
    @NotEmpty
    @Column(name="password",nullable=false)
    private String password;
    @NotEmpty
    @Column(name="git",nullable=false)
    private String git;
    @NotNull
    @Column(name="created_at")
    private String create_at;
    @Column(name="deleted_at")
    private String delete_at;
    @Column(name="introduction")
    private String introduction;






}

