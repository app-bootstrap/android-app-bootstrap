package com.github.android_app_bootstrap.component;

/**
 * Created by xdf on 10/3/15.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.activity.WebviewActivity;
import com.github.android_app_bootstrap.common.Constants;
import com.github.android_app_bootstrap.common.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CustomWebview extends WebView {

    Utils utils = new Utils();

    private static final String PREFIX = "jsbridge://";
    private static final String JSFILENAME = "JSBridge.js";
    public Boolean isLoad = false;
    private String distUrl = Constants.ERROR_PAGE;
    private View parentView;
    private Activity parentActivity;
    private Toast loadingToast;

    LottieAnimationView animationView = null;

    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomWebview(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setDistUrl(String distUrl) {
        this.distUrl = distUrl;
    }

    public void setWebViewTitle(String text) {

        TextView title = (TextView) parentView.findViewById(R.id.title_text);

        title.setText(text);
    }

    public void pushView(String url) {

        utils.debug("push view to url --> " + url);

        Intent intent = new Intent(parentActivity, WebviewActivity.class);
        intent.putExtra("url", url);
        parentActivity.startActivity(intent);
    }

    public void popView() {
        parentActivity.finish();
    }

    public String getVersion() {
        String version = "unknown";
        PackageManager manager = getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    void showLoading() {
        loadingToast = Toast.makeText(getContext(), "Loading...", Toast.LENGTH_LONG);
        loadingToast.setDuration(Toast.LENGTH_LONG);
        loadingToast.setGravity(Gravity.CENTER, 0, 0);
        loadingToast.show();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    public void initWebview(View _parentView, Activity _parentActivity) {

        showLoading();
        parentView = _parentView;
        parentActivity = _parentActivity;
        utils.debug("init webview");

        String originUAString = getSettings().getUserAgentString();
        originUAString += " " + Constants.CUSTOM_UA_FIELD;
        originUAString += "/" + getVersion();
        getSettings().setUserAgentString(originUAString);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setSupportZoom(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        animationView = _parentView.findViewById(R.id.animation_view);

        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                utils.debug("onReceivedTitle:" + title);
                setWebViewTitle(title);
            }
        });

        setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                isLoad = true;
                loadingToast.cancel();
                if (null != animationView) {
                    animationView.setVisibility(View.GONE);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlString) {

                if (urlString.startsWith(PREFIX)) {
                    urlString = urlString.replace(PREFIX, "http://");

                    URL url;
                    try {
                        url = new URL(urlString);
                        String host = url.getHost();

                        if (host.equals("call")) {
                            String qs = url.getQuery();
                            HashMap query = utils.parseQuery(qs);
                            String method = (String) query.get("method");
                            String dataString = (String) query.get("data");

                            if (method.equals("setTitle")) {

                                if (!dataString.isEmpty()) {

                                    JSONObject data = JSON.parseObject(dataString);
                                    String title = data.getString("title");

                                    if (!title.isEmpty()) {
                                        setWebViewTitle(title);
                                    }
                                }

                            } else if (method.equals("pushView")) {

                                if (!dataString.isEmpty()) {

                                    JSONObject data = JSON.parseObject(dataString);
                                    String distUrl = data.getString("url");

                                    if (!distUrl.isEmpty()) {

                                        if (distUrl.equals(Constants.TEST)) {
                                            distUrl = Constants.TEST_PAGE;
                                        }

                                        pushView(distUrl);
                                    }
                                }

                            } else if (method.equals("popView")) {
                                popView();
                            }
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return true;
                } else {
                    utils.debug("redirect url: " + urlString);
                    view.loadUrl(urlString);
                    return true;
                }
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                view.loadUrl(Constants.ERROR_PAGE);
            }
        });

        InputStream in = null;

        try {
            in = getContext().getAssets().open(JSFILENAME);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(in));
            String line;

            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) {
                    sb.append(line);
                }
            } while (line != null);

            bufferedReader.close();
            in.close();
            loadUrl(distUrl);
            utils.debug("loadurl :" + distUrl);
            loadUrl("javascript:" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
