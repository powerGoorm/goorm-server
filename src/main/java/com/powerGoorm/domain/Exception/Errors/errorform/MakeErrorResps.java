package com.powerGoorm.domain.Exception.Errors.errorform;

import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MakeErrorResps<T> implements MakeErrorResponse<T> {

    @Override
    public ResponseEntity<ErrorJson<T>> MakeErrorResp(Status status, Data<T> data) {

        ErrorJson<T> errorJson =new ErrorJson<>();
        errorJson.setStatus(status);
        errorJson.setData(data);
        return new ResponseEntity<>(errorJson,HttpStatus.BAD_REQUEST);
    }
}
