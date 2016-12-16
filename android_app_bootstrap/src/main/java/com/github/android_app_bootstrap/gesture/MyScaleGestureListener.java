package com.github.android_app_bootstrap.gesture;

import android.view.ScaleGestureDetector;
import android.widget.TextView;

/**
 * Created by liuyang on 16/12/15.
 */
public class MyScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
    private static final String TAG = "testScaleListener";

    //Pinch gesture name
    private static final String GESTURE_PINCH = "pinch";
    //Rotate gesture name
    private static final String GESTURE_ROTATE = "rotate";

    //The largest value for us to judge whether a gesture is a pinch action
    private static final float PINCH_THRESHOLD = 10f;
    //The largest value for us to judge whether a gesture is a rotate action
    private static final float ROTATE_THRESHOLD = 0.15f;

    //gesture name for display
    private String gestureName;
    //Current gesture's focus point: x axis
    private float focus_x;
    //Current gesture's focus point: y axis
    private float focus_y;
    //Change amount of rotate
    private double rotate;
    //Current change of pinch
    private double pinch;
    //Widget for display information
    private TextView gestureLabel;


    public MyScaleGestureListener(TextView textView) {
        gestureLabel = textView;
    }

    public boolean onScale(ScaleGestureDetector detector) {
        if (!judgeRotate(detector)) {
            judgePinch(detector);
        }
        return false;
    }

    //// TODO: 16/12/15 Still get better method to judge the rotate gesture
    private boolean judgeRotate(ScaleGestureDetector detector) {
        boolean res = false;

        double preDistX = detector.getPreviousSpanX();
        double preDistY = detector.getPreviousSpanY();
        double curDistX = detector.getCurrentSpanX();
        double curDistY = detector.getCurrentSpanY();

        double atanPre = Math.atan2(preDistY,preDistX);
        double atanCur = Math.atan2(curDistY,curDistX);
        rotate = atanCur - atanPre;

        if (Math.abs(rotate) > ROTATE_THRESHOLD) {
            res = true;
            gestureName = GESTURE_ROTATE;
            focus_x = detector.getFocusX();
            focus_y = detector.getFocusY();
            gestureLabel.setText(gestureName + "\n x:" + focus_x + " y:" + focus_y + "\ndeltaAngel:" + rotate);
        }
        return res;
    }

    private boolean judgePinch(ScaleGestureDetector detector) {
        boolean res = false;
        float curDist = detector.getCurrentSpan();
        float preDist = detector.getPreviousSpan();
        pinch = preDist - curDist;
        if (( curDist< preDist) && (pinch > PINCH_THRESHOLD)) {
            res = true;
            gestureName = GESTURE_PINCH;
            focus_x = detector.getFocusX();
            focus_y = detector.getFocusY();

            gestureLabel.setText(gestureName + "\n x:" + focus_x + " y:" + focus_y + "\npinch:" + pinch);
        }
        return res;
    }
}
