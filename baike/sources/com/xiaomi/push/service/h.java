package com.xiaomi.push.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.metok.geofencing.a;
import com.xiaomi.xmpush.thrift.j;
import java.util.ArrayList;
import java.util.Iterator;

public class h {
    public static void a(Context context, String str) {
        ArrayList b = e.a(context).b(str);
        if (b != null && b.size() >= 1) {
            if (e.a(context).e(str) == 0) {
                b.a("appIsUninstalled. failed to delete geofencing with package name. name:" + str);
            }
            Iterator it = b.iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                if (jVar == null) {
                    b.a("appIsUninstalled. failed to find geofence with package name. name:" + str);
                    return;
                }
                a(jVar.a(), context);
                if (g.a(context).b(jVar.a()) == 0) {
                    b.a("appIsUninstalled. failed to delete geoMessage with package name. name:" + str + ", geoId:" + jVar.a());
                }
            }
        }
    }

    public static void a(String str, Context context) {
        new a(context).a(context, "com.xiaomi.xmsf", str);
    }

    public static boolean a(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.metok", 8192);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null && packageInfo.versionCode >= 20;
    }

    public static boolean b(Context context) {
        return TextUtils.equals(context.getPackageName(), "com.xiaomi.xmsf");
    }
}
