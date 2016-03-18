package com.github.android_app_bootstrap.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Constants;
import com.github.android_app_bootstrap.component.CustomWebview;

import java.util.HashMap;

public class CustomWebViewFragment extends Fragment implements Callback, OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private CustomWebview customWebview;

    private ImageButton leftButton;
    private ImageButton rightButton;

    private String distUrl = Constants.ERROR_PAGE;

    private View rootView;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.webview_activity, null);
            initView(rootView);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();

        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    public void config(HashMap config) {

        if (config.containsKey("distUrl")) {
            distUrl = "" + config.get("distUrl");
        }

    }

    public void initView(View view) {

        customWebview = (CustomWebview) view.findViewById(R.id.webview);
        customWebview.setDistUrl(distUrl);
        customWebview.initWebview(view, getActivity());

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        /*
        leftButton = (ImageButton) view.findViewById(R.id.toggle_btn);
		leftButton.setOnClickListener(this);
		leftButton.setBackgroundResource(R.drawable.back);
		leftButton.setVisibility(View.VISIBLE);
		leftButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//onBackPressed();
			}
		});

		*/
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View view) {

    }

    public void onRefresh() {
        customWebview.reload();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {mSwipeLayout.setRefreshing(false);
            }
        }, 5000);
    }
}