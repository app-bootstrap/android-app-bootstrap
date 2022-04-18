package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.android_app_bootstrap.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestActivity extends Activity implements View.OnClickListener {

    private static final String API = "https://httpbin.org/get";

    private static final String TAG = "request";

    private TextView show_result;

    class Task extends AsyncTask<Integer, Integer, String> {
        public Task() {
        }

        @Override
        protected String doInBackground(Integer... params)  {
            String lastVersionStr = null;
            Log.i(TAG, API);

            OkHttpClient client = new OkHttpClient();

            try {
                Request request = new Request.Builder()
                        .url(API)
                        .get()
                        .build();

                Response response = client.newCall(request).execute();
                JSONObject res = JSON.parseObject(response.body().string());
                Log.i(TAG, res.toJSONString());
                lastVersionStr = res.toJSONString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lastVersionStr;
        }

        @Override
        protected void onPostExecute(String lastVersionStr) {
            show_result.setText(lastVersionStr);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_activity);
        show_result = (TextView) findViewById(R.id.result);
        initView();
    }

    public void initView() {
        Button button = (Button) findViewById(R.id.request);
        button.setOnClickListener(this);
    }

    void doRequest() {
        Task task = new Task();
        task.execute(200);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request:
                doRequest();
                break;
        }
    }
}