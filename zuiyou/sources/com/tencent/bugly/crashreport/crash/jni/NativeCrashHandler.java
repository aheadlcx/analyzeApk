package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.tencent.bugly.crashreport.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.io.File;

public class NativeCrashHandler implements a {
    private static NativeCrashHandler a;
    private static boolean l = false;
    private static boolean m = false;
    private final Context b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final am d;
    private NativeExceptionHandler e;
    private String f;
    private final boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private b n;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    private static void a(String str) {
        an.c("[Native] Check extra jni for Bugly NDK v%s", new Object[]{str});
        String replace = "2.1.1".replace(".", "");
        String replace2 = "2.3.0".replace(".", "");
        String replace3 = str.replace(".", "");
        if (replace3.length() == 2) {
            replace3 = replace3 + "0";
        } else if (replace3.length() == 1) {
            replace3 = replace3 + "00";
        }
        try {
            if (Integer.parseInt(replace3) >= Integer.parseInt(replace)) {
                l = true;
            }
            if (Integer.parseInt(replace3) >= Integer.parseInt(replace2)) {
                m = true;
            }
        } catch (Throwable th) {
        }
        if (m) {
            an.a("[Native] Info setting jni can be accessed.", new Object[0]);
        } else {
            an.d("[Native] Info setting jni can not be accessed.", new Object[0]);
        }
        if (l) {
            an.a("[Native] Extra jni can be accessed.", new Object[0]);
        } else {
            an.d("[Native] Extra jni can not be accessed.", new Object[0]);
        }
    }

