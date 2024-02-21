package com.powerGoorm.domain.Exception.Errors.NotCollectFormerror;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotCollectFormError extends RuntimeException{
    private HttpStatus status;
   private BingdingErrorsList bingdingErrorsList;
    public NotCollectFormError(String message,HttpStatus status,BingdingErrorsList bingdingErrorsList){
        super(message);
        this.status=status;
        this.bingdingErrorsList=bingdingErrorsList;

    }

}
