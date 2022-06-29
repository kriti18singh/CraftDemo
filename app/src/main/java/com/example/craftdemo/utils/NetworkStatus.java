package com.example.craftdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkStatus {
    private static final String TAG = NetworkStatus.class.getSimpleName();
    boolean connected = false;
    private static NetworkStatus sInstance;

    public static NetworkStatus getInstance() {
        if(sInstance == null) {
            sInstance = new NetworkStatus();
        }
        return sInstance;
    }

    public boolean isOnline(ConnectivityManager manager) {
        try {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return connected;
    }
}