    @SuppressLint({"SdCardPath"})
    protected NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, am amVar, boolean z, String str) {
        this.b = ap.a(context);
        try {
            if (ap.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable th) {
            str = "/data/data/" + com.tencent.bugly.crashreport.common.info.a.a(context).d + "/app_bugly";
        }
        this.n = bVar;
        this.f = str;
        this.c = aVar;
        this.d = amVar;
        this.g = z;
        this.e = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, am amVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (a == null) {
                a = new NativeCrashHandler(context, aVar, bVar, aVar2, amVar, z, str);
            }
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    protected synchronized void a(boolean z) {
        if (this.j) {
            an.d("[Native] Native crash report has already registered.", new Object[0]);
        } else {
            String regist;
            if (this.i) {
                try {
                    regist = regist(this.f, z, 1);
                    if (regist != null) {
                        an.a("[Native] Native Crash Report enable.", new Object[0]);
                        a(regist);
                        this.c.s = regist;
                        this.j = true;
                    }
                } catch (Throwable th) {
                    an.c("[Native] Failed to load Bugly SO file.", new Object[0]);
                }
            } else if (this.h) {
                try {
                    String str = "com.tencent.feedback.eup.jni.NativeExceptionUpload";
                    String str2 = "registNativeExceptionHandler2";
                    Class[] clsArr = new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE};
                    Object[] objArr = new Object[4];
                    objArr[0] = this.f;
                    objArr[1] = com.tencent.bugly.crashreport.common.info.b.a(false);
                    objArr[2] = Integer.valueOf(z ? 1 : 5);
                    objArr[3] = Integer.valueOf(1);
                    regist = (String) ap.a(str, str2, null, clsArr, objArr);
                    if (regist == null) {
                        regist = (String) ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, new Class[]{String.class, String.class, Integer.TYPE}, new Object[]{this.f, com.tencent.bugly.crashreport.common.info.b.a(false), Integer.valueOf(com.tencent.bugly.crashreport.common.info.a.b().L())});
                    }
                    if (regist != null) {
                        this.j = true;
                        com.tencent.bugly.crashreport.common.info.a.b().s = regist;
                        Boolean bool = (Boolean) ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "checkExtraJni", null, new Class[]{String.class}, new Object[]{regist});
                        if (bool != null) {
                            l = bool.booleanValue();
                        }
                        ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        int i = z ? 1 : 5;
                        ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
                    }
                } catch (Throwable th2) {
                }
            }
            this.i = false;
            this.h = false;
        }
    }

    public synchronized void startNativeMonitor() {
        if (this.i || this.h) {
            a(this.g);
        } else {
            boolean z;
            String str = "Bugly";
            String str2 = "NativeRQD";
            if (ap.a(this.c.r)) {
                z = false;
            } else {
                z = true;
            }
            if (c.b) {
                this.i = a(z ? this.c.r : str + "-rqd", z);
                if (!(this.i || z)) {
                    this.h = a(str2, false);
                }
            } else {
                String str3 = this.c.r;
                if (z) {
                    str = str3;
                } else {
                    this.c.getClass();
                    if ("".length() > 0) {
                        this.c.getClass();
                        if (!"".contains("@")) {
                            StringBuilder append = new StringBuilder().append(str).append("-");
                            this.c.getClass();
                            str = append.append("").toString();
                        }
                    }
                }
                this.i = a(str, z);
            }
            if (this.i || this.h) {
                a(this.g);
                this.d.a(new Runnable(this) {
                    final /* synthetic */ NativeCrashHandler a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (ap.a(this.a.b, "native_record_lock", 10000)) {
                            try {
                                this.a.setNativeAppVersion(this.a.c.o);
                                this.a.setNativeAppChannel(this.a.c.q);
                                this.a.setNativeAppPackage(this.a.c.d);
                                this.a.setNativeUserId(this.a.c.g());
                                this.a.setNativeIsAppForeground(this.a.c.a());
                                this.a.setNativeLaunchTime(this.a.c.a);
                            } catch (Throwable th) {
                                if (!an.a(th)) {
                                    th.printStackTrace();
                                }
                            }
                            CrashDetailBean a = b.a(this.a.b, this.a.f, this.a.e);
                            if (a != null) {
                                an.a("[Native] Get crash from native record.", new Object[0]);
                                if (!this.a.n.a(a)) {
                                    this.a.n.a(a, 3000, false);
                                }
                                b.a(false, this.a.f);
                            }
                            this.a.b();
                            ap.b(this.a.b, "native_record_lock");
                            return;
                        }
                        an.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                    }
                });
            }
        }
    }

    private boolean a(String str, boolean z) {
        Throwable th;
        boolean z2;
        try {
            an.a("[Native] Trying to load so: %s", new Object[]{str});
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                an.a("[Native] Successfully loaded SO: %s", new Object[]{str});
                return true;
            } catch (Throwable th2) {
                th = th2;
                z2 = true;
            }
        } catch (Throwable th22) {
            th = th22;
            z2 = false;
            an.d(th.getMessage(), new Object[0]);
            an.d("[Native] Failed to load so: %s", new Object[]{str});
            return z2;
        }
    }

    protected synchronized void a() {
        if (this.j) {
            try {
                if (unregist() != null) {
                    an.a("[Native] Successfully closed native crash report.", new Object[0]);
                    this.j = false;
                }
            } catch (Throwable th) {
                an.c("[Native] Failed to close native crash report.", new Object[0]);
            }
            try {
                ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                this.j = false;
                an.a("[Native] Successfully closed native crash report.", new Object[0]);
            } catch (Throwable th2) {
                an.c("[Native] Failed to close native crash report.", new Object[0]);
                this.i = false;
                this.h = false;
            }
        } else {
            an.d("[Native] Native crash report has already unregistered.", new Object[0]);
        }
        return;
    }

    public void testNativeCrash() {
        if (this.i) {
            testCrash();
        } else {
            an.d("[Native] Bugly SO file has not been load.", new Object[0]);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    protected void b() {
        long b = ap.b() - c.g;
        File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "tomb_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            an.e("[Native] Tomb file format error, delete %s", new Object[]{name});
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                an.c("[Native] Clean tombs %d", new Object[]{Integer.valueOf(i)});
            }
        }
    }

    protected synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            a();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            an.a("user change native %b", new Object[]{Boolean.valueOf(z)});
            this.k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            c(z);
            boolean isUserOpened = isUserOpened();
            com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
            if (a == null) {
                z2 = isUserOpened;
            } else if (!(isUserOpened && a.c().g)) {
                z2 = false;
            }
            if (z2 != this.j) {
                an.a("native changed to %b", new Object[]{Boolean.valueOf(z2)});
                b(z2);
            }
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != this.j) {
                    an.d("server native changed to %b", new Object[]{Boolean.valueOf(strategyBean.g)});
                }
            }
            if (!(com.tencent.bugly.crashreport.common.strategy.a.a().c().g && this.k)) {
                z = false;
            }
            if (z != this.j) {
                an.a("native changed to %b", new Object[]{Boolean.valueOf(z)});
                b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            if (this.i) {
                return appendNativeLog(str, str2, str3);
            }
            Boolean bool = (Boolean) ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
            return bool != null ? bool.booleanValue() : false;
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (an.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null) {
            return false;
        }
        try {
            if (this.i) {
                return putNativeKeyValue(str, str2);
            }
            Boolean bool = (Boolean) ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
            return bool != null ? bool.booleanValue() : false;
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (an.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(int i, String str) {
        if (!this.i || !m) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            m = false;
            return false;
        } catch (Throwable th) {
            if (an.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean filterSigabrtSysLog() {
        return a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return a(14, z ? "true" : "false");
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return a(15, String.valueOf(j));
        } catch (Throwable e) {
            if (!an.a(e)) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
