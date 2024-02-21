package com.powerGoorm.domain.Sucess;

import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface MakeSucessResponse<T> {


     public ResponseEntity<SucessResp<T>> MakeSucessResp(Status status, Data<T> data);


}
