package com.alibaba.mtl.appmonitor;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.util.Log;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.log.e.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public final class AppMonitor {
    public static final String TAG = "AppMonitor";
    private static Application a = null;
    /* renamed from: a */
    private static ServiceConnection f0a = new AppMonitor$5();
    /* renamed from: a */
    private static HandlerThread f1a = null;
    /* renamed from: a */
    private static AppMonitor$b f2a = AppMonitor$b.b;
    /* renamed from: a */
    private static AppMonitor$c f3a = null;
    /* renamed from: a */
    protected static IMonitor f4a;
    /* renamed from: a */
    private static Object f5a = new Object();
    /* renamed from: a */
    private static String f6a;
    /* renamed from: a */
    private static List<AppMonitor$a> f7a = Collections.synchronizedList(new ArrayList());
    /* renamed from: a */
    private static volatile boolean f8a;
    private static String b;
    /* renamed from: b */
    private static boolean f9b = false;
    private static String c;
    /* renamed from: c */
    private static boolean f10c;
    private static String d;
    private static Context mContext;

    public static class Alarm {
        public static void setStatisticsInterval(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$1(i));
            }
        }

        public static void setSampling(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$2(i));
            }
        }

        @Deprecated
        public static boolean checkSampled(String str, String str2) {
            boolean z = false;
            if (AppMonitor.f4a != null) {
                try {
                    z = AppMonitor.f4a.alarm_checkSampled(str, str2);
                } catch (Exception e) {
                    AppMonitor.a(e);
                }
            }
            return z;
        }

        public static void commitSuccess(String str, String str2) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$3(str, str2));
            }
        }

        public static void commitSuccess(String str, String str2, String str3) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$4(str, str2, str3));
            }
        }

        public static void commitFail(String str, String str2, String str3, String str4) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$5(str, str2, str3, str4));
            }
        }

        public static void commitFail(String str, String str2, String str3, String str4, String str5) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Alarm$6(str, str2, str3, str4, str5));
            }
        }
    }

    public static class Counter {
        public static void setStatisticsInterval(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Counter$1(i));
            }
        }

        public static void setSampling(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Counter$2(i));
            }
        }

        @Deprecated
        public static boolean checkSampled(String str, String str2) {
            boolean z = false;
            if (AppMonitor.f4a != null) {
                try {
                    z = AppMonitor.f4a.counter_checkSampled(str, str2);
                } catch (Exception e) {
                    AppMonitor.a(e);
                }
            }
            return z;
        }

        public static void commit(String str, String str2, double d) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Counter$3(str, str2, d));
            }
        }

        public static void commit(String str, String str2, String str3, double d) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$Counter$4(str, str2, str3, d));
            }
        }
    }

    public static class OffLineCounter {
        public static void setStatisticsInterval(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$OffLineCounter$1(i));
            }
        }

        public static void setSampling(int i) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$OffLineCounter$2(i));
            }
        }

        @Deprecated
        public static boolean checkSampled(String str, String str2) {
            boolean z = false;
            if (AppMonitor.f4a != null) {
                try {
                    z = AppMonitor.f4a.offlinecounter_checkSampled(str, str2);
                } catch (Exception e) {
                    AppMonitor.a(e);
                }
            }
            return z;
        }

        public static void commit(String str, String str2, double d) {
            if (AppMonitor.f2a) {
                AppMonitor.f2a.a(new AppMonitor$OffLineCounter$3(str, str2, d));
            }
        }
    }

    static {
        try {
            System.loadLibrary("ut_c_api");
            Log.i(TAG, "load ut_c_api.so success");
        } catch (Throwable th) {
            Log.w(TAG, "load ut_c_api.so failed");
        }
    }

    public static synchronized void init(Application application) {
        synchronized (AppMonitor.class) {
            i.a(TAG, new Object[]{"[init]"});
            try {
                if (!f8a) {
                    a = application;
                    if (a != null) {
                        mContext = a.getApplicationContext();
                    }
                    f1a = new HandlerThread("AppMonitor_Client");
                    f1a.start();
                    f3a = new AppMonitor$c(f1a.getLooper());
                    if (f2a == AppMonitor$b.b) {
                        b();
                    } else if (f2a) {
                        f3a.a(true);
                    }
                    f3a.a(f2a);
                    f8a = true;
                }
            } catch (Throwable th) {
            }
        }
    }

    @Deprecated
    public static synchronized void destroy() {
        synchronized (AppMonitor.class) {
            if (b()) {
                f3a.a(new AppMonitor$1());
            }
        }
    }

    @Deprecated
    public static synchronized void triggerUpload() {
        synchronized (AppMonitor.class) {
            if (f8a) {
                f3a.a(new AppMonitor$10());
            }
        }
    }

    public static void setStatisticsInterval(int i) {
        if (b()) {
            f3a.a(new AppMonitor$11(i));
        }
    }

    public static void setSampling(int i) {
        if (b()) {
            f3a.a(new AppMonitor$12(i));
        }
    }

    public static void enableLog(boolean z) {
        if (b()) {
            f3a.a(new AppMonitor$13(z));
        }
    }

    public static void register(String str, String str2, MeasureSet measureSet) {
        if (b()) {
            f3a.a(new AppMonitor$14(str, str2, measureSet));
            a(str, str2, measureSet, null, false);
        }
    }

    public static void register(String str, String str2, MeasureSet measureSet, boolean z) {
        if (b()) {
            f3a.a(new AppMonitor$15(str, str2, measureSet, z));
            a(str, str2, measureSet, null, z);
        }
    }

    public static void register(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) {
        if (b()) {
            f3a.a(new AppMonitor$16(str, str2, measureSet, dimensionSet));
            a(str, str2, measureSet, dimensionSet, false);
        }
    }

    public static void register(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        if (b()) {
            registerInternal(str, str2, measureSet, dimensionSet, z, false);
        }
    }

    public static void register(String str, String str2, String[] strArr, String[] strArr2, boolean z) {
        int i = 0;
        String str3 = TAG;
        Object[] objArr = new Object[9];
        objArr[0] = "[register]";
        objArr[1] = "module:";
        objArr[2] = str;
        objArr[3] = "measures:";
        objArr[4] = strArr == null ? "null" : new JSONArray(Arrays.asList(strArr)).toString();
        objArr[5] = "dimensions:";
        objArr[6] = strArr2 == null ? "null" : new JSONArray(Arrays.asList(strArr2)).toString();
        objArr[7] = "isCommitDetail:";
        objArr[8] = Boolean.valueOf(z);
        i.a(str3, objArr);
        if (strArr != null) {
            MeasureSet create = MeasureSet.create();
            for (String addMeasure : strArr) {
                create.addMeasure(addMeasure);
            }
            DimensionSet dimensionSet = null;
            if (strArr2 != null) {
                dimensionSet = DimensionSet.create();
                while (i < strArr2.length) {
                    dimensionSet.addDimension(strArr2[i]);
                    i++;
                }
            }
            register(str, str2, create, dimensionSet, z);
            return;
        }
        i.a(TAG, new Object[]{"register failed:no mearsure"});
    }

    public static void updateMeasure(String str, String str2, String str3, double d, double d2, double d3) {
        i.a(TAG, new Object[]{"[updateMeasure]"});
        if (b()) {
            f3a.post(new AppMonitor$17(str, str2, str3, d, d2, d3));
        }
    }

    public static void registerInternal(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z, boolean z2) {
        if (b()) {
            i.a(TAG, new Object[]{"[registerInternal] : module:", str, "monitorPoint:", str2, "measures:", measureSet, "dimensions:", dimensionSet, "isCommitDetail:", Boolean.valueOf(z), "isInternal:", Boolean.valueOf(z2)});
            if (!z2) {
                a(str, str2, measureSet, dimensionSet, z);
            }
            f3a.a(a(str, str2, measureSet, dimensionSet, z));
        }
    }

    /* renamed from: a */
    private static void m1a(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        try {
            AppMonitor$a appMonitor$a = new AppMonitor$a();
            appMonitor$a.o = str;
            appMonitor$a.p = str2;
            appMonitor$a.b = measureSet;
            appMonitor$a.b = dimensionSet;
            appMonitor$a.g = z;
            f7a.add(appMonitor$a);
        } catch (Throwable th) {
        }
    }

    public static void setStatisticsInterval(f fVar, int i) {
        if (b()) {
            f3a.a(new AppMonitor$2(a(fVar), i));
        }
    }

    private static int a(f fVar) {
        return fVar.a();
    }

    public static void setRequestAuthInfo(boolean z, String str, String str2, String str3) {
        if (b()) {
            f3a.a(a(z, str, str2, str3));
            f10c = z;
            b = str;
            c = str2;
            d = str3;
        }
    }

    public static void setChannel(String str) {
        if (b()) {
            f3a.a(a(str));
            f6a = str;
        }
    }

    public static void turnOnRealTimeDebug(Map<String, String> map) {
        if (b()) {
            f3a.a(new AppMonitor$3(map));
        }
    }

    public static void turnOffRealTimeDebug() {
        if (b()) {
            f3a.a(new AppMonitor$4());
        }
    }

    /* renamed from: a */
    private static boolean m2a() {
        if (a == null) {
            return false;
        }
        boolean bindService = a.getApplicationContext().bindService(new Intent(a.getApplicationContext(), AppMonitorService.class), f0a, 1);
        if (!bindService) {
            b();
        }
        i.a(TAG, new Object[]{"bindsuccess:", Boolean.valueOf(bindService)});
        return bindService;
    }

    private static void a(Exception exception) {
        i.a(TAG, "", exception);
        if (exception instanceof DeadObjectException) {
            f2a;
        }
    }

    /* renamed from: a */
    private static synchronized void m0a() {
        synchronized (AppMonitor.class) {
            i.a(TAG, new Object[]{"[restart]"});
            try {
                if (f9b) {
                    f9b = false;
                    b();
                    f2a.run();
                    a(f10c, b, c, d).run();
                    a(f6a).run();
                    synchronized (f7a) {
                        for (int i = 0; i < f7a.size(); i++) {
                            AppMonitor$a appMonitor$a = (AppMonitor$a) f7a.get(i);
                            if (appMonitor$a != null) {
                                try {
                                    a(appMonitor$a.o, appMonitor$a.p, appMonitor$a.b, appMonitor$a.b, appMonitor$a.g).run();
                                } catch (Throwable th) {
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        }
    }

    private static void b() {
        f4a = new Monitor(a);
        f2a = AppMonitor$b.b;
        i.a(TAG, "Start AppMonitor Service failed,AppMonitor run in local Mode...");
    }

    /* renamed from: b */
    private static boolean m3b() {
        if (!f8a) {
            i.a(TAG, new Object[]{"Please call init() before call other method"});
        }
        return f8a;
    }

    private static Runnable a() {
        return new AppMonitor$6();
    }

    private static Runnable a(boolean z, String str, String str2, String str3) {
        return new AppMonitor$7(z, str, str2, str3);
    }

    private static Runnable a(String str) {
        return new AppMonitor$8(str);
    }

    private static Runnable a(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        return new AppMonitor$9(str, str2, measureSet, dimensionSet, z);
    }
}
