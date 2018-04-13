package com.alibaba.mtl.appmonitor;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.log.b.a;
import com.alibaba.mtl.log.e.i;

public class AppMonitorDelegate$OffLineCounter {
    public static void setStatisticsInterval(int i) {
        f.c.setStatisticsInterval(i);
        AppMonitorDelegate.setStatisticsInterval(f.c, i);
    }

    public static void setSampling(int i) {
        j.a().a(f.c, i);
    }

    @Deprecated
    public static boolean checkSampled(String str, String str2) {
        return j.a(f.c, str, str2);
    }

    public static void commit(String str, String str2, double d) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i.a(AppMonitorDelegate.TAG, "module & monitorPoint must not null");
                return;
            }
            a.x();
            if (!AppMonitorDelegate.i || !com.alibaba.mtl.log.a.a.h() || !f.c.isOpen()) {
                return;
            }
            if (AppMonitorDelegate.IS_DEBUG || j.a(f.c, str, str2)) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"commitOffLineCount module: ", str, " monitorPoint: ", str2, " value: ", Double.valueOf(d)});
                a.y();
                e.a().a(f.c.a(), str, str2, null, d);
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }
}
