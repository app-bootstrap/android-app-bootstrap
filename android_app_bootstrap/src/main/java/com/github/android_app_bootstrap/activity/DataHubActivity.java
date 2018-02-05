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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataHubActivity extends Activity implements View.OnClickListener {

    private static final String API = (String) Utils.dataHubAdaptor("testApi");

    private static final String TAG = "datahub";

    private TextView show_result;

    class Task extends AsyncTask<Integer, Integer, String> {
        public Task() {
        }

        @Override
        protected String doInBackground(Integer... params) {
            HttpClient client = new DefaultHttpClient();
            StringBuilder builder = new StringBuilder();
            HttpGet myget = new HttpGet(API);
            String lastVersionStr = null;
            Log.i(TAG, API);
            try {
                HttpResponse response = client.execute(myget);
                int statusCode = response.getStatusLine().getStatusCode();
                Log.i(TAG, "status code: " + statusCode);
                if (statusCode == 200) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(response.getEntity()
                                    .getContent()));
                    for (String s = reader.readLine(); s != null; s = reader
                            .readLine()) {
                        builder.append(s);
                    }
                    JSONObject res = JSON.parseObject(builder.toString());
                    Log.i(TAG, res.toJSONString());
                    lastVersionStr = res.toJSONString();
                } else {
                    Log.i(TAG, "failed");
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
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
        setContentView(R.layout.datahub_activity);
        show_result = (TextView) findViewById(R.id.result);
        initView();
    }

    public void initView() {
        Button button = (Button) findViewById(R.id.request);
        button.setOnClickListener(this);
    }

    void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(), "request", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, -50, 100);
        toast.show();
    }

    void doRequest() {
        Task task = new Task();
        task.execute(200);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request:
                showToast();
                doRequest();
                break;

        }
    }
}

