package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

public class AlertActivity extends Activity implements View.OnClickListener {

    private final Utils utils = new Utils();
    private TextView show_keyevent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_activity);
        initView();
    }

    public void initView() {
        Button button = (Button) findViewById(R.id.alert_button);
        button.setOnClickListener(this);
        show_keyevent = (TextView) findViewById(R.id.show_keyevent);
        showSimpleDialog();
    }

    private void showSimpleDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("haha~")
                .setPositiveButton("yes", null)
                .setNegativeButton("no", null)
                .show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alert_button:
                showSimpleDialog();
                break;

        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        final int keyCode = event.getKeyCode();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show_keyevent.setText("KeyCode: " + keyCode);
            }
        });
        return super.dispatchKeyEvent(event);
    }
}
