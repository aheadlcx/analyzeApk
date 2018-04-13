package com.budejie.www.push;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class f {
    public static String a(Context context, String str) {
        String str2 = null;
        if (!(context == null || str == null)) {
            try {
                Bundle bundle;
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    bundle = applicationInfo.metaData;
                } else {
                    bundle = null;
                }
                if (bundle != null) {
                    str2 = bundle.getString(str);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return str2;
    }
}
