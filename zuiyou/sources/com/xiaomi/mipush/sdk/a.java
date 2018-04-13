package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.misc.h;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.g;
import java.util.ArrayList;

public class a {
    public static StackTraceElement[] a;

    public static void a() {
        try {
            a = (StackTraceElement[]) Thread.getAllStackTraces().get(Thread.currentThread());
        } catch (Throwable th) {
        }
    }

    public static void a(Context context) {
        h.a(context).a(new b(context), 20);
    }

    private static String b(int i) {
        int i2 = 4;
        if (a == null || a.length <= 4) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        while (i2 < a.length && i2 < i + 4) {
            try {
                arrayList.add(a[i2].toString());
                i2++;
            } catch (Exception e) {
                return "";
            }
        }
        return arrayList.toString();
    }

    private static boolean d(Context context) {
        am a = am.a(context);
        if (!a.a(g.AggregationSdkMonitorSwitch.a(), false)) {
            return false;
        }
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_upload_call_stack_timestamp", 0)) >= ((long) Math.max(60, a.a(g.AggregationSdkMonitorFrequency.a(), 86400)));
    }

    private static void e(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_upload_call_stack_timestamp", System.currentTimeMillis());
        edit.commit();
    }
}
