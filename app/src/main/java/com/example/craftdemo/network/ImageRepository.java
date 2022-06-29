package com.example.craftdemo.network;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.model.ImageResult;
import com.example.craftdemo.model.NetworkResponse;
import com.example.craftdemo.model.ResponseStatus;
import com.example.craftdemo.utils.NetworkStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {
    private final Api mApi;
    private final AppDatabase mDatabase;
    private final ConnectivityManager mConnectivityManager;

    public interface RepositoryCallback<T> {
        void onComplete(NetworkResponse response);
    }

    private static final String TAG = ImageRepository.class.getSimpleName();

    public ImageRepository(AppDatabase db, Api api, ConnectivityManager manager) {
        mApi = api;
        mDatabase = db;
        mConnectivityManager = manager;
    }

    private void makeRequest(String page, String limit, final RepositoryCallback callback) {
        mApi.getImages(page, limit).enqueue(new Callback<List<ImageResult>>() {
            @Override
            public void onResponse(Call<List<ImageResult>> call, Response<List<ImageResult>> response) {
                Log.d(TAG, "onResponse : "+ response);
                List<ImageResult> list = response.body();
                NetworkResponse networkResponse = new NetworkResponse(
                        ResponseStatus.SUCCESS,
                        list,
                        null
                );
                callback.onComplete(networkResponse);

                saveData(list);
            }

            @Override
            public void onFailure(Call<List<ImageResult>> call, Throwable t) {
                Log.e(TAG, "onFailure :" + t.getMessage());
                NetworkResponse networkResponse = new NetworkResponse(
                        ResponseStatus.ERROR,
                        null,
                        t
                );
                callback.onComplete(networkResponse);
            }
        });
    }

    public void loadImages(String page, String limit, final RepositoryCallback callback) {
        if(!NetworkStatus.getInstance().isOnline(mConnectivityManager)) {
            //load from db
            new LoadImagesTask(mDatabase, callback).execute();
        }
        else makeRequest(page, limit, callback);
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

    private static class LoadImagesTask extends AsyncTask<Void, Void, List<ImageResult>> {
        private final AppDatabase mDatabase;
        private final RepositoryCallback mCallback;

        public LoadImagesTask(AppDatabase database, RepositoryCallback callback) {
            mDatabase = database;
            mCallback = callback;
        }

        @Override
        protected List<ImageResult> doInBackground(Void... voids) {
            return mDatabase.imageDao().getAll();
        }

        @Override
        protected void onPostExecute(List<ImageResult> listData) {
            super.onPostExecute(listData);
            //mCallback.onComplete(listData);
        }
    }
}
