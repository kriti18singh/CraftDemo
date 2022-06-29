package com.example.craftdemo.ui;

import android.net.ConnectivityManager;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.model.NetworkResponse;
import com.example.craftdemo.model.ResponseStatus;
import com.example.craftdemo.network.Api;
import com.example.craftdemo.network.ImageRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements ImageRepository.RepositoryCallback {
    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private MutableLiveData<NetworkResponse> responseMutableLiveData;

    private final ImageRepository mRepo;

    public MainActivityViewModel(AppDatabase db, Api api, ConnectivityManager manager) {
        this.mRepo = new ImageRepository(db, api, manager);
    }

    public LiveData<NetworkResponse> getImages() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
            loadImagesFromRepo();
        }
        return responseMutableLiveData;
    }

    private void loadImagesFromRepo() {
        //TODO: remove hard coded values
        mRepo.loadImages("2", "100", this);
    }

    @Override
    public void onComplete(NetworkResponse response) {
        if(response.responseStatus == ResponseStatus.SUCCESS) {
            responseMutableLiveData.postValue(
                    NetworkResponse.success(response.data)
            );
        } else if(response.responseStatus == ResponseStatus.ERROR) {
            responseMutableLiveData.postValue(
                    NetworkResponse.error(new Throwable(response.error))
            );
        }
    }
}
