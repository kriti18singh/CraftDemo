package com.example.craftdemo.ui;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.network.Api;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private Api mApi;
    private AppDatabase mDatabase;

    public MainActivityViewModelFactory(AppDatabase db, Api api) {
        mApi = api;
        mDatabase = db;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mDatabase, mApi);
    }
}
