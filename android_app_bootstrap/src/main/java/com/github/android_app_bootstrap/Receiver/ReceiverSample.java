package com.github.android_app_bootstrap.receiver;

/**
 * Created by xdf on 7/14/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverSample extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("ReceiverSample", "message");
    }
}