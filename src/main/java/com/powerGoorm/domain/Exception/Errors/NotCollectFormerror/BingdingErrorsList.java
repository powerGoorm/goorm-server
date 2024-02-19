package com.powerGoorm.domain.Exception.Errors.NotCollectFormerror;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
@Data
public class BingdingErrorsList {


    private List<String> errormessages=new ArrayList<>();
    public BingdingErrorsList(List<FieldError> errorlist){

        for(FieldError err :errorlist){
            this.errormessages.add(err.getField()+" "+err.getDefaultMessage());
        }
    }
}
