package com.example.craftdemo;

import com.example.craftdemo.network.ImageRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    ImageRepository mRepo;

    public MainActivityViewModelFactory(ImageRepository param) {
        mRepo = param;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mRepo);
    }
}
