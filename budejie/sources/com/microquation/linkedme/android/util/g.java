package com.microquation.linkedme.android.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.g.c;
import com.microquation.linkedme.android.g.e;

public class g {
    public static int a(Context context) {
        int i = -1;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static boolean a(Context context, String str) {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            return a(context) >= 23 ? c.a(context.getApplicationContext(), str) == 0 : e.a(context.getApplicationContext(), str) == 0;
        } catch (Exception e) {
            b.a(a.a, "请将支持库版本升级到23及以后");
            return true;
        }
    }
}
