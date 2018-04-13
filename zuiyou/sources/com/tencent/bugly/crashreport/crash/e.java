package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;

public class e implements UncaughtExceptionHandler {
    private static String h = null;
    private static final Object i = new Object();
    protected final Context a;
    protected final b b;
    protected final a c;
    protected final com.tencent.bugly.crashreport.common.info.a d;
    protected UncaughtExceptionHandler e;
    protected UncaughtExceptionHandler f;
    protected boolean g = false;
    private int j;

    public e(Context context, b bVar, a aVar, com.tencent.bugly.crashreport.common.info.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public synchronized void a() {
        if (this.j >= 10) {
            an.a("java crash handler over %d, no need set.", new Object[]{Integer.valueOf(10)});
        } else {
            this.g = true;
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                if (!getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                        an.a("backup system java handler: %s", new Object[]{defaultUncaughtExceptionHandler.toString()});
                        this.f = defaultUncaughtExceptionHandler;
                        this.e = defaultUncaughtExceptionHandler;
                    } else {
                        an.a("backup java handler: %s", new Object[]{defaultUncaughtExceptionHandler.toString()});
                        this.e = defaultUncaughtExceptionHandler;
                    }
                }
            }
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.j++;
            an.a("registered java monitor: %s", new Object[]{toString()});
        }
    }

    public synchronized void b() {
        this.g = false;
        an.a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            an.a("Java monitor to unregister: %s", new Object[]{toString()});
            Thread.setDefaultUncaughtExceptionHandler(this.e);
            this.j--;
        }
    }

    protected void a(Thread thread, Throwable th) {
        an.e("current process die", new Object[0]);
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    public CrashDetailBean a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (th == null) {
            an.d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        Object a;
        boolean m = c.a().m();
        String str2 = (m && z) ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (m && z) {
            an.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.B = b.i();
        crashDetailBean.C = b.g();
        crashDetailBean.D = b.k();
        crashDetailBean.E = this.d.p();
        crashDetailBean.F = this.d.o();
        crashDetailBean.G = this.d.q();
        crashDetailBean.w = ap.a(this.a, c.e, c.h);
        crashDetailBean.x = ao.a();
        String str3 = "user log size:%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.x == null ? 0 : crashDetailBean.x.length);
        an.a(str3, objArr);
        crashDetailBean.b = z ? 0 : 2;
        crashDetailBean.e = this.d.h();
        crashDetailBean.f = this.d.o;
        crashDetailBean.g = this.d.w();
        crashDetailBean.m = this.d.g();
        String name = th.getClass().getName();
        String b = b(th, 1000);
        if (b == null) {
            b = "";
        }
        String str4 = "stack frame :%d, has cause %b";
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        an.e(str4, objArr2);
        str3 = "";
        if (th.getStackTrace().length > 0) {
            str3 = th.getStackTrace()[0].toString();
        }
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.n = name;
            crashDetailBean.o = b + "" + str2;
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            crashDetailBean.p = str3;
            a = a(th, c.f);
            crashDetailBean.q = a;
        } else {
            crashDetailBean.n = th2.getClass().getName();
            crashDetailBean.o = b(th2, 1000);
            if (crashDetailBean.o == null) {
                crashDetailBean.o = "";
            }
            if (th2.getStackTrace().length > 0) {
                crashDetailBean.p = th2.getStackTrace()[0].toString();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name).append(":").append(b).append("\n");
            stringBuilder.append(str3);
            stringBuilder.append("\n......");
            stringBuilder.append("\nCaused by:\n");
            stringBuilder.append(crashDetailBean.n).append(":").append(crashDetailBean.o).append("\n");
            a = a(th2, c.f);
            stringBuilder.append(a);
            crashDetailBean.q = stringBuilder.toString();
        }
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = ap.b(crashDetailBean.q.getBytes());
        try {
            crashDetailBean.y = ap.a(c.f, false);
            crashDetailBean.z = this.d.e;
            crashDetailBean.A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.y.put(crashDetailBean.A, a);
            crashDetailBean.H = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.K();
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            crashDetailBean.P = this.d.H();
            crashDetailBean.Q = this.d.I();
            crashDetailBean.R = this.d.B();
            crashDetailBean.S = this.d.G();
        } catch (Throwable th3) {
            an.e("handle crash error %s", new Object[]{th3.toString()});
        }
        if (z) {
            this.b.c(crashDetailBean);
        } else {
            Object obj = (str == null || str.length() <= 0) ? null : 1;
            a = (bArr == null || bArr.length <= 0) ? null : 1;
            if (obj != null) {
                crashDetailBean.O = new HashMap(1);
                crashDetailBean.O.put("UserData", str);
            }
            if (a != null) {
                crashDetailBean.T = bArr;
            }
        }
        return crashDetailBean;
    }

    private boolean a(Thread thread) {
        boolean z;
        synchronized (i) {
            if (h == null || !thread.getName().equals(h)) {
                h = thread.getName();
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    public void b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (z) {
            an.e("Java Crash Happen cause by %s(%d)", new Object[]{thread.getName(), Long.valueOf(thread.getId())});
            if (a(thread)) {
                an.a("this class has handled this exception", new Object[0]);
                if (this.f != null) {
                    an.a("call system handler", new Object[0]);
                    this.f.uncaughtException(thread, th);
                } else {
                    a(thread, th);
                }
            }
        } else {
            an.e("Java Catch Happen", new Object[0]);
        }
        try {
            if (this.g) {
                if (!this.c.b()) {
                    an.e("waiting for remote sync", new Object[0]);
                    int i = 0;
                    while (!this.c.b()) {
                        ap.b(500);
                        i += 500;
                        if (i >= 3000) {
                            break;
                        }
                    }
                }
                if (!this.c.b()) {
                    an.d("no remote but still store!", new Object[0]);
                }
                if (this.c.c().g || !this.c.b()) {
                    CrashDetailBean a = a(thread, th, z, str, bArr);
                    if (a == null) {
                        an.e("pkg crash datas fail!", new Object[0]);
                        if (!z) {
                            return;
                        }
                        if (this.e != null && a(this.e)) {
                            an.e("sys default last handle start!", new Object[0]);
                            this.e.uncaughtException(thread, th);
                            an.e("sys default last handle end!", new Object[0]);
                            return;
                        } else if (this.f != null) {
                            an.e("system handle start!", new Object[0]);
                            this.f.uncaughtException(thread, th);
                            an.e("system handle end!", new Object[0]);
                            return;
                        } else {
                            an.e("crashreport last handle start!", new Object[0]);
                            a(thread, th);
                            an.e("crashreport last handle end!", new Object[0]);
                            return;
                        }
                    }
                    b.a(z ? "JAVA_CRASH" : "JAVA_CATCH", ap.a(), this.d.e, thread, ap.a(th), a);
                    if (!this.b.a(a)) {
                        this.b.a(a, 3000, z);
                    }
                    this.b.b(a);
                    if (!z) {
                        return;
                    }
                    if (this.e != null && a(this.e)) {
                        an.e("sys default last handle start!", new Object[0]);
                        this.e.uncaughtException(thread, th);
                        an.e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f != null) {
                        an.e("system handle start!", new Object[0]);
                        this.f.uncaughtException(thread, th);
                        an.e("system handle end!", new Object[0]);
                        return;
                    } else {
                        an.e("crashreport last handle start!", new Object[0]);
                        a(thread, th);
                        an.e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                an.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                b.a(z ? "JAVA_CRASH" : "JAVA_CATCH", ap.a(), this.d.e, thread, ap.a(th), null);
                if (!z) {
                    return;
                }
                if (this.e != null && a(this.e)) {
                    an.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    an.e("sys default last handle end!", new Object[0]);
                    return;
                } else if (this.f != null) {
                    an.e("system handle start!", new Object[0]);
                    this.f.uncaughtException(thread, th);
                    an.e("system handle end!", new Object[0]);
                    return;
                } else {
                    an.e("crashreport last handle start!", new Object[0]);
                    a(thread, th);
                    an.e("crashreport last handle end!", new Object[0]);
                    return;
                }
            }
            an.c("Java crash handler is disable. Just return.", new Object[0]);
            if (!z) {
                return;
            }
            if (this.e != null && a(this.e)) {
                an.e("sys default last handle start!", new Object[0]);
                this.e.uncaughtException(thread, th);
                an.e("sys default last handle end!", new Object[0]);
            } else if (this.f != null) {
                an.e("system handle start!", new Object[0]);
                this.f.uncaughtException(thread, th);
                an.e("system handle end!", new Object[0]);
            } else {
                an.e("crashreport last handle start!", new Object[0]);
                a(thread, th);
                an.e("crashreport last handle end!", new Object[0]);
            }
        } catch (Throwable th2) {
            if (z) {
                if (this.e != null && a(this.e)) {
                    an.e("sys default last handle start!", new Object[0]);
                    this.e.uncaughtException(thread, th);
                    an.e("sys default last handle end!", new Object[0]);
                } else if (this.f != null) {
                    an.e("system handle start!", new Object[0]);
                    this.f.uncaughtException(thread, th);
                    an.e("system handle end!", new Object[0]);
                } else {
                    an.e("crashreport last handle start!", new Object[0]);
                    a(thread, th);
                    an.e("crashreport last handle end!", new Object[0]);
                }
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        synchronized (i) {
            b(thread, th, true, null, null);
        }
    }

    private boolean a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        String str = "uncaughtException";
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && str.equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.g != this.g) {
                an.a("java changed to %b", new Object[]{Boolean.valueOf(strategyBean.g)});
                if (strategyBean.g) {
                    a();
                } else {
                    b();
                }
            }
        }
    }

    public static String a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i <= 0 || stringBuilder.length() < i) {
                        stringBuilder.append(stackTraceElement.toString()).append("\n");
                        i2++;
                    } else {
                        stringBuilder.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return stringBuilder.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            an.e("gen stack error %s", new Object[]{th2.toString()});
        }
        return stringBuilder.toString();
    }

    public static String b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (i < 0 || th.getMessage().length() <= i) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, i) + "\n[Message over limit size:" + i + ", has been cutted!]";
    }
}
