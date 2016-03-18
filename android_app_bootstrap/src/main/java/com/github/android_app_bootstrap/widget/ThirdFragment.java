package com.github.android_app_bootstrap.widget;

import com.github.android_app_bootstrap.common.Constants;

import java.util.HashMap;

public class ThirdFragment extends CustomWebViewFragment {

    public ThirdFragment() {

        HashMap <String, String> config = new HashMap<String, String>();
        config.put("distUrl", Constants.WEB_PAGE);
        super.config(config);
    }
}