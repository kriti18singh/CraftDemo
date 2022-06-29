package com.example.craftdemo.ui;

import android.net.ConnectivityManager;

import com.example.craftdemo.database.AppDatabase;
import com.example.craftdemo.network.Api;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final Api mApi;
    private final AppDatabase mDatabase;
    private final ConnectivityManager mConnectivityManager;

    public MainActivityViewModelFactory(AppDatabase db, Api api, ConnectivityManager manager) {
        mApi = api;
        mDatabase = db;
        mConnectivityManager = manager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mDatabase, mApi, mConnectivityManager);
    }
}
