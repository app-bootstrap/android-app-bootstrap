package com.github.android_app_bootstrap.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {
                onMpaaSInitialized(MainApplication.this);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QuinoxlessFramework.init();
    }

    private void onMpaaSInitialized(Context context) {
        Log.i("application", "mpaas ready");
    }
}
