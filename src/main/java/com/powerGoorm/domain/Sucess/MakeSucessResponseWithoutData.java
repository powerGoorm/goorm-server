package com.powerGoorm.domain.Sucess;

import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MakeSucessResponseWithoutData<T> implements MakeSucessResponse<T>{
    @Override
    public ResponseEntity<SucessResp<T>> MakeSucessResp(Status status, Data<T> data) {
        {
            SucessResp<T> sucessResp=new SucessResp<>();
            sucessResp.setStatus(status);

            return new ResponseEntity<>(sucessResp, HttpStatus.OK);
        }
    }
}
