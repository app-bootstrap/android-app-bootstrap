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

public class PersonalFragment extends Fragment implements Callback, OnClickListener {
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        TextView titleTextView = (TextView) view.findViewById(R.id.title_text);

        titleTextView.setText(Constants.TAB_LIST[3]);

        button = (Button) view.findViewById(R.id.logout_button);
        button.setOnClickListener(this);

    }

    void logout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
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
            case R.id.logout_button:
                logout();
                break;
        }
    }

}