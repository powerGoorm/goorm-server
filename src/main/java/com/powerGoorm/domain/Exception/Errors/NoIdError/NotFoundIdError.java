package com.powerGoorm.domain.Exception.Errors.NoIdError;

import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class NotFoundIdError extends RuntimeException{

    private HttpStatus status;
    private NoId noid;
    public NotFoundIdError(String message,HttpStatus status,NoId noid){
        super(message);
        this.status=status;
        this.noid=noid;
    }
}
