package com.example.craftdemo.model;

import java.util.List;

public class NetworkResponse {
    public ResponseStatus responseStatus;
    public Throwable error;
    public List<ImageResult> data;


    public NetworkResponse(ResponseStatus responseStatus, List<ImageResult> data ,Throwable error){
        this.responseStatus = responseStatus ;
        this.data = data ;
        this.error = error ;
    }

    public static NetworkResponse loading(){
        return new NetworkResponse(ResponseStatus.LOADING,null,null);
    }

    public static NetworkResponse success(List<ImageResult> data){
        return new NetworkResponse(ResponseStatus.SUCCESS, data,null);
    }

    public static NetworkResponse error(Throwable error){
        return new NetworkResponse(ResponseStatus.ERROR,null,error);
    }
}
