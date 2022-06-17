package com.example.craftdemo.network;

import android.util.Log;

import com.example.craftdemo.model.ImageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageRepository {

    private final Api mApi;

    public interface RepositoryCallback<T> {
        void onComplete(List<ImageResult> list);
    }

    private static final String TAG = ImageRepository.class.getSimpleName();

    private ImageRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(Api.class);
    }

    private static ImageRepository sInstance;

    public static ImageRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ImageRepository();
        }
        return sInstance;
    }

    public void makeRequest(String page, String limit, final RepositoryCallback callback) {
        mApi.getImages(page, limit).enqueue(new Callback<List<ImageResult>>() {
            @Override
            public void onResponse(Call<List<ImageResult>> call, Response<List<ImageResult>> response) {
                Log.d(TAG, "onResponse : "+ response);
                List<ImageResult> list = response.body();
                callback.onComplete(list);
            }

            @Override
            public void onFailure(Call<List<ImageResult>> call, Throwable t) {
                Log.e(TAG, "onFailure :" + t.getMessage());
            }
        });
    }
}
