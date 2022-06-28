package com.example.craftdemo.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.model.ImageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {
    private final Api mApi;
    private final AppDatabase mDatabase;

    public interface RepositoryCallback<T> {
        void onComplete(List<ImageResult> list);
    }

    private static final String TAG = ImageRepository.class.getSimpleName();

    public ImageRepository(AppDatabase db, Api api) {
        mApi = api;
        mDatabase = db;
    }

    public void makeRequest(String page, String limit, final RepositoryCallback callback) {
        mApi.getImages(page, limit).enqueue(new Callback<List<ImageResult>>() {
            @Override
            public void onResponse(Call<List<ImageResult>> call, Response<List<ImageResult>> response) {
                Log.d(TAG, "onResponse : "+ response);
                List<ImageResult> list = response.body();
                callback.onComplete(list);

                saveData(list);
            }

            @Override
            public void onFailure(Call<List<ImageResult>> call, Throwable t) {
                Log.e(TAG, "onFailure :" + t.getMessage());
            }
        });
    }

    private void saveData(List<ImageResult> list) {
        new SaveImagesTask(mDatabase).execute(list);
    }

    private static class SaveImagesTask extends AsyncTask<List<ImageResult>, Void, Void> {
        private final AppDatabase mDatabase;

        public SaveImagesTask(AppDatabase database) {
            this.mDatabase = database;
        }
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ImageResult>... lists) {
            mDatabase.imageDao().insertAll(lists[0]);
            return null;
        }
    }
}
