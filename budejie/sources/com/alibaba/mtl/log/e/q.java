package com.alibaba.mtl.log.e;

import android.util.Log;

public class q {
    private static final String TAG = q.class.getSimpleName();

    public static String get(String str) {
        String str2 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls.newInstance(), new Object[]{str});
        } catch (Throwable e) {
            Log.e(TAG, "get() ERROR!!! Exception!", e);
            return str2;
        }
    }

    public static String get(String str, String str2) {
        String str3 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{str, str2});
        } catch (Throwable e) {
            Log.e(TAG, "get() ERROR!!! Exception!", e);
            return str3;
        }
    }
}
