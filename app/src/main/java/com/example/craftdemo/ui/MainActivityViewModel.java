package com.example.craftdemo.ui;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.model.ImageResult;
import com.example.craftdemo.network.Api;
import com.example.craftdemo.network.ImageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements ImageRepository.RepositoryCallback {
    public MutableLiveData<List<ImageResult>> getList() {
        return mList;
    }

    private MutableLiveData<List<ImageResult>> mList;

    private final ImageRepository mRepo;

    public MainActivityViewModel(AppDatabase db, Api api) {
        this.mRepo = new ImageRepository(db, api);
    }

    public LiveData<List<ImageResult>> getImages() {
        if (mList == null) {
            mList = new MutableLiveData<>();
            loadImages();
        }
        return mList;
    }

    private void loadImages() {
        mRepo.makeRequest("2", "100", this);
    }

    @Override
    public void onComplete(List list) {
        mList.setValue(list);
    }
}

