package com.powerGoorm.domain.Exception.Errors.errorform;

import com.powerGoorm.domain.Status;
import lombok.Data;


@Data
public class ErrorJson<T> {

    private Status status;
    private com.powerGoorm.domain.Data<T> data;



}
