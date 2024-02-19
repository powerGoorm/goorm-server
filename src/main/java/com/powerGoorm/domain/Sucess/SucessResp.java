package com.powerGoorm.domain.Sucess;

import com.powerGoorm.domain.Status;
import lombok.Data;

@Data
public class SucessResp<T> {

    private Status status;

    private com.powerGoorm.domain.Data<T> data;


}
