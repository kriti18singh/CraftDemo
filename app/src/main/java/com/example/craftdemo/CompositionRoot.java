package com.example.craftdemo;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.network.Api;
import com.example.craftdemo.utils.Constants;
import com.squareup.picasso.Picasso;


import androidx.room.Room;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit mRetrofit;
    private AppDatabase mDatabase;
    private ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private Picasso mPicasso;

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

    public ConnectivityManager getConnectivityManager() {
        if(mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return mConnectivityManager;
    }

    public Picasso getPicasso() {
        if(mPicasso == null) {
            Picasso.Builder builder = new Picasso.Builder(mContext);
            //builder.downloader(okHttp3Downloader);
            Picasso picasso = builder.build();
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
            Picasso.setSingletonInstance(picasso);
            mPicasso = picasso;
        }
        return mPicasso;
    }
}
