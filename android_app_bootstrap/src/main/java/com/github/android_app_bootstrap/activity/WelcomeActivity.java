package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.github.android_app_bootstrap.R;

public class WelcomeActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        initView();
    }

    void goLogin() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goLogin();
            }
        }, 3500);
    }
}
