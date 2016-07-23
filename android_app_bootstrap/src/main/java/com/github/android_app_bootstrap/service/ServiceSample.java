package com.github.android_app_bootstrap.service;

/**
 * Created by xdf on 7/14/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceSample extends Service {
    private static final String TAG = "ServiceSample";

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "Service sample onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "Service sample onCreate");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "Service sample onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.v(TAG, "Service sample onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "Service sample onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "Service sample onDestroy");
        super.onDestroy();
    }
}