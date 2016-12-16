package com.github.android_app_bootstrap.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by liuyang on 2016/12/13.
 */

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = "test_GestureScroll";

    private static final String GESTURE_SINGLE_TAP = "singleTap";
    private static final String GESTURE_DOUBLE_TAP = "doubleTap";
    private static final String GESTURE_LONG_PRESS = "longPress";
    private static final String GESTURE_DRAG = "drag";

    private TextView gestureLabel;

    private String gestureName;
    private float axis_x;
    private float axis_y;

    private static final float DRAG_THRESHOLD = 10f;
    public MyGestureListener(TextView textView) {
        gestureLabel = textView;
    }


    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    
     //single tap gesture detect
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureName = GESTURE_SINGLE_TAP;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        return super.onSingleTapConfirmed(e);
    }


    //double tap gesture detect
    public boolean onDoubleTap(MotionEvent e) {
        gestureName = GESTURE_DOUBLE_TAP;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        return super.onDoubleTap(e);
    }


    //long press gesture detect
    public void onLongPress(MotionEvent e) {
        gestureName = GESTURE_LONG_PRESS;
        axis_x = e.getX();
        axis_y = e.getY();
        gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
    }


    //Gesture drag detect
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        double dist = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (dist > DRAG_THRESHOLD || distanceX > DRAG_THRESHOLD || distanceY > DRAG_THRESHOLD) {
            gestureName = GESTURE_DRAG;
            axis_x = e2.getX();
            axis_y = e2.getY();
            gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        }
        return false;
    }

}
