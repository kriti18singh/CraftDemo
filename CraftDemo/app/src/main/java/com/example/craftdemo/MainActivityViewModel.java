package com.example.craftdemo;

import com.example.craftdemo.model.ImageResult;
import com.example.craftdemo.network.ImageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements ImageRepository.RepositoryCallback {
    private MutableLiveData<List<ImageResult>> mList;

    private ImageRepository mRepo;

    public MainActivityViewModel(ImageRepository mRepo) {
        this.mRepo = mRepo;
    }

    public LiveData<List<ImageResult>> getImages() {
        if (mList == null) {
            mList = new MutableLiveData<>();
            loadImages();
        }
        return mList;
    }

    private void loadImages() {
        mRepo.makeRequest("2", "20", this);
    }

    @Override
    public void onComplete(List list) {
        mList.setValue(list);
    }
}

