package com.powerGoorm.domain.Exception.Errors.CodeMistMatch;

import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
@Data
public class MailCodeMisMatchError extends RuntimeException{
    private HttpStatus status;
    private CodeMisMatch codeMisMatch;
    public MailCodeMisMatchError(String message,HttpStatus status,CodeMisMatch codeMisMatch){
        super(message);
        this.status=status;
        this.codeMisMatch=codeMisMatch;
    }

}
