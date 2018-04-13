package com.lt.a.b;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class c {
    public static String a(Context context) {
        String str = "";
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (f.b(str)) {
            return "";
        }
        return str;
    }
}
