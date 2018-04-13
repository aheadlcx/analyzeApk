package com.alibaba.mtl.appmonitor;

import android.app.Application;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import com.alibaba.mtl.log.sign.BaseRequestAuth;
import com.alibaba.mtl.log.sign.IRequestAuth;
import com.alibaba.mtl.log.sign.SecurityRequestAuth;
import java.util.Map;

public final class AppMonitorDelegate {
    public static final String DEFAULT_VALUE = "defaultValue";
    public static boolean IS_DEBUG = false;
    public static final String MAX_VALUE = "maxValue";
    public static final String MIN_VALUE = "minValue";
    public static final String TAG = "AppMonitorDelegate";
    private static Application b;
    static volatile boolean i = false;

    public static synchronized void init(Application application) {
        synchronized (AppMonitorDelegate.class) {
            i.a(TAG, "start init");
            try {
                if (!i) {
                    b = application;
                    a.init(application.getApplicationContext());
                    b.init();
                    c.init();
                    a.init(application);
                    l.a(application.getApplicationContext());
                    i = true;
                }
            } catch (Throwable th) {
                destroy();
            }
        }
    }

    public static synchronized void destroy() {
        synchronized (AppMonitorDelegate.class) {
            try {
                i.a(TAG, "start destory");
                if (i) {
                    c.e();
                    c.destroy();
                    b.destroy();
                    if (b != null) {
                        l.b(b.getApplicationContext());
                    }
                    i = false;
                }
            } catch (Throwable th) {
                b.a(th);
            }
        }
    }

    public static synchronized void triggerUpload() {
        synchronized (AppMonitorDelegate.class) {
            try {
                i.a(TAG, "triggerUpload");
                if (i && com.alibaba.mtl.log.a.a.h()) {
                    c.e();
                }
            } catch (Throwable th) {
                b.a(th);
            }
        }
    }

    public static void setStatisticsInterval(int i) {
        for (f fVar : f.a()) {
            fVar.setStatisticsInterval(i);
            setStatisticsInterval(fVar, i);
        }
    }

    public static void setSampling(int i) {
        int i2 = 0;
        i.a(TAG, "[setSampling]");
        f[] a = f.a();
        int length = a.length;
        while (i2 < length) {
            f fVar = a[i2];
            fVar.c(i);
            j.a().a(fVar, i);
            i2++;
        }
    }

    public static void enableLog(boolean z) {
        i.a(TAG, "[enableLog]");
        i.d(z);
    }

    public static void register(String str, String str2, MeasureSet measureSet) {
        register(str, str2, measureSet, null);
    }

    public static void register(String str, String str2, MeasureSet measureSet, boolean z) {
        register(str, str2, measureSet, null, z);
    }

    public static void register(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) {
        register(str, str2, measureSet, dimensionSet, false);
    }

    public static void register(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        try {
            if (!i) {
                return;
            }
            if (com.alibaba.mtl.appmonitor.f.b.isBlank(str) || com.alibaba.mtl.appmonitor.f.b.isBlank(str2)) {
                i.a(TAG, "register stat event. module: ", str, " monitorPoint: ", str2);
                if (IS_DEBUG) {
                    throw new com.alibaba.mtl.appmonitor.b.a("register error. module and monitorPoint can't be null");
                }
                return;
            }
            MetricRepo.getRepo().add(new Metric(str, str2, measureSet, dimensionSet, z));
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static void updateMeasure(String str, String str2, String str3, double d, double d2, double d3) {
        i.a(TAG, "[updateMeasure]");
        try {
            if (i && !com.alibaba.mtl.appmonitor.f.b.isBlank(str) && !com.alibaba.mtl.appmonitor.f.b.isBlank(str2)) {
                Metric metric = MetricRepo.getRepo().getMetric(str, str2);
                if (metric != null && metric.getMeasureSet() != null) {
                    metric.getMeasureSet().upateMeasure(new Measure(str3, Double.valueOf(d3), Double.valueOf(d), Double.valueOf(d2)));
                }
            }
        } catch (Exception e) {
        }
    }

    static void setStatisticsInterval(f fVar, int i) {
        try {
            if (i && fVar != null) {
                c.a(fVar.a(), i);
                if (i > 0) {
                    fVar.b(true);
                } else {
                    fVar.b(false);
                }
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }

    public static void setRequestAuthInfo(boolean z, String str, String str2, String str3) {
        IRequestAuth securityRequestAuth;
        if (z) {
            securityRequestAuth = new SecurityRequestAuth(str, str3);
        } else {
            securityRequestAuth = new BaseRequestAuth(str, str2);
        }
        a.a(securityRequestAuth);
        com.alibaba.mtl.log.a.a.init(b);
    }

    public static void setChannel(String str) {
        a.setChannel(str);
    }

    public static void turnOnRealTimeDebug(Map<String, String> map) {
        com.alibaba.mtl.log.a.a.turnOnRealTimeDebug(map);
    }

    public static void turnOffRealTimeDebug() {
        i.a(TAG, "[turnOffRealTimeDebug]");
    }
}
