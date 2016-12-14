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


    int threshold = 20;
    float angleThreshold = 0.3f;

    //// TODO: 2016/12/14 Generate Pinch Gesture
    float pinchThreshold = 5f;

    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        int pointCount = e2.getActionIndex();
        if (pointCount > 1) {
            if (judgeRotate(e1, e2, distanceX, distanceY)) {

            } else if (judgePinch( e1,  e2,
             distanceX,  distanceY)) {

            }
        } else {
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY );

            if (distanceX > threshold || distanceY > threshold || distance > threshold) {
                gestureName = GESTURE_DRAG;
                axis_x = e2.getX();
                axis_y = e2.getY();
                gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
            }
        }
        return false;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        return false;
    }


    private boolean judgeRotate(MotionEvent e1, MotionEvent e2,
                             float distanceX, float distanceY) {
        boolean res = false;

        double angel = Math.atan((double) distanceY / distanceX);
        if (Math.abs(angel) > angleThreshold) {
            res = true;
            gestureName = GESTURE_ROTATE;
            axis_x = e2.getX();
            axis_y = e2.getY();
            gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y + " rotate :" + angel);
        }

        return res;
    }

    double lastDist = -1;
    private boolean judgePinch(MotionEvent e1, MotionEvent e2,
                               float distanceX, float distanceY) {
        boolean res = false;

        double dist = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (dist != lastDist) {
//            res = true;
            gestureName = GESTURE_PINCH;
            axis_x = e2.getX();
            axis_y = e2.getY();
            gestureLabel.setText(gestureName + " x:" + axis_x + " y:" + axis_y);
        }

        return res;
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
