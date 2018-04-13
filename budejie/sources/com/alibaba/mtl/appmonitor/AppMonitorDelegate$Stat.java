package com.alibaba.mtl.appmonitor;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.log.a.a;
import com.alibaba.mtl.log.e.i;
import java.util.List;

public class AppMonitorDelegate$Stat {
    public static void setStatisticsInterval(int i) {
        f.d.setStatisticsInterval(i);
        AppMonitorDelegate.setStatisticsInterval(f.d, i);
    }

    public static void setSampling(int i) {
        j.a().a(f.d, i);
    }

    @Deprecated
    public static boolean checkSampled(String str, String str2) {
        return j.a(f.d, str, str2);
    }

    public static void begin(String str, String str2, String str3) {
        try {
            if (!AppMonitorDelegate.i || !a.h() || !f.d.isOpen()) {
                return;
            }
            if (AppMonitorDelegate.IS_DEBUG || j.a(f.d, str, str2)) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"statEvent begin. module: ", str, " monitorPoint: ", str2, " measureName: ", str3});
                e.a().a(Integer.valueOf(f.d.a()), str, str2, str3);
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static void end(String str, String str2, String str3) {
        try {
            if (!AppMonitorDelegate.i || !a.h() || !f.d.isOpen()) {
                return;
            }
            if (AppMonitorDelegate.IS_DEBUG || j.a(f.d, str, str2)) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"statEvent end. module: ", str, " monitorPoint: ", str2, " measureName: ", str3});
                e.a().a(str, str2, str3);
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static Transaction createTransaction(String str, String str2) {
        return createTransaction(str, str2, null);
    }

    public static Transaction createTransaction(String str, String str2, DimensionValueSet dimensionValueSet) {
        return new Transaction(Integer.valueOf(f.d.a()), str, str2, dimensionValueSet);
    }

    public static void commit(String str, String str2, double d) {
        commit(str, str2, null, d);
    }

    public static void commit(String str, String str2, DimensionValueSet dimensionValueSet, double d) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i.a(AppMonitorDelegate.TAG, "module & monitorPoint must not null");
                return;
            }
            com.alibaba.mtl.log.b.a.v();
            if (!AppMonitorDelegate.i || !a.h() || !f.d.isOpen()) {
                return;
            }
            if (AppMonitorDelegate.IS_DEBUG || j.a(f.d, str, str2)) {
                i.a(AppMonitorDelegate.TAG, new Object[]{"statEvent commit. module: ", str, " monitorPoint: ", str2});
                Metric metric = MetricRepo.getRepo().getMetric(str, str2);
                com.alibaba.mtl.log.b.a.w();
                if (metric != null) {
                    List measures = metric.getMeasureSet().getMeasures();
                    if (measures.size() == 1) {
                        commit(str, str2, dimensionValueSet, ((MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.a().a(MeasureValueSet.class, new Object[0])).setValue(((Measure) measures.get(0)).getName(), d));
                    }
                }
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void commit(java.lang.String r6, java.lang.String r7, com.alibaba.mtl.appmonitor.model.DimensionValueSet r8, com.alibaba.mtl.appmonitor.model.MeasureValueSet r9) {
        /*
        r0 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x0068 }
        if (r0 != 0) goto L_0x000c;
    L_0x0006:
        r0 = android.text.TextUtils.isEmpty(r7);	 Catch:{ Throwable -> 0x0068 }
        if (r0 == 0) goto L_0x0014;
    L_0x000c:
        r0 = "AppMonitorDelegate";
        r1 = "module & monitorPoint must not null";
        com.alibaba.mtl.log.e.i.a(r0, r1);	 Catch:{ Throwable -> 0x0068 }
    L_0x0013:
        return;
    L_0x0014:
        com.alibaba.mtl.log.b.a.v();	 Catch:{ Throwable -> 0x0068 }
        r0 = com.alibaba.mtl.appmonitor.AppMonitorDelegate.i;	 Catch:{ Throwable -> 0x0068 }
        if (r0 == 0) goto L_0x006f;
    L_0x001b:
        r0 = com.alibaba.mtl.log.a.a.h();	 Catch:{ Throwable -> 0x0068 }
        if (r0 == 0) goto L_0x006f;
    L_0x0021:
        r0 = com.alibaba.mtl.appmonitor.a.f.d;	 Catch:{ Throwable -> 0x0068 }
        r0 = r0.isOpen();	 Catch:{ Throwable -> 0x0068 }
        if (r0 == 0) goto L_0x006f;
    L_0x0029:
        r0 = com.alibaba.mtl.appmonitor.AppMonitorDelegate.IS_DEBUG;	 Catch:{ Throwable -> 0x0068 }
        if (r0 != 0) goto L_0x003b;
    L_0x002d:
        r1 = com.alibaba.mtl.appmonitor.a.f.d;	 Catch:{ Throwable -> 0x0068 }
        if (r8 == 0) goto L_0x006d;
    L_0x0031:
        r0 = r8.getMap();	 Catch:{ Throwable -> 0x0068 }
    L_0x0035:
        r0 = com.alibaba.mtl.appmonitor.d.j.a(r1, r6, r7, r0);	 Catch:{ Throwable -> 0x0068 }
        if (r0 == 0) goto L_0x006f;
    L_0x003b:
        r0 = "statEvent commit success";
        r1 = 4;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0068 }
        r2 = 0;
        r3 = "statEvent commit. module: ";
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0068 }
        r2 = 1;
        r1[r2] = r6;	 Catch:{ Throwable -> 0x0068 }
        r2 = 2;
        r3 = " monitorPoint: ";
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0068 }
        r2 = 3;
        r1[r2] = r7;	 Catch:{ Throwable -> 0x0068 }
        com.alibaba.mtl.log.e.i.a(r0, r1);	 Catch:{ Throwable -> 0x0068 }
        com.alibaba.mtl.log.b.a.w();	 Catch:{ Throwable -> 0x0068 }
        r0 = com.alibaba.mtl.appmonitor.a.e.a();	 Catch:{ Throwable -> 0x0068 }
        r1 = com.alibaba.mtl.appmonitor.a.f.d;	 Catch:{ Throwable -> 0x0068 }
        r1 = r1.a();	 Catch:{ Throwable -> 0x0068 }
        r2 = r6;
        r3 = r7;
        r4 = r9;
        r5 = r8;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0068 }
        goto L_0x0013;
    L_0x0068:
        r0 = move-exception;
        com.alibaba.mtl.appmonitor.b.b.a(r0);
        goto L_0x0013;
    L_0x006d:
        r0 = 0;
        goto L_0x0035;
    L_0x006f:
        r0 = "statEvent commit failed,log discard";
        r1 = 4;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0068 }
        r2 = 0;
        r3 = " ,. module: ";
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0068 }
        r2 = 1;
        r1[r2] = r6;	 Catch:{ Throwable -> 0x0068 }
        r2 = 2;
        r3 = " monitorPoint: ";
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0068 }
        r2 = 3;
        r1[r2] = r7;	 Catch:{ Throwable -> 0x0068 }
        com.alibaba.mtl.log.e.i.a(r0, r1);	 Catch:{ Throwable -> 0x0068 }
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.appmonitor.AppMonitorDelegate$Stat.commit(java.lang.String, java.lang.String, com.alibaba.mtl.appmonitor.model.DimensionValueSet, com.alibaba.mtl.appmonitor.model.MeasureValueSet):void");
    }
}
