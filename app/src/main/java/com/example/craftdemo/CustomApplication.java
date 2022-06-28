package com.example.craftdemo;

import android.app.Application;

public class CustomApplication extends Application {
    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot(this);
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
