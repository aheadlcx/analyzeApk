package com.alibaba.mtl.appmonitor;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.log.b.a;
import com.alibaba.mtl.log.e.i;

public class AppMonitorDelegate$Counter {
    public static void setStatisticsInterval(int i) {
        f.b.setStatisticsInterval(i);
        AppMonitorDelegate.setStatisticsInterval(f.b, i);
    }

    public static void setSampling(int i) {
        j.a().a(f.b, i);
    }

    @Deprecated
    public static boolean checkSampled(String str, String str2) {
        return j.a(f.b, str, str2);
    }

    public static void commit(String str, String str2, double d) {
        commit(str, str2, null, d);
    }

    public static void commit(String str, String str2, String str3, double d) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i.a(AppMonitorDelegate.TAG, "module & monitorPoint must not null");
                return;
            }
            a.z();
            if (!AppMonitorDelegate.i || !com.alibaba.mtl.log.a.a.h() || !f.b.isOpen()) {
                return;
            }
            if (AppMonitorDelegate.IS_DEBUG || j.a(f.b, str, str2)) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"commitCount module: ", str, " monitorPoint: ", str2, " value: ", Double.valueOf(d)});
                a.A();
                e.a().a(f.b.a(), str, str2, str3, d);
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }
}
