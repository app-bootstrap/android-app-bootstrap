package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

public class TableActivity extends Activity {

    private static final Utils utils = new Utils();

    private static final String[] list = new String[]{
            "Toast",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test",
            "Test test test"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.table_activity);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        Intent intent = new Intent(TableActivity.this, ToastActivity.class);
                        startActivity(intent);
                }
            }
        });

    }
}