package com.github.android_app_bootstrap.widget;

import com.github.android_app_bootstrap.common.Constants;
import java.util.HashMap;

public class SecondFragment extends CustomWebViewFragment {

    public SecondFragment() {

        HashMap <String, String> config = new HashMap<String, String>();
        config.put("distUrl", Constants.TEST_PAGE);
        super.config(config);
    }
}