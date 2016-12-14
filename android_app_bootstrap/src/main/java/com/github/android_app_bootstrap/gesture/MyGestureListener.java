package com.github.android_app_bootstrap.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by liuyang on 2016/12/13.
 */

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
    private static final String TAG = "test_Gesture";

    private static final String GESTURE_SINGLE_TAP = "singleTap";
    private static final String GESTURE_DOUBLE_TAP = "doubleTap";
    private static final String GESTURE_ROTATE = "rotate";
    private static final String GESTURE_SWIPE = "swipe";
    private static final String GESTURE_LONG_PRESS = "longPress";
    private static final String GESTURE_PINCH = "pinch";
    private static final String GESTURE_DRAG = "drag";

    private TextView gestureLabel;

    private String gestureName;
    private float scale;
    private float rotate;
    private float axis_x;
    private float axis_y;

    public MyGestureListener(TextView textView) {
        gestureLabel = textView;
    }


    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }



    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        return false;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        return false;
    }



    //---------------------------  Determined Gesture Callback--------------------

    //单击事件
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureName = GESTURE_SINGLE_TAP;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        return super.onSingleTapConfirmed(e);
    }

    //双击事件
    public boolean onDoubleTap(MotionEvent e) {
        gestureName = GESTURE_DOUBLE_TAP;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        return super.onDoubleTap(e);
    }

    //长按事件
    public void onLongPress(MotionEvent e) {
        gestureName = GESTURE_LONG_PRESS;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
    }


}
