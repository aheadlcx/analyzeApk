package com.alibaba.mtl.appmonitor;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.log.b.a;
import com.alibaba.mtl.log.e.i;
import java.util.HashMap;
import java.util.Map;

public class AppMonitorDelegate$Alarm {
    public static void setStatisticsInterval(int i) {
        f.a.setStatisticsInterval(i);
        AppMonitorDelegate.setStatisticsInterval(f.a, i);
    }

    public static void setSampling(int i) {
        j.a().a(f.a, i);
    }

    @Deprecated
    public static boolean checkSampled(String str, String str2) {
        return j.a(f.a, str, str2);
    }

    public static void commitSuccess(String str, String str2) {
        commitSuccess(str, str2, null);
    }

    public static void commitSuccess(String str, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i.a(AppMonitorDelegate.TAG, "module & monitorPoint must not null");
                return;
            }
            a.B();
            if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.h() && f.a.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(str, str2, Boolean.valueOf(true), null))) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"commitSuccess module:", str, " monitorPoint:", str2});
                a.C();
                e.a().a(f.a.a(), str, str2, str3);
                return;
            }
            i.a("log discard !", "");
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static void commitFail(String str, String str2, String str3, String str4) {
        commitFail(str, str2, null, str3, str4);
    }

    public static void commitFail(String str, String str2, String str3, String str4, String str5) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i.a(AppMonitorDelegate.TAG, "module & monitorPoint must not null");
                return;
            }
            a.B();
            Map hashMap = new HashMap();
            hashMap.put("_status", "0");
            if (AppMonitorDelegate.i && com.alibaba.mtl.log.a.a.h() && f.a.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(str, str2, Boolean.valueOf(false), hashMap))) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"commitFail module:", str, " monitorPoint:", str2, " errorCode:", str4, "errorMsg:", str5});
                a.C();
                e.a().a(f.a.a(), str, str2, str3, str4, str5);
                return;
            }
            i.a("log discard !", "");
        } catch (Throwable th) {
            b.a(th);
        }
    }
}
