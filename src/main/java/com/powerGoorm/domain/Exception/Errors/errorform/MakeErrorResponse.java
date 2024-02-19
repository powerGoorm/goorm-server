package com.powerGoorm.domain.Exception.Errors.errorform;

import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Status;
import org.springframework.http.ResponseEntity;

public interface MakeErrorResponse<T> {


     ResponseEntity<ErrorJson<T>> MakeErrorResp(Status status, Data<T> data);
}
