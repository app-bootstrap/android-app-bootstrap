package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

public class LoginActivity extends Activity implements View.OnClickListener {

    private Button button;
    private EditText mobileNoEditText;
    private EditText codeEditText;
    private Utils utils = new Utils();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
    }

    public void initView() {
        button = (Button) findViewById(R.id.login_button);
        mobileNoEditText = (EditText) findViewById(R.id.mobileNoEditText);
        mobileNoEditText.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        codeEditText = (EditText) findViewById(R.id.codeEditText);
        codeEditText.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        button.setOnClickListener(this);
        utils.isWifiConnected(this);
        utils.collect2ExecFile(true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                Intent intent = new Intent(this, TabBarActivity.class);
                startActivity(intent);
                break;

        }
    }
}
