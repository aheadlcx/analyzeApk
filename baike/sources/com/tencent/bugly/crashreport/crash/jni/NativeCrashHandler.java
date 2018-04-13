package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.analytics.pro.c;
import java.io.File;

public class NativeCrashHandler implements a {
    private static NativeCrashHandler a;
    private static boolean l = false;
    private static boolean m = false;
    private final Context b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final w d;
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

    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, w wVar, boolean z, String str) {
        this.b = z.a(context);
        try {
            if (z.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable th) {
            str = new StringBuilder(c.a).append(com.tencent.bugly.crashreport.common.info.a.a(context).c).append("/app_bugly").toString();
        }
        this.n = bVar;
        this.f = str;
        this.c = aVar;
        this.d = wVar;
        this.g = z;
        this.e = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, w wVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (a == null) {
                a = new NativeCrashHandler(context, aVar, bVar, wVar, z, str);
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

    private synchronized void a(boolean z) {
        if (this.j) {
            x.d("[Native] Native crash report has already registered.", new Object[0]);
        } else {
            String regist;
            String replace;
            String replace2;
            if (this.i) {
                try {
                    regist = regist(this.f, z, 1);
                    if (regist != null) {
                        x.a("[Native] Native Crash Report enable.", new Object[0]);
                        x.c("[Native] Check extra jni for Bugly NDK v%s", regist);
                        replace = "2.1.1".replace(".", "");
                        String replace3 = "2.3.0".replace(".", "");
                        replace2 = regist.replace(".", "");
                        if (replace2.length() == 2) {
                            replace2 = replace2 + "0";
                        } else if (replace2.length() == 1) {
                            replace2 = replace2 + "00";
                        }
                        try {
                            if (Integer.parseInt(replace2) >= Integer.parseInt(replace)) {
                                l = true;
                            }
                            if (Integer.parseInt(replace2) >= Integer.parseInt(replace3)) {
                                m = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (m) {
                            x.a("[Native] Info setting jni can be accessed.", new Object[0]);
                        } else {
                            x.d("[Native] Info setting jni can not be accessed.", new Object[0]);
                        }
                        if (l) {
                            x.a("[Native] Extra jni can be accessed.", new Object[0]);
                        } else {
                            x.d("[Native] Extra jni can not be accessed.", new Object[0]);
                        }
                        this.c.n = regist;
                        this.j = true;
                    }
                } catch (Throwable th2) {
                    x.c("[Native] Failed to load Bugly SO file.", new Object[0]);
                }
            } else if (this.h) {
                try {
                    regist = "com.tencent.feedback.eup.jni.NativeExceptionUpload";
                    replace = "registNativeExceptionHandler2";
                    Class[] clsArr = new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE};
                    Object[] objArr = new Object[4];
                    objArr[0] = this.f;
                    objArr[1] = com.tencent.bugly.crashreport.common.info.b.a(false);
                    objArr[2] = Integer.valueOf(z ? 1 : 5);
                    objArr[3] = Integer.valueOf(1);
                    replace2 = (String) z.a(regist, replace, null, clsArr, objArr);
                    if (replace2 == null) {
                        Class[] clsArr2 = new Class[]{String.class, String.class, Integer.TYPE};
                        Object[] objArr2 = new Object[3];
                        objArr2[0] = this.f;
                        objArr2[1] = com.tencent.bugly.crashreport.common.info.b.a(false);
                        com.tencent.bugly.crashreport.common.info.a.b();
                        objArr2[2] = Integer.valueOf(com.tencent.bugly.crashreport.common.info.a.J());
                        replace2 = (String) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, clsArr2, objArr2);
                    }
                    if (replace2 != null) {
                        this.j = true;
                        com.tencent.bugly.crashreport.common.info.a.b().n = replace2;
                        Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "checkExtraJni", null, new Class[]{String.class}, new Object[]{replace2});
                        if (bool != null) {
                            l = bool.booleanValue();
                        }
                        z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        int i = z ? 1 : 5;
                        z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
                    }
                } catch (Throwable th3) {
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
            String str = "Bugly";
            boolean z = !z.a(this.c.m);
            String str2 = this.c.m;
            if (z) {
                str = str2;
            } else {
                this.c.getClass();
            }
            this.i = a(str, z);
            if (this.i || this.h) {
                a(this.g);
                this.d.a(new c(this));
            }
        }
    }

    private static boolean a(String str, boolean z) {
        Throwable th;
        boolean z2;
        try {
            x.a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                x.a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th2) {
                th = th2;
                z2 = true;
            }
        } catch (Throwable th22) {
            th = th22;
            z2 = false;
            x.d(th.getMessage(), new Object[0]);
            x.d("[Native] Failed to load so: %s", str);
            return z2;
        }
    }

    private synchronized void b() {
        if (this.j) {
            try {
                if (unregist() != null) {
                    x.a("[Native] Successfully closed native crash report.", new Object[0]);
                    this.j = false;
                }
            } catch (Throwable th) {
                x.c("[Native] Failed to close native crash report.", new Object[0]);
            }
            try {
                z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                this.j = false;
                x.a("[Native] Successfully closed native crash report.", new Object[0]);
            } catch (Throwable th2) {
                x.c("[Native] Failed to close native crash report.", new Object[0]);
                this.i = false;
                this.h = false;
            }
        } else {
            x.d("[Native] Native crash report has already unregistered.", new Object[0]);
        }
        return;
    }

    public void testNativeCrash() {
        if (this.i) {
            testCrash();
        } else {
            x.d("[Native] Bugly SO file has not been load.", new Object[0]);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    protected final void a() {
        long b = z.b() - com.tencent.bugly.crashreport.crash.c.g;
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
                            x.e("[Native] Tomb file format error, delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                x.c("[Native] Clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            x.a("user change native %b", Boolean.valueOf(z));
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
                x.a("native changed to %b", Boolean.valueOf(z2));
                b(z2);
            }
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != this.j) {
                    x.d("server native changed to %b", Boolean.valueOf(strategyBean.g));
                }
            }
            if (!(com.tencent.bugly.crashreport.common.strategy.a.a().c().g && this.k)) {
                z = false;
            }
            if (z != this.j) {
                x.a("native changed to %b", Boolean.valueOf(z));
                b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if (!this.h && !this.i) {
            return false;
        }
        if (!l) {
            return false;
        }
        if (str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            if (this.i) {
                return appendNativeLog(str, str2, str3);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
            return bool != null ? bool.booleanValue() : false;
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if (!this.h && !this.i) {
            return false;
        }
        if (!l) {
            return false;
        }
        if (str == null || str2 == null) {
            return false;
        }
        try {
            if (this.i) {
                return putNativeKeyValue(str, str2);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
            return bool != null ? bool.booleanValue() : false;
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
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
            if (x.a(th)) {
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
        return a(14, z ? "true" : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return a(15, String.valueOf(j));
        } catch (Throwable e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
