package com.tencent.weibo.sdk.android.api.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.util.Log;
import cn.v6.sixrooms.constants.CommonStrs;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Util {
    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo state : allNetworkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static void saveSharePersistent(Context context, String str, String str2) {
        SharePersistent.getInstance().put(context, str, str2);
    }

    public static void saveSharePersistent(Context context, String str, long j) {
        SharePersistent.getInstance().put(context, str, j);
    }

    public static String getSharePersistent(Context context, String str) {
        return SharePersistent.getInstance().get(context, str);
    }

    public static Long getSharePersistentLong(Context context, String str) {
        return Long.valueOf(SharePersistent.getInstance().getLong(context, str));
    }

    public static void clearSharePersistent(Context context, String str) {
        SharePersistent.getInstance().clear(context, str);
    }

    public static void clearSharePersistent(Context context) {
        SharePersistent instance = SharePersistent.getInstance();
        instance.clear(context, "ACCESS_TOKEN");
        instance.clear(context, "EXPIRES_IN");
        instance.clear(context, "OPEN_ID");
        instance.clear(context, "OPEN_KEY");
        instance.clear(context, "REFRESH_TOKEN");
        instance.clear(context, "NAME");
        instance.clear(context, "NICK");
        instance.clear(context, "CLIENT_ID");
    }

    public static String getLocalIPAddress(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo().getIpAddress();
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }

    public static Location getLocation(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(CommonStrs.TYPE_LOCATION);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(2);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(3);
            criteria.setSpeedRequired(false);
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Log.d("Location", "currentProvider: " + bestProvider);
            return locationManager.getLastKnownLocation(bestProvider);
        } catch (Exception e) {
            return null;
        }
    }

    private String intToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static Drawable loadImageFromUrl(String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            Options options = new Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            return new BitmapDrawable(BitmapFactory.decodeStream(inputStream, null, options));
        } catch (Exception e) {
            return null;
        }
    }

    public static Properties getConfig() {
        Properties properties = new Properties();
        try {
            properties.load(Util.class.getResourceAsStream("/config/config.properties"));
        } catch (IOException e) {
            Log.e("工具包异常", "获取配置文件异常");
            e.printStackTrace();
        }
        return properties;
    }
}
