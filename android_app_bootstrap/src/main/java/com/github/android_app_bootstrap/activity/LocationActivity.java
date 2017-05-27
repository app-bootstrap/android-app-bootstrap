package com.github.android_app_bootstrap.activity;

/**
 * Created by xdf on 10/8/15.
 */

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.android_app_bootstrap.R;
import com.github.android_app_bootstrap.common.Utils;

import java.util.List;

public class LocationActivity extends Activity {

    private final Utils utils = new Utils();
    private TextView text;
    private LocationManager locationManager;
    private String locationProvider;
    private Location location;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_activity);
        initView();
    }

    public void initView() {
        Button button = (Button) findViewById(R.id.alert_button);
        button.setVisibility(View.GONE);
        text = (TextView) findViewById(R.id.show_keyevent);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "no location provider", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 100, 1, locationListener);
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            String locationStr = "latitude：" + location.getLatitude() + "\n"
                    + "longitude：" + location.getLongitude();
            text.setText(locationStr);
        }
    };
}