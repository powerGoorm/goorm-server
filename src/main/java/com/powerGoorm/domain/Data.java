package com.powerGoorm.domain;
@lombok.Data
public class Data<T> {

    private T value;


    public Data(T rejectvalue) {
        this.value = rejectvalue;
    }
}
