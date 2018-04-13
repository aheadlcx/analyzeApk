package com.xiaomi.push.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.metoknlp.geofencing.a;
import com.xiaomi.xmpush.thrift.m;
import java.util.ArrayList;
import java.util.Iterator;

public class x {
    public static void a(Context context, String str) {
        ArrayList b = h.a(context).b(str);
        if (b != null && b.size() >= 1) {
            if (h.a(context).e(str) == 0) {
                b.a("appIsUninstalled. failed to delete geofencing with package name. name:" + str);
            }
            Iterator it = b.iterator();
            while (it.hasNext()) {
                m mVar = (m) it.next();
                if (mVar == null) {
                    b.a("appIsUninstalled. failed to find geofence with package name. name:" + str);
                    return;
                }
                a(mVar.a(), context);
                if (j.a(context).b(mVar.a()) == 0) {
                    b.a("appIsUninstalled. failed to delete geoMessage with package name. name:" + str + ", geoId:" + mVar.a());
                }
            }
        }
    }

    public static void a(Context context, boolean z) {
        bb.a(context).a("mipush_extra", "geo_switch", Boolean.valueOf(z));
    }

    public static void a(String str, Context context) {
        new a(context).a(context, "com.xiaomi.xmsf", str);
    }

    public static boolean a(Context context) {
        return a(context, "com.xiaomi.metoknlp", 6);
    }

    public static boolean a(Context context, String str, int i) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null && packageInfo.versionCode >= i;
    }

    public static boolean b(Context context) {
        return a(context, "com.xiaomi.xmsf", 106) && (a(context, "com.xiaomi.metok", 20) || a(context, "com.xiaomi.metoknlp", 6));
    }

    public static boolean c(Context context) {
        return TextUtils.equals(context.getPackageName(), "com.xiaomi.xmsf");
    }

    public static boolean d(Context context) {
        return a(context);
    }

    public static boolean e(Context context) {
        return bb.a(context).a("mipush_extra", "geo_switch", false);
    }
}
