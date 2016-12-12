package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;
import com.github.android_app_bootstrap.gesture.MoveGestureDetector;
import com.github.android_app_bootstrap.gesture.RotateGestureDetector;
import com.github.android_app_bootstrap.gesture.ShoveGestureDetector;

public class GestureActivity extends Activity implements View.OnTouchListener {

    private Matrix mMatrix = new Matrix();
    private float mScaleFactor = .4f;
    private float mRotationDegrees = 0.f;
    private float mFocusX = 0.f;
    private float mFocusY = 0.f;
    private int mAlpha = 255;
    private int mImageHeight, mImageWidth;

    private ScaleGestureDetector mScaleDetector;
    private RotateGestureDetector mRotateDetector;
    private MoveGestureDetector mMoveDetector;
    private ShoveGestureDetector mShoveDetector;
    private RelativeLayout mRootLayer;
    private TextView text ;

    public void onDestroy() {
        super.onDestroy();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_activity);
        mRootLayer = (RelativeLayout)findViewById(R.id.gesture_root);
        mRootLayer.setOnTouchListener(this);
        text = (TextView)findViewById(R.id.text);
        text.setClickable(false);

        Display display = getWindowManager().getDefaultDisplay();
        mFocusX = display.getWidth()/2f;
        mFocusY = display.getHeight()/2f;

        Drawable d 		= this.getResources().getDrawable(R.drawable.earth);
        mImageHeight 	= d.getIntrinsicHeight();
        mImageWidth 	= d.getIntrinsicWidth();

        float scaledImageCenterX = (mImageWidth*mScaleFactor)/2;
        float scaledImageCenterY = (mImageHeight*mScaleFactor)/2;

        mMatrix.postScale(mScaleFactor, mScaleFactor);
        mMatrix.postTranslate(mFocusX - scaledImageCenterX, mFocusY - scaledImageCenterY);

        mScaleDetector 	= new ScaleGestureDetector(getApplicationContext(), new ScaleListener());
        mRotateDetector = new RotateGestureDetector(getApplicationContext(), new RotateListener());
        mMoveDetector 	= new MoveGestureDetector(getApplicationContext(), new MoveListener());
        mShoveDetector 	= new ShoveGestureDetector(getApplicationContext(), new ShoveListener());
    }

    private long lastClickedTime = -1;
    private float last_x = -1;
    private float last_y = -1;
    private boolean handleSimpleDoubleEvent(MotionEvent event) {
        boolean res = false;
        float x = -1;
        float y = -1;
        long currentTime = System.currentTimeMillis();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (currentTime - lastClickedTime < 500) {
                x = last_x + (event.getX() - last_x) / 2;
                y = last_x + (event.getY() - last_y) / 2;
                res = true;
                last_x = -1;
                last_y = -1;
            }
              lastClickedTime = currentTime;
            text.setText("x :" + x + " y :" + y);
        }
        return res;
    }

    @SuppressWarnings("deprecation")
    public boolean onTouch(View v, MotionEvent event) {
        if (handleSimpleDoubleEvent(event)) {
            return false;
        }
        mScaleDetector.onTouchEvent(event);
        mRotateDetector.onTouchEvent(event);
        mMoveDetector.onTouchEvent(event);
        mShoveDetector.onTouchEvent(event);

        float scaledImageCenterX = (mImageWidth*mScaleFactor)/2;
        float scaledImageCenterY = (mImageHeight*mScaleFactor)/2;

        mMatrix.reset();
        mMatrix.postScale(mScaleFactor, mScaleFactor);
        mMatrix.postRotate(mRotationDegrees,  scaledImageCenterX, scaledImageCenterY);
        float final_x = mFocusX - scaledImageCenterX;
        float final_y = mFocusY - scaledImageCenterY;
        mMatrix.postTranslate(final_x, final_y);

        setText(mFocusX - scaledImageCenterX,mFocusY - scaledImageCenterY);

        return true;
    }

    private void setText(float translateX,float translateY) {
        text.setText("x :" + translateX + " \n y:" + translateY + "\n rotate :" + mRotationDegrees + " \n scale :" + mScaleFactor + " \n");
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            return true;
        }
    }

    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees -= detector.getRotationDegreesDelta();
            return true;
        }
    }

    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF d = detector.getFocusDelta();
            mFocusX += d.x;
            mFocusY += d.y;

            return true;
        }
    }

    private class ShoveListener extends ShoveGestureDetector.SimpleOnShoveGestureListener {
        @Override
        public boolean onShove(ShoveGestureDetector detector) {
            mAlpha += detector.getShovePixelsDelta();
            if (mAlpha > 255)
                mAlpha = 255;
            else if (mAlpha < 0)
                mAlpha = 0;

            return true;
        }
    }
}
