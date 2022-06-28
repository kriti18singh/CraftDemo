package com.example.craftdemo;

import android.content.Context;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.network.Api;
import com.example.craftdemo.utils.Constants;

import androidx.room.Room;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit mRetrofit;
    private AppDatabase mDatabase;
    private final Context mContext;

    public CompositionRoot(CustomApplication customApplication) {
        mContext = customApplication;
    }

    public Api getImagesApi() {
        return getRetrofit().create(Api.class);
    }

    public AppDatabase getDatabase() {
        return getAppDatabase();
    }

    private AppDatabase getAppDatabase() {
        if(mDatabase == null) {
            mDatabase = Room.databaseBuilder(mContext,
                    AppDatabase.class, "image_db").build();
        }
        return mDatabase;
    }

    private Retrofit getRetrofit() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
