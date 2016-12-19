package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

public class ToastActivity extends Activity implements View.OnClickListener {

    private final Utils utils = new Utils();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_activity);
        initView();
    }

    public void initView() {
        Button button = (Button) findViewById(R.id.alert_button);
        button.setOnClickListener(this);
    }

    void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, -50, 100);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alert_button:
                showToast();
                break;

        }
    }
}
