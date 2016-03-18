package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.Bundle;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Constants;
import com.github.android_app_bootstrap.component.CustomWebview;

public class WebviewActivity extends Activity {

    private CustomWebview customWebview;
    private String distUrl = Constants.TEST_PAGE;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        initView();
    }

    public void initView() {
        customWebview = (CustomWebview) findViewById(R.id.webview);
        customWebview.setDistUrl(distUrl);
        customWebview.initWebview(customWebview.getRootView(), this);
    }
}
