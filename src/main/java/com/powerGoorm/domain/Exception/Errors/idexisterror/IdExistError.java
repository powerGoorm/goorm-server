package com.powerGoorm.domain.Exception.Errors.idexisterror;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class IdExistError extends RuntimeException{
    private HttpStatus status;
    private NoId noid;
    public IdExistError(String message,HttpStatus status,NoId noid){
        super(message);
        this.status=status;
        this.noid=noid;
    }


}
