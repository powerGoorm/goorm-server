package com.powerGoorm.Web.Controller;


import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.CodeMisMatch;
import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.MailCodeMisMatchError;
import com.powerGoorm.domain.Exception.Errors.NoIdError.NotFoundIdError;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.BingdingErrorsList;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.NotCollectFormError;
import com.powerGoorm.domain.Exception.Errors.SessionEnderror.Redirecturl;
import com.powerGoorm.domain.Exception.Errors.SessionEnderror.SessionEndError;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.NotPassword;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.UnCorrectPasswordError;
import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Exception.Errors.errorform.ErrorJson;
import com.powerGoorm.domain.Exception.Errors.errorform.MakeErrorResps;
import com.powerGoorm.domain.Status;
import com.powerGoorm.domain.Exception.Errors.idexisterror.IdExistError;
import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(IdExistError.class)
    public ResponseEntity<ErrorJson<NoId>> IdExist(IdExistError e){


        MakeErrorResps<NoId> makeErrorResps=new MakeErrorResps<>();

        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus().value()),e.getMessage()),
                new Data<NoId>(e.getNoid()));
    }

    @ExceptionHandler(NotFoundIdError.class)
    public ResponseEntity<ErrorJson<NoId>> NotFoundId(NotFoundIdError e){

        MakeErrorResps<NoId> makeErrorResps=new MakeErrorResps<>();

        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus()),e.getMessage()),
                new Data<NoId>(e.getNoid()));

    }
    @ExceptionHandler(UnCorrectPasswordError.class)
    public ResponseEntity<ErrorJson<NotPassword>> NotFoundId(UnCorrectPasswordError e){


        MakeErrorResps<NotPassword> makeErrorResps=new MakeErrorResps<>();

        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus()),e.getMessage()),
                new Data<NotPassword>(e.getPassword()));

    }

    @ExceptionHandler(SessionEndError.class)
    public ResponseEntity<ErrorJson<Redirecturl>> NoSessionErrors(SessionEndError e){

        MakeErrorResps<Redirecturl> makeErrorResps=new MakeErrorResps<>();

        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus()),e.getMessage()),
                new Data<Redirecturl>(e.getRedirecturl()));
    }

    @ExceptionHandler(NotCollectFormError.class)
    public ResponseEntity<ErrorJson<BingdingErrorsList>> FormError(NotCollectFormError e){

        MakeErrorResps<BingdingErrorsList> makeErrorResps=new MakeErrorResps<>();
        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus()),e.getMessage()),
                new Data<BingdingErrorsList>(e.getBingdingErrorsList()));




    }

    @ExceptionHandler(MailCodeMisMatchError.class)
    public ResponseEntity<ErrorJson<CodeMisMatch>> MailCodeMismatch(MailCodeMisMatchError e){

        MakeErrorResps<CodeMisMatch> makeErrorResps=new MakeErrorResps<>();
        return makeErrorResps.MakeErrorResp(new Status(String.valueOf(e.getStatus()),e.getMessage()),
                new Data<CodeMisMatch>(e.getCodeMisMatch()));

    }
}
