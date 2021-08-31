package com.github.android_app_bootstrap.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.activity.*;
import com.github.android_app_bootstrap.common.Constants;
import com.github.android_app_bootstrap.common.Utils;
import com.mpaas.nebula.adapter.api.MPNebula;

public class HomeFragment extends Fragment implements Callback, OnClickListener {

    Utils utils = new Utils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.title_text);
        titleTextView.setText(Constants.TAB_LIST[0]);
        Button button = (Button) view.findViewById(R.id.list_button);
        button.setOnClickListener(this);
    }

    void goList() {
        Intent intent = new Intent(getActivity(), TableActivity.class);
        startActivity(intent);
    }

    void goWebView() {
        MPNebula.startUrl("https://www.github.com");
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
        switch (view.getId()) {
            case R.id.list_button:
                goList();
                break;
            case R.id.webview_button:
                goWebView();
                break;
        }
    }
}