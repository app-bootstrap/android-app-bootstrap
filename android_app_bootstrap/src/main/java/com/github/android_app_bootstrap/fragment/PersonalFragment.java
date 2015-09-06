package com.github.android_app_bootstrap.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;

public class PersonalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        TextView titleTextView = (TextView) view.findViewById(R.id.title_text);

        titleTextView.setText("我的");

    }

}