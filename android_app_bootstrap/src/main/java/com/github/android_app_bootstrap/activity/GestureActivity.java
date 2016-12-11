package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

public class GestureActivity extends Activity implements GestureDetector.OnGestureListener {

    private final Utils utils = new Utils();
    GestureDetector detector;

    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_activity);
        initView();
    }

    public void initView() {
        textView = (TextView) findViewById(R.id.info);
        detector = new GestureDetector(this, this);

    }


    // singleTap/doubleTap/rotate/swipe/longPress/pinch/drag

    @Override
    public boolean onDown(MotionEvent e) {
        System.out.print("1231221");
        textView.setText("12312");
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.print("1231221");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //return detector.onTouchEvent(e);
//        int eventType = e.getAction();
//        switch(eventType) {
//            case MotionEvent.ACTION_DOWN:
//                //single tap
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//
//        }
        return detector.onTouchEvent(e);
//        return false;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }
}
