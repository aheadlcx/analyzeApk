package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.reflect.a;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.g;
import java.util.HashMap;

public class ak {
    public static v a(Context context) {
        try {
            return (context.getPackageManager().getServiceInfo(new ComponentName(HuaweiApiAvailability.SERVICES_PACKAGE, "com.huawei.hms.core.service.HMSCoreService"), 128) == null || !b()) ? v.OTHER : v.HUAWEI;
        } catch (Exception e) {
            return v.OTHER;
        }
    }

    public static boolean a() {
        return MiPushClient.getOpenHmsPush();
    }

    private static boolean b() {
        try {
            String str = (String) a.a("android.os.SystemProperties", "get", "ro.build.hw_emui_api_level", "");
            if (!TextUtils.isEmpty(str) && Integer.parseInt(str) >= 9) {
                return true;
            }
        } catch (Throwable e) {
            b.a(e);
        }
        return false;
    }

    public static boolean b(Context context) {
        return am.a(context).a(g.AggregatePushSwitch.a(), true);
    }

    public static void c(Context context) {
        d a = z.a(context);
        if (a != null) {
            a.a();
        }
    }

    public static void d(Context context) {
        d a = z.a(context);
        if (a != null) {
            a.b();
        }
    }

    public static HashMap<String, String> e(Context context) {
        HashMap<String, String> hashMap = new HashMap();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Exception e) {
            b.d(e.toString());
        }
        int i = -1;
        if (applicationInfo != null) {
            i = applicationInfo.metaData.getInt("com.huawei.hms.client.appid");
        }
        hashMap.put("RegInfo", "brand:" + a(context).name() + "~" + "token" + ":" + AssemblePushHelper.a(context) + "~" + com.umeng.analytics.b.g.e + ":" + context.getPackageName() + "~" + Strategy.APP_ID + ":" + i);
        return hashMap;
    }
}
