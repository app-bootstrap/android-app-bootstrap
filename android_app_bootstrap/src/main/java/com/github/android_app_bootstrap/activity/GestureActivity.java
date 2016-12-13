package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.gesture.MyGestureListener;

public class GestureActivity extends Activity {
    private MyGestureListener gestureListener;
    private GestureDetector gestureDetector;
    private TextView dispGestureLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_activity);
        dispGestureLabel = (TextView) findViewById(R.id.text);
        gestureListener = new MyGestureListener(dispGestureLabel);
        gestureDetector = new GestureDetector(this, gestureListener);
    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onDestroy() {
        super.onDestroy();
    }

}



