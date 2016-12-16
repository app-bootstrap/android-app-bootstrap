package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.gesture.MyGestureListener;
import com.github.android_app_bootstrap.gesture.MyScaleGestureListener;

public class GestureActivity extends Activity {
    private static final String TAG = "testGestActivity";
    private MyGestureListener gestureListener;
    private MyScaleGestureListener scaleGestureListener;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private TextView dispGestureLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_activity);
        dispGestureLabel = (TextView) findViewById(R.id.info);
        gestureListener = new MyGestureListener(dispGestureLabel);
        scaleGestureListener = new MyScaleGestureListener(dispGestureLabel);
        gestureDetector = new GestureDetector(this, gestureListener);
        scaleGestureDetector = new ScaleGestureDetector(this,scaleGestureListener);
    }


    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
