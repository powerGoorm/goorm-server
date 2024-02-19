package com.powerGoorm.domain.Exception.Errors.SessionEnderror;

import lombok.Data;

@Data
public class Redirecturl {

    private String redirecturl;

    public Redirecturl(String redirecturl){
        this.redirecturl=redirecturl;
    }
}
