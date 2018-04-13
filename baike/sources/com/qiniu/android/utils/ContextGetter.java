package com.qiniu.android.utils;

import android.app.Application;
import android.content.Context;

public final class ContextGetter {
    public static Context applicationContext() {
        try {
            return a().getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Application a() throws Exception {
        return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
    }
}
