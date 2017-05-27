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
import android.widget.Toast;

import com.github.android_app_bootstrap.R;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.table_activity);

        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getData());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TableActivity.this, getData().get(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        Intent toastIntent = new Intent(TableActivity.this, ToastActivity.class);
                        startActivity(toastIntent);
                        break;
                    case 1:
                        Intent gestureIntent = new Intent(TableActivity.this, GestureActivity.class);
                        startActivity(gestureIntent);
                        break;
                    case 2:
                        Intent alertIntent = new Intent(TableActivity.this, AlertActivity.class);
                        startActivity(alertIntent);
                        break;
                    case 3:
                        Intent locationIntent = new Intent(TableActivity.this, LocationActivity.class);
                        startActivity(locationIntent);
                        break;
                }
            }
        });
    }

    // Model
    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("Toast");
        data.add("Gesture");
        data.add("Alert");
        data.add("Location");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        data.add("Test test test");
        return data;
    }
}