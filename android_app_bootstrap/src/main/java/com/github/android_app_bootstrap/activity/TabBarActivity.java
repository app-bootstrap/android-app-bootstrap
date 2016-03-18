package com.github.android_app_bootstrap.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Constants;
import com.github.android_app_bootstrap.widget.HomeFragment;
import com.github.android_app_bootstrap.widget.PersonalFragment;
import com.github.android_app_bootstrap.widget.SecondFragment;
import com.github.android_app_bootstrap.widget.ThirdFragment;

public class TabBarActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;

    private LayoutInflater layoutInflater;

    private Class fragmentArray[] = {HomeFragment.class, SecondFragment.class, ThirdFragment.class, PersonalFragment.class};

    private int imageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_webview_btn, R.drawable.tab_test_btn, R.drawable.tab_personal_btn};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_activity);
        initView();
    }

    private void initView() {

        layoutInflater = LayoutInflater.from(this);

        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (int i = 0; i < fragmentArray.length; i++) {

            TabSpec tabSpec = fragmentTabHost.newTabSpec(Constants.TAB_LIST[i])
                    .setIndicator(getTabItemView(i));

            fragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(imageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(Constants.TAB_LIST[index]);

        return view;
    }
}