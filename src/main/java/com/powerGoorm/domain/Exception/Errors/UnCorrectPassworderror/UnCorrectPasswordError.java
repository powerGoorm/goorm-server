package com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UnCorrectPasswordError extends RuntimeException {

    private HttpStatus status;
    private NotPassword password;
    public UnCorrectPasswordError(String message,HttpStatus status,NotPassword password){
        super(message);
        this.status=status;
        this.password=password;
    }
}
