package com.github.android_app_bootstrap.common;

/**
 * Created by xdf on 9/4/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Utils {

    public void debug(String string) {
        Log.i(Constants.TAG, " debug:" + string);
    }

    public HashMap<String, String> parseQuery(String querystring) {
        HashMap<String, String> res = new HashMap<String, String>();

        try {
            String text = URLDecoder.decode(querystring, "UTF-8");
            String[] arr = text.split("\\&");

            for (int i = 0; i < arr.length; i++) {
                String[] temp = arr[i].split("\\=");
                res.put(temp[0], temp[1]);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public void toast(Context context, String content) {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static String md5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }

    public String getData(Context context, String key) {
        SharedPreferences getter = context.getSharedPreferences(
                "localstoregeXML", 0);
        String value = getter.getString(key, "");
        return value;
    }

    public void setData(Context context, String key, String value) {
        removeData(context, value);
        SharedPreferences settings = context.getSharedPreferences(
                "localstoregeXML", 0);
        SharedPreferences.Editor localEditor = settings.edit();
        localEditor.putString(key, value);
        localEditor.commit();
    }

    public void removeData(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                "localstoregeXML", 0);
        SharedPreferences.Editor localEditor = settings.edit();
        localEditor.remove(key);
        localEditor.commit();
    }

    public static String encodeURL(String string) {
        try {
            String strUTF8 = URLEncoder.encode(string, "UTF-8");
            return strUTF8;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String dataHubAdaptor(String APIName) {
        return (String) Constants.DATAHUB_HOST + "/data/" + Constants.DATAHUB_NAME_1 + "/" + APIName;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetInfo != null) {
            Toast.makeText(context, "Active Network: " + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (ConnectivityManager.TYPE_WIFI == activeNetInfo.getType()) {
                return true;
            }
        }
        return false;
    }
}