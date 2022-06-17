package com.example.craftdemo.network;

import com.example.craftdemo.model.ImageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://picsum.photos/";

    @GET("v2/list")
    Call<List<ImageResult>> getImages(@Query("page") String page, @Query("limit") String limit);
}
