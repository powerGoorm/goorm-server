package com.powerGoorm.domain.Exception.Errors.SessionEnderror;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SessionEndError extends RuntimeException{
    private HttpStatus status;
    private Redirecturl redirecturl;
    public SessionEndError(String message,HttpStatus status,Redirecturl redirecturl){
        super(message);
        this.status=status;
        this.redirecturl=redirecturl;
    }


}
