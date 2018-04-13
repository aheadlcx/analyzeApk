package com.tencent.tinker.loader.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

public class DefaultApplicationLike extends ApplicationLike {
    private static final String TAG = "Tinker.DefaultAppLike";

    public DefaultApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    public void onLowMemory() {
        Log.d(TAG, "onLowMemory");
    }

    public void onTrimMemory(int i) {
        Log.d(TAG, "onTrimMemory level:" + i);
    }

    public void onTerminate() {
        Log.d(TAG, "onTerminate");
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "onConfigurationChanged:" + configuration.toString());
    }

    public void onBaseContextAttached(Context context) {
        Log.d(TAG, "onBaseContextAttached:");
    }
}
