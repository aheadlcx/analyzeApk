package com.crashlytics.android;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.C0009ap;
import com.crashlytics.android.internal.C0010aq;
import com.crashlytics.android.internal.aR;
import com.crashlytics.android.internal.ah;
import com.crashlytics.android.internal.ai;
import com.crashlytics.android.internal.ao;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.analytics.pro.b;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ba implements UncaughtExceptionHandler {
    static final FilenameFilter a = new bb();
    private static Comparator<File> b = new h();
    private static Comparator<File> c = new j();
    private static final Pattern d = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final Map<String, String> e = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    private static final ai f = ai.a("0");
    private final AtomicInteger g;
    private final AtomicBoolean h;
    private final int i;
    private final UncaughtExceptionHandler j;
    private final File k;
    private final File l;
    private final AtomicBoolean m;
    private final String n;
    private final BroadcastReceiver o;
    private final BroadcastReceiver p;
    private final ai q;
    private final ai r;
    private final ExecutorService s;
    private RunningAppProcessInfo t;
    private C0010aq u;
    private boolean v;
    private Thread[] w;
    private List<StackTraceElement[]> x;
    private StackTraceElement[] y;

    static /* synthetic */ void a(ba baVar, Date date, Thread thread, Throwable th) throws Exception {
        Closeable akVar;
        Throwable e;
        Flushable flushable = null;
        try {
            Closeable closeable;
            new File(baVar.k, "crash_marker").createNewFile();
            String n = baVar.n();
            if (n != null) {
                Crashlytics.b(n);
                akVar = new ak(baVar.k, n + "SessionCrash");
                try {
                    flushable = am.a((OutputStream) akVar);
                    baVar.a(date, (am) flushable, thread, th, "crash", true);
                    closeable = akVar;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        v.a().b().a(Crashlytics.TAG, "An error occurred in the fatal exception logger", e);
                        baVar.a(e, (OutputStream) akVar);
                        C0003ab.a(flushable, "Failed to flush to session begin file.");
                        C0003ab.a(akVar, "Failed to close fatal exception file output stream.");
                        baVar.m();
                        baVar.l();
                        ag.a(baVar.k, a, 4, c);
                        if (Crashlytics.getInstance().j()) {
                            baVar.p();
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        C0003ab.a(flushable, "Failed to flush to session begin file.");
                        C0003ab.a(akVar, "Failed to close fatal exception file output stream.");
                        throw e;
                    }
                }
            }
            v.a().b().a(Crashlytics.TAG, "Tried to write a fatal exception while no session was open.", null);
            closeable = null;
            C0003ab.a(flushable, "Failed to flush to session begin file.");
            C0003ab.a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            akVar = null;
            v.a().b().a(Crashlytics.TAG, "An error occurred in the fatal exception logger", e);
            baVar.a(e, (OutputStream) akVar);
            C0003ab.a(flushable, "Failed to flush to session begin file.");
            C0003ab.a(akVar, "Failed to close fatal exception file output stream.");
            baVar.m();
            baVar.l();
            ag.a(baVar.k, a, 4, c);
            if (Crashlytics.getInstance().j()) {
                baVar.p();
            }
        } catch (Throwable th3) {
            e = th3;
            akVar = null;
            C0003ab.a(flushable, "Failed to flush to session begin file.");
            C0003ab.a(akVar, "Failed to close fatal exception file output stream.");
            throw e;
        }
        baVar.m();
        baVar.l();
        ag.a(baVar.k, a, 4, c);
        if (Crashlytics.getInstance().j()) {
            baVar.p();
        }
    }

    static /* synthetic */ void b(ba baVar, Date date, Thread thread, Throwable th) {
        Throwable e;
        Closeable closeable;
        Flushable flushable = null;
        String n = baVar.n();
        if (n != null) {
            Crashlytics.a(n);
            try {
                v.a().b().a(Crashlytics.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
                Closeable akVar = new ak(baVar.k, n + "SessionEvent" + C0003ab.a(baVar.g.getAndIncrement()));
                try {
                    flushable = am.a((OutputStream) akVar);
                    baVar.a(date, (am) flushable, thread, th, b.J, false);
                    C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                    C0003ab.a(akVar, "Failed to close non-fatal file output stream.");
                } catch (Exception e2) {
                    e = e2;
                    closeable = akVar;
                    try {
                        v.a().b().a(Crashlytics.TAG, "An error occurred in the non-fatal exception logger", e);
                        baVar.a(e, (OutputStream) closeable);
                        C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                        C0003ab.a(closeable, "Failed to close non-fatal file output stream.");
                        baVar.a(n, 64);
                        return;
                    } catch (Throwable th2) {
                        e = th2;
                        C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                        C0003ab.a(closeable, "Failed to close non-fatal file output stream.");
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    closeable = akVar;
                    C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                    C0003ab.a(closeable, "Failed to close non-fatal file output stream.");
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                closeable = null;
                v.a().b().a(Crashlytics.TAG, "An error occurred in the non-fatal exception logger", e);
                baVar.a(e, (OutputStream) closeable);
                C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                C0003ab.a(closeable, "Failed to close non-fatal file output stream.");
                baVar.a(n, 64);
                return;
            } catch (Throwable th4) {
                e = th4;
                closeable = null;
                C0003ab.a(flushable, "Failed to flush to non-fatal file.");
                C0003ab.a(closeable, "Failed to close non-fatal file output stream.");
                throw e;
            }
            try {
                baVar.a(n, 64);
                return;
            } catch (Throwable e4) {
                v.a().b().a(Crashlytics.TAG, "An error occurred when trimming non-fatal files.", e4);
                return;
            }
        }
        v.a().b().a(Crashlytics.TAG, "Tried to write a non-fatal exception while no session was open.", null);
    }

    static {
        k kVar = new k();
    }

    public ba(UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, String str) {
        this(uncaughtExceptionHandler, crashlyticsListener, ah.a("Crashlytics Exception Handler"), str);
    }

    private ba(UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, ExecutorService executorService, String str) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicBoolean(false);
        this.j = uncaughtExceptionHandler;
        this.s = executorService;
        this.m = new AtomicBoolean(false);
        this.k = v.a().h();
        this.l = new File(this.k, "initialization_marker");
        this.n = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{Crashlytics.getInstance().getVersion()});
        this.i = 8;
        v.a().b().a(Crashlytics.TAG, "Checking for previous crash marker.");
        File file = new File(v.a().h(), "crash_marker");
        if (file.exists()) {
            file.delete();
            if (crashlyticsListener != null) {
                try {
                    crashlyticsListener.crashlyticsDidDetectCrashDuringPreviousExecution();
                } catch (Throwable e) {
                    v.a().b().a(Crashlytics.TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
                }
            }
        }
        this.q = ai.a(Crashlytics.d());
        this.r = str == null ? null : ai.a(str.replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, ""));
        this.p = new l(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.o = new m(this);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        Crashlytics.getInstance().getContext().registerReceiver(this.p, intentFilter);
        Crashlytics.getInstance().getContext().registerReceiver(this.o, intentFilter2);
        this.h.set(true);
    }

    final boolean a() {
        return this.m.get();
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.m.set(true);
        try {
            v.a().b().a(Crashlytics.TAG, "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
            if (!this.h.getAndSet(true)) {
                v.a().b().a(Crashlytics.TAG, "Unregistering power receivers.");
                Crashlytics.getInstance().getContext().unregisterReceiver(this.p);
                Crashlytics.getInstance().getContext().unregisterReceiver(this.o);
            }
            a(new n(this, new Date(), thread, th));
            v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, th);
            this.m.set(false);
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "An error occurred in the uncaught exception handler", e);
            v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, th);
            this.m.set(false);
        } catch (Throwable th2) {
            v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, th);
            this.m.set(false);
        }
    }

    final boolean b() {
        return ((Boolean) a(new o(this))).booleanValue();
    }

    final void a(Thread thread, Throwable th) {
        a(new p(this, new Date(), thread, th));
    }

    final void a(long j, String str) {
        b(new bc(this, j, str));
    }

    private ai a(C0010aq c0010aq) {
        if (c0010aq == null) {
            return null;
        }
        int[] iArr = new int[]{0};
        byte[] bArr = new byte[c0010aq.a()];
        try {
            c0010aq.a(new bd(this, bArr, iArr));
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "A problem occurred while reading the Crashlytics log file.", e);
        }
        return ai.a(bArr, 0, iArr[0]);
    }

    final void c() {
        b(new be(this));
    }

    final void d() {
        b(new a(this));
    }

    final void e() {
        b(new b(this));
    }

    final boolean f() {
        return ((Boolean) a(new c(this))).booleanValue();
    }

    static void a(C0010aq c0010aq, int i, long j, String str) {
        if (c0010aq != null) {
            String str2;
            if (str == null) {
                str2 = "null";
            } else {
                str2 = str;
            }
            try {
                if (str2.length() > 16384) {
                    str2 = "..." + str2.substring(str2.length() - 16384);
                }
                str2 = str2.replaceAll("\r", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replaceAll("\n", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                c0010aq.a(String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(j), str2}).getBytes("UTF-8"));
                while (!c0010aq.b() && c0010aq.a() > 65536) {
                    c0010aq.c();
                }
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "There was a problem writing to the Crashlytics log.", e);
            }
        }
    }

    final boolean g() {
        return o().length > 0;
    }

    private boolean k() {
        Throwable e;
        if (C0003ab.a(Crashlytics.getInstance().getContext(), "com.crashlytics.CollectCustomLogs", true)) {
            C0003ab.a(this.u, "Could not close log file: " + this.u);
            Object file;
            try {
                file = new File(v.a().h(), "crashlytics-userlog-" + UUID.randomUUID().toString() + ".temp");
                try {
                    this.u = new C0010aq(file);
                    file.delete();
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    v.a().b().a(Crashlytics.TAG, "Could not create log file: " + file, e);
                    return false;
                }
            } catch (Exception e3) {
                e = e3;
                file = null;
                v.a().b().a(Crashlytics.TAG, "Could not create log file: " + file, e);
                return false;
            }
        }
        v.a().b().a(Crashlytics.TAG, "Preferences requested not to collect custom logs. Aborting log file creation.");
        return false;
    }

    private void l() throws Exception {
        Throwable e;
        Closeable closeable;
        OutputStream outputStream;
        Closeable closeable2;
        Flushable flushable = null;
        Date date = new Date();
        String ajVar = new aj(Crashlytics.getInstance().b()).toString();
        v.a().b().a(Crashlytics.TAG, "Opening an new session with ID " + ajVar);
        Closeable akVar;
        Flushable a;
        try {
            akVar = new ak(v.a().h(), ajVar + "BeginSession");
            try {
                a = am.a((OutputStream) akVar);
                try {
                    a.a(1, ai.a(this.n));
                    a.a(2, ai.a(ajVar));
                    a.a(3, date.getTime() / 1000);
                    C0003ab.a(a, "Failed to flush to session begin file.");
                    C0003ab.a(akVar, "Failed to close begin session file.");
                    try {
                        akVar = new ak(v.a().h(), ajVar + "SessionApp");
                        try {
                            a = am.a((OutputStream) akVar);
                        } catch (Exception e2) {
                            e = e2;
                            closeable = akVar;
                            try {
                                a(e, outputStream);
                                throw e;
                            } catch (Throwable th) {
                                e = th;
                                akVar = outputStream;
                                a = flushable;
                                C0003ab.a(a, "Failed to flush to session app file.");
                                C0003ab.a(akVar, "Failed to close session app file.");
                                throw e;
                            }
                        } catch (Throwable th2) {
                            e = th2;
                            a = null;
                            C0003ab.a(a, "Failed to flush to session app file.");
                            C0003ab.a(akVar, "Failed to close session app file.");
                            throw e;
                        }
                        try {
                            ai a2 = ai.a(Crashlytics.d());
                            ai a3 = ai.a(Crashlytics.g());
                            ai a4 = ai.a(Crashlytics.f());
                            ai.a(Crashlytics.h());
                            ai.a(Crashlytics.getInstance().getContext().getPackageCodePath());
                            ai a5 = ai.a(Crashlytics.getInstance().b().b());
                            int a6 = ai.a(Crashlytics.e()).a();
                            a.g(7, 2);
                            int b = ((am.b(1, a2) + 0) + am.b(2, a3)) + am.b(3, a4);
                            int q = q();
                            a.b(((b + (q + (am.a(5) + am.c(q)))) + am.b(6, a5)) + am.e(10, a6));
                            a.a(1, a2);
                            a.a(2, a3);
                            a.a(3, a4);
                            a.g(5, 2);
                            a.b(q());
                            a.a(1, r.a(Crashlytics.getInstance().getContext(), false));
                            a.a(6, a5);
                            a.b(10, a6);
                            C0003ab.a(a, "Failed to flush to session app file.");
                            C0003ab.a(akVar, "Failed to close session app file.");
                            try {
                                closeable = new ak(v.a().h(), ajVar + "SessionOS");
                                try {
                                    flushable = am.a((OutputStream) closeable);
                                    a2 = ai.a(VERSION.RELEASE);
                                    ai a7 = ai.a(VERSION.CODENAME);
                                    boolean e3 = C0003ab.e();
                                    flushable.g(8, 2);
                                    flushable.b((((am.e(1, 3) + 0) + am.b(2, a2)) + am.b(3, a7)) + am.b(4, e3));
                                    flushable.b(1, 3);
                                    flushable.a(2, a2);
                                    flushable.a(3, a7);
                                    flushable.a(4, e3);
                                    C0003ab.a(flushable, "Failed to flush to session OS file.");
                                    C0003ab.a(closeable, "Failed to close session OS file.");
                                    c(ajVar);
                                } catch (Exception e4) {
                                    e = e4;
                                    try {
                                        a(e, (OutputStream) closeable);
                                        throw e;
                                    } catch (Throwable th3) {
                                        e = th3;
                                        C0003ab.a(flushable, "Failed to flush to session OS file.");
                                        C0003ab.a(closeable, "Failed to close session OS file.");
                                        throw e;
                                    }
                                }
                            } catch (Exception e5) {
                                e = e5;
                                closeable = null;
                                a(e, (OutputStream) closeable);
                                throw e;
                            } catch (Throwable th4) {
                                e = th4;
                                closeable = null;
                                C0003ab.a(flushable, "Failed to flush to session OS file.");
                                C0003ab.a(closeable, "Failed to close session OS file.");
                                throw e;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            flushable = a;
                            closeable = akVar;
                            a(e, outputStream);
                            throw e;
                        } catch (Throwable th5) {
                            e = th5;
                            C0003ab.a(a, "Failed to flush to session app file.");
                            C0003ab.a(akVar, "Failed to close session app file.");
                            throw e;
                        }
                    } catch (Exception e7) {
                        e = e7;
                        outputStream = null;
                        a(e, outputStream);
                        throw e;
                    } catch (Throwable th6) {
                        e = th6;
                        a = null;
                        akVar = null;
                        C0003ab.a(a, "Failed to flush to session app file.");
                        C0003ab.a(akVar, "Failed to close session app file.");
                        throw e;
                    }
                } catch (Exception e8) {
                    e = e8;
                    closeable2 = akVar;
                    try {
                        a(e, r2);
                        throw e;
                    } catch (Throwable th7) {
                        e = th7;
                        akVar = r2;
                        flushable = a;
                        C0003ab.a(flushable, "Failed to flush to session begin file.");
                        C0003ab.a(akVar, "Failed to close begin session file.");
                        throw e;
                    }
                } catch (Throwable th8) {
                    e = th8;
                    flushable = a;
                    C0003ab.a(flushable, "Failed to flush to session begin file.");
                    C0003ab.a(akVar, "Failed to close begin session file.");
                    throw e;
                }
            } catch (Exception e9) {
                e = e9;
                a = null;
                closeable2 = akVar;
                a(e, r2);
                throw e;
            } catch (Throwable th9) {
                e = th9;
                C0003ab.a(flushable, "Failed to flush to session begin file.");
                C0003ab.a(akVar, "Failed to close begin session file.");
                throw e;
            }
        } catch (Exception e10) {
            e = e10;
            a = null;
            a(e, r2);
            throw e;
        } catch (Throwable th10) {
            e = th10;
            akVar = null;
            C0003ab.a(flushable, "Failed to flush to session begin file.");
            C0003ab.a(akVar, "Failed to close begin session file.");
            throw e;
        }
    }

    private void m() throws Exception {
        int i;
        Closeable akVar;
        Flushable flushable;
        Throwable e;
        ak akVar2;
        Set hashSet = new HashSet();
        File[] o = o();
        Arrays.sort(o, b);
        int min = Math.min(8, o.length);
        for (i = 0; i < min; i++) {
            hashSet.add(a(o[i]));
        }
        for (File file : a(new q())) {
            Object name = file.getName();
            Matcher matcher = d.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(1))) {
                v.a().b().a(Crashlytics.TAG, "Trimming open session file: " + name);
                file.delete();
            }
        }
        String n = n();
        Closeable akVar3;
        if (n != null) {
            Flushable flushable2 = null;
            try {
                akVar = new ak(this.k, n + "SessionUser");
                try {
                    flushable2 = am.a((OutputStream) akVar);
                    n = Crashlytics.getInstance().m();
                    String o2 = Crashlytics.getInstance().o();
                    String n2 = Crashlytics.getInstance().n();
                    if (n == null && o2 == null && n2 == null) {
                        C0003ab.a(flushable2, "Failed to flush session user file.");
                        C0003ab.a(akVar, "Failed to close session user file.");
                    } else {
                        if (n == null) {
                            n = "";
                        }
                        ai a = ai.a(n);
                        ai a2 = o2 == null ? null : ai.a(o2);
                        ai a3 = n2 == null ? null : ai.a(n2);
                        i = am.b(1, a) + 0;
                        if (a2 != null) {
                            i += am.b(2, a2);
                        }
                        if (a3 != null) {
                            i += am.b(3, a3);
                        }
                        flushable2.g(6, 2);
                        flushable2.b(i);
                        flushable2.a(1, a);
                        if (a2 != null) {
                            flushable2.a(2, a2);
                        }
                        if (a3 != null) {
                            flushable2.a(3, a3);
                        }
                        C0003ab.a(flushable2, "Failed to flush session user file.");
                        C0003ab.a(akVar, "Failed to close session user file.");
                    }
                    aR r = Crashlytics.getInstance().r();
                    if (r != null) {
                        int i2 = r.a;
                        v.a().b().a(Crashlytics.TAG, "Closing all open sessions.");
                        File[] o3 = o();
                        if (o3 != null && o3.length > 0) {
                            for (File file2 : o3) {
                                String a4 = a(file2);
                                v.a().b().a(Crashlytics.TAG, "Closing session: " + a4);
                                v.a().b().a(Crashlytics.TAG, "Collecting session parts for ID " + a4);
                                File[] a5 = a(new r(a4 + "SessionCrash"));
                                boolean z = a5 != null && a5.length > 0;
                                v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{a4, Boolean.valueOf(z)}));
                                File[] a6 = a(new r(a4 + "SessionEvent"));
                                boolean z2 = a6 != null && a6.length > 0;
                                v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{a4, Boolean.valueOf(z2)}));
                                if (z || z2) {
                                    flushable = null;
                                    try {
                                        akVar3 = new ak(this.k, a4);
                                        try {
                                            flushable = am.a((OutputStream) akVar3);
                                        } catch (Exception e2) {
                                            e = e2;
                                            flushable2 = flushable;
                                            akVar = akVar3;
                                            try {
                                                v.a().b().a(Crashlytics.TAG, "Failed to write session file for session ID: " + a4, e);
                                                a(e, (OutputStream) akVar2);
                                                C0003ab.a(flushable2, "Error flushing session file stream");
                                                a(akVar2);
                                                v.a().b().a(Crashlytics.TAG, "Removing session part files for ID " + a4);
                                                a(a4);
                                            } catch (Throwable th) {
                                                e = th;
                                                Object obj = akVar2;
                                                flushable = flushable2;
                                            }
                                        } catch (Throwable th2) {
                                            e = th2;
                                        }
                                        try {
                                            v.a().b().a(Crashlytics.TAG, "Collecting SessionStart data for session ID " + a4);
                                            a((am) flushable, file2);
                                            flushable.a(4, new Date().getTime() / 1000);
                                            flushable.a(5, z);
                                            a((am) flushable, a4);
                                            if (z2) {
                                                File[] a7;
                                                if (a6.length > i2) {
                                                    v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i2)}));
                                                    a(a4, i2);
                                                    a7 = a(new r(a4 + "SessionEvent"));
                                                } else {
                                                    a7 = a6;
                                                }
                                                a((am) flushable, a7, a4);
                                            }
                                            if (z) {
                                                a((am) flushable, a5[0]);
                                            }
                                            flushable.a(11, 1);
                                            flushable.b(12, 3);
                                            C0003ab.a(flushable, "Error flushing session file stream");
                                            C0003ab.a(akVar3, "Failed to close CLS file");
                                        } catch (Exception e3) {
                                            e = e3;
                                            flushable2 = flushable;
                                            akVar = akVar3;
                                            v.a().b().a(Crashlytics.TAG, "Failed to write session file for session ID: " + a4, e);
                                            a(e, (OutputStream) akVar2);
                                            C0003ab.a(flushable2, "Error flushing session file stream");
                                            a(akVar2);
                                            v.a().b().a(Crashlytics.TAG, "Removing session part files for ID " + a4);
                                            a(a4);
                                        } catch (Throwable th22) {
                                            e = th22;
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                        flushable2 = flushable;
                                        akVar2 = null;
                                        v.a().b().a(Crashlytics.TAG, "Failed to write session file for session ID: " + a4, e);
                                        a(e, (OutputStream) akVar2);
                                        C0003ab.a(flushable2, "Error flushing session file stream");
                                        a(akVar2);
                                        v.a().b().a(Crashlytics.TAG, "Removing session part files for ID " + a4);
                                        a(a4);
                                    } catch (Throwable th3) {
                                        e = th3;
                                        akVar3 = null;
                                    }
                                } else {
                                    v.a().b().a(Crashlytics.TAG, "No events present for session ID " + a4);
                                }
                                v.a().b().a(Crashlytics.TAG, "Removing session part files for ID " + a4);
                                a(a4);
                            }
                            return;
                        }
                        return;
                    }
                    v.a().b().a(Crashlytics.TAG, "No session begin files found.");
                    return;
                } catch (Exception e5) {
                    e = e5;
                    try {
                        a(e, (OutputStream) akVar);
                        throw e;
                    } catch (Throwable th4) {
                        e = th4;
                        C0003ab.a(flushable2, "Failed to flush session user file.");
                        C0003ab.a(akVar, "Failed to close session user file.");
                        throw e;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                akVar = null;
                a(e, (OutputStream) akVar);
                throw e;
            } catch (Throwable th5) {
                e = th5;
                akVar = null;
                C0003ab.a(flushable2, "Failed to flush session user file.");
                C0003ab.a(akVar, "Failed to close session user file.");
                throw e;
            }
        }
        v.a().b().a(Crashlytics.TAG, "Unable to close session. Settings are not loaded.");
        return;
        C0003ab.a(flushable, "Error flushing session file stream");
        C0003ab.a(akVar3, "Failed to close CLS file");
        throw e;
    }

    private String n() {
        File[] a = a(new r("BeginSession"));
        Arrays.sort(a, b);
        return a.length > 0 ? a(a[0]) : null;
    }

    private static String a(File file) {
        return file.getName().substring(0, 35);
    }

    private static void a(ak akVar) {
        if (akVar != null) {
            try {
                akVar.a();
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void a(am amVar, File[] fileArr, String str) {
        Arrays.sort(fileArr, C0003ab.a);
        for (File name : fileArr) {
            try {
                v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, name.getName()}));
                a(amVar, name);
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void a(am amVar, String str) throws IOException {
        for (String str2 : new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"}) {
            File[] a = a(new r(str + str2));
            if (a.length == 0) {
                v.a().b().a(Crashlytics.TAG, "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                v.a().b().a(Crashlytics.TAG, "Collecting " + str2 + " data for session ID " + str);
                a(amVar, a[0]);
            }
        }
    }

    private void a(String str) {
        for (File delete : a(new s(str))) {
            delete.delete();
        }
    }

    private File[] o() {
        return a(new r("BeginSession"));
    }

    private File[] a(FilenameFilter filenameFilter) {
        File[] listFiles = this.k.listFiles(filenameFilter);
        return listFiles == null ? new File[0] : listFiles;
    }

    private void p() {
        for (File file : a(a)) {
            v.a().b().a(Crashlytics.TAG, "Attempting to send crash report at time of crash...");
            new Thread(new d(this, file), "Crashlytics Report Uploader").start();
        }
    }

    private void a(Throwable th, OutputStream outputStream) {
        Closeable printWriter;
        Throwable e;
        if (outputStream != null) {
            try {
                printWriter = new PrintWriter(outputStream);
                try {
                    a(th, (Writer) printWriter);
                    C0003ab.a(printWriter, "Failed to close stack trace writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        v.a().b().a(Crashlytics.TAG, "Failed to create PrintWriter", e);
                        C0003ab.a(printWriter, "Failed to close stack trace writer.");
                    } catch (Throwable th2) {
                        e = th2;
                        C0003ab.a(printWriter, "Failed to close stack trace writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                printWriter = null;
                v.a().b().a(Crashlytics.TAG, "Failed to create PrintWriter", e);
                C0003ab.a(printWriter, "Failed to close stack trace writer.");
            } catch (Throwable th3) {
                e = th3;
                printWriter = null;
                C0003ab.a(printWriter, "Failed to close stack trace writer.");
                throw e;
            }
        }
    }

    private static void a(Throwable th, Writer writer) {
        Object obj = 1;
        while (th != null) {
            try {
                String localizedMessage = th.getLocalizedMessage();
                localizedMessage = localizedMessage == null ? null : localizedMessage.replaceAll("(\r\n|\n|\f)", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                writer.write((obj != null ? "" : "Caused by: ") + th.getClass().getName() + ": " + (localizedMessage != null ? localizedMessage : "") + "\n");
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    writer.write("\tat " + stackTraceElement.toString() + "\n");
                }
                th = th.getCause();
                obj = null;
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Could not write stack trace", e);
                return;
            }
        }
    }

    private static int q() {
        return am.b(1, ai.a(r.a(Crashlytics.getInstance().getContext(), v.a().f()))) + 0;
    }

    private static ai b(String str) {
        return str == null ? null : ai.a(str);
    }

    private void c(String str) throws Exception {
        am a;
        Throwable e;
        OutputStream outputStream;
        OutputStream outputStream2 = null;
        am amVar = null;
        try {
            OutputStream akVar = new ak(v.a().h(), str + "SessionDevice");
            try {
                a = am.a(akVar);
            } catch (Exception e2) {
                e = e2;
                outputStream2 = akVar;
                try {
                    a(e, outputStream2);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    a = amVar;
                    outputStream = outputStream2;
                    C0003ab.a((Flushable) a, "Failed to flush session device info.");
                    C0003ab.a((Closeable) outputStream, "Failed to close session device file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                a = null;
                C0003ab.a((Flushable) a, "Failed to flush session device info.");
                C0003ab.a((Closeable) outputStream, "Failed to close session device file.");
                throw e;
            }
            try {
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                int b = C0003ab.b();
                ai b2 = b(Build.MODEL);
                ai b3 = b(Build.MANUFACTURER);
                ai b4 = b(Build.PRODUCT);
                int availableProcessors = Runtime.getRuntime().availableProcessors();
                long c = C0003ab.c();
                long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
                boolean d = C0003ab.d();
                ao b5 = Crashlytics.getInstance().b();
                ai a2 = ai.a(b5.e());
                Map f = b5.f();
                int f2 = C0003ab.f();
                a.g(9, 2);
                a.b(a(b, a2, b2, availableProcessors, c, blockCount, d, f, f2, b3, b4));
                a.a(1, a2);
                a.b(3, b);
                a.a(4, b2);
                a.a(5, availableProcessors);
                a.a(6, c);
                a.a(7, blockCount);
                a.a(10, d);
                for (Entry entry : f.entrySet()) {
                    a.g(11, 2);
                    a.b(a((C0009ap) entry.getKey(), (String) entry.getValue()));
                    a.b(1, ((C0009ap) entry.getKey()).f);
                    a.a(2, ai.a((String) entry.getValue()));
                }
                a.a(12, f2);
                if (b3 != null) {
                    a.a(13, b3);
                }
                if (b4 != null) {
                    a.a(14, b4);
                }
                C0003ab.a((Flushable) a, "Failed to flush session device info.");
                C0003ab.a((Closeable) akVar, "Failed to close session device file.");
            } catch (Exception e3) {
                e = e3;
                amVar = a;
                outputStream2 = akVar;
                a(e, outputStream2);
                throw e;
            } catch (Throwable th3) {
                e = th3;
                C0003ab.a((Flushable) a, "Failed to flush session device info.");
                C0003ab.a((Closeable) outputStream, "Failed to close session device file.");
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            a(e, outputStream2);
            throw e;
        } catch (Throwable th4) {
            e = th4;
            a = null;
            outputStream = null;
            C0003ab.a((Flushable) a, "Failed to flush session device info.");
            C0003ab.a((Closeable) outputStream, "Failed to close session device file.");
            throw e;
        }
    }

    private static int a(C0009ap c0009ap, String str) {
        return am.e(1, c0009ap.f) + am.b(2, ai.a(str));
    }

    private int a(int i, ai aiVar, ai aiVar2, int i2, long j, long j2, boolean z, Map<C0009ap, String> map, int i3, ai aiVar3, ai aiVar4) {
        int i4;
        int i5;
        int e = am.e(3, i) + (am.b(1, aiVar) + 0);
        if (aiVar2 == null) {
            i4 = 0;
        } else {
            i4 = am.b(4, aiVar2);
        }
        i4 = ((((i4 + e) + am.d(5, i2)) + am.b(6, j)) + am.b(7, j2)) + am.b(10, z);
        if (map != null) {
            i5 = i4;
            for (Entry entry : map.entrySet()) {
                i4 = a((C0009ap) entry.getKey(), (String) entry.getValue());
                i5 = (i4 + (am.a(11) + am.c(i4))) + i5;
            }
        } else {
            i5 = i4;
        }
        return (aiVar4 == null ? 0 : am.b(14, aiVar4)) + ((i5 + am.d(12, i3)) + (aiVar3 == null ? 0 : am.b(13, aiVar3)));
    }

    private static void a(am amVar, File file) throws IOException {
        Throwable th;
        if (file.exists()) {
            byte[] bArr = new byte[((int) file.length())];
            Closeable fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                int i = 0;
                while (i < bArr.length) {
                    try {
                        int read = fileInputStream.read(bArr, i, bArr.length - i);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                C0003ab.a(fileInputStream, "Failed to close file input stream.");
                amVar.a(bArr);
                return;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                C0003ab.a(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        }
        v.a().b().a(Crashlytics.TAG, "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private void a(String str, int i) {
        ag.a(this.k, new r(str + "SessionEvent"), i, c);
    }

    private void a(Date date, am amVar, Thread thread, Throwable th, String str, boolean z) throws Exception {
        Map treeMap;
        long time = date.getTime() / 1000;
        float b = C0003ab.b(Crashlytics.getInstance().getContext());
        int a = C0003ab.a(this.v);
        boolean c = C0003ab.c(Crashlytics.getInstance().getContext());
        int i = Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation;
        long c2 = C0003ab.c() - C0003ab.a(Crashlytics.getInstance().getContext());
        long b2 = C0003ab.b(Environment.getDataDirectory().getPath());
        this.t = C0003ab.a(Crashlytics.d(), Crashlytics.getInstance().getContext());
        this.x = new LinkedList();
        this.y = th.getStackTrace();
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            this.w = new Thread[allStackTraces.size()];
            int i2 = 0;
            for (Entry entry : allStackTraces.entrySet()) {
                this.w[i2] = (Thread) entry.getKey();
                this.x.add(entry.getValue());
                i2++;
            }
        } else {
            this.w = new Thread[0];
        }
        ai a2 = a(this.u);
        if (a2 == null) {
            v.a().b().a(Crashlytics.TAG, "No log data to include with this event.");
        }
        C0003ab.a(this.u, "There was a problem closing the Crashlytics log file.");
        this.u = null;
        if (C0003ab.a(Crashlytics.getInstance().getContext(), "com.crashlytics.CollectCustomKeys", true)) {
            allStackTraces = Crashlytics.getInstance().a();
            treeMap = (allStackTraces == null || allStackTraces.size() <= 1) ? allStackTraces : new TreeMap(allStackTraces);
        } else {
            treeMap = new TreeMap();
        }
        amVar.g(10, 2);
        int b3 = (am.b(1, time) + 0) + am.b(2, ai.a(str));
        int a3 = a(thread, th, treeMap);
        b3 += a3 + (am.a(3) + am.c(a3));
        a3 = a(b, a, c, i, c2, b2);
        b3 += a3 + (am.a(5) + am.c(a3));
        if (a2 != null) {
            a3 = am.b(1, a2);
            b3 += a3 + (am.a(6) + am.c(a3));
        }
        amVar.b(b3);
        amVar.a(1, time);
        amVar.a(2, ai.a(str));
        amVar.g(3, 2);
        amVar.b(a(thread, th, treeMap));
        a(amVar, thread, th);
        if (!(treeMap == null || treeMap.isEmpty())) {
            a(amVar, treeMap);
        }
        if (this.t != null) {
            amVar.a(3, this.t.importance != 100);
        }
        amVar.a(4, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation);
        amVar.g(5, 2);
        amVar.b(a(b, a, c, i, c2, b2));
        amVar.a(1, b);
        amVar.c(2, a);
        amVar.a(3, c);
        amVar.a(4, i);
        amVar.a(5, c2);
        amVar.a(6, b2);
        if (a2 != null) {
            amVar.g(6, 2);
            amVar.b(am.b(1, a2));
            amVar.a(1, a2);
        }
    }

    private void a(am amVar, Thread thread, Throwable th) throws Exception {
        amVar.g(1, 2);
        amVar.b(b(thread, th));
        a(amVar, thread, this.y, 4, true);
        int length = this.w.length;
        for (int i = 0; i < length; i++) {
            a(amVar, this.w[i], (StackTraceElement[]) this.x.get(i), 0, false);
        }
        a(amVar, th, 1, 2);
        amVar.g(3, 2);
        amVar.b(s());
        amVar.a(1, f);
        amVar.a(2, f);
        amVar.a(3, 0);
        amVar.g(4, 2);
        amVar.b(r());
        amVar.a(1, 0);
        amVar.a(2, 0);
        amVar.a(3, this.q);
        if (this.r != null) {
            amVar.a(4, this.r);
        }
    }

    private void a(am amVar, Map<String, String> map) throws Exception {
        for (Entry entry : map.entrySet()) {
            amVar.g(2, 2);
            amVar.b(a((String) entry.getKey(), (String) entry.getValue()));
            amVar.a(1, ai.a((String) entry.getKey()));
            String str = (String) entry.getValue();
            if (str == null) {
                str = "";
            }
            amVar.a(2, ai.a(str));
        }
    }

    private int r() {
        int b = ((am.b(1, 0) + 0) + am.b(2, 0)) + am.b(3, this.q);
        if (this.r != null) {
            return b + am.b(4, this.r);
        }
        return b;
    }

    private void a(am amVar, Throwable th, int i, int i2) throws Exception {
        int i3 = 0;
        amVar.g(i2, 2);
        amVar.b(a(th, 1));
        amVar.a(1, ai.a(th.getClass().getName()));
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            amVar.a(3, ai.a(localizedMessage));
        }
        for (StackTraceElement a : th.getStackTrace()) {
            a(amVar, 4, a, true);
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return;
        }
        if (i < 8) {
            a(amVar, cause, i + 1, 6);
            return;
        }
        while (cause != null) {
            cause = cause.getCause();
            i3++;
        }
        amVar.a(7, i3);
    }

    private int a(Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        int d = am.d(2, i) + am.b(1, ai.a(thread.getName()));
        for (StackTraceElement a : stackTraceElementArr) {
            int a2 = a(a, z);
            d += a2 + (am.a(3) + am.c(a2));
        }
        return d;
    }

    private void a(am amVar, Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) throws Exception {
        amVar.g(1, 2);
        amVar.b(a(thread, stackTraceElementArr, i, z));
        amVar.a(1, ai.a(thread.getName()));
        amVar.a(2, i);
        for (StackTraceElement a : stackTraceElementArr) {
            a(amVar, 3, a, z);
        }
    }

    private static int a(StackTraceElement stackTraceElement, boolean z) {
        int b;
        int i;
        if (stackTraceElement.isNativeMethod()) {
            b = am.b(1, (long) Math.max(stackTraceElement.getLineNumber(), 0)) + 0;
        } else {
            b = am.b(1, 0) + 0;
        }
        b += am.b(2, ai.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            b += am.b(3, ai.a(stackTraceElement.getFileName()));
        }
        if (stackTraceElement.isNativeMethod() || stackTraceElement.getLineNumber() <= 0) {
            i = b;
        } else {
            i = b + am.b(4, (long) stackTraceElement.getLineNumber());
        }
        return am.d(5, z ? 2 : 0) + i;
    }

    private void a(am amVar, int i, StackTraceElement stackTraceElement, boolean z) throws Exception {
        int i2 = 4;
        amVar.g(i, 2);
        amVar.b(a(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            amVar.a(1, (long) Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            amVar.a(1, 0);
        }
        amVar.a(2, ai.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            amVar.a(3, ai.a(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            amVar.a(4, (long) stackTraceElement.getLineNumber());
        }
        if (!z) {
            i2 = 0;
        }
        amVar.a(5, i2);
    }

    private int a(Thread thread, Throwable th, Map<String, String> map) {
        int i;
        int b = b(thread, th);
        b = (b + (am.a(1) + am.c(b))) + 0;
        if (map != null) {
            i = b;
            for (Entry entry : map.entrySet()) {
                b = a((String) entry.getKey(), (String) entry.getValue());
                i = (b + (am.a(2) + am.c(b))) + i;
            }
        } else {
            i = b;
        }
        if (this.t != null) {
            i += am.b(3, this.t.importance != 100);
        }
        return am.d(4, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation) + i;
    }

    private int b(Thread thread, Throwable th) {
        int i;
        int a = a(thread, this.y, 4, true);
        a = (a + (am.a(1) + am.c(a))) + 0;
        int length = this.w.length;
        int i2 = a;
        for (i = 0; i < length; i++) {
            a = a(this.w[i], (StackTraceElement[]) this.x.get(i), 0, false);
            i2 += a + (am.a(1) + am.c(a));
        }
        a = a(th, 1);
        a = (a + (am.a(2) + am.c(a))) + i2;
        i = s();
        a += i + (am.a(3) + am.c(i));
        i = r();
        return a + (i + (am.a(3) + am.c(i)));
    }

    private static int a(String str, String str2) {
        int b = am.b(1, ai.a(str));
        if (str2 == null) {
            str2 = "";
        }
        return b + am.b(2, ai.a(str2));
    }

    private static int a(float f, int i, boolean z, int i2, long j, long j2) {
        return (((((am.b(1, f) + 0) + am.f(2, i)) + am.b(3, z)) + am.d(4, i2)) + am.b(5, j)) + am.b(6, j2);
    }

    private int a(Throwable th, int i) {
        int i2 = 0;
        int b = am.b(1, ai.a(th.getClass().getName())) + 0;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            b += am.b(3, ai.a(localizedMessage));
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i3 = 0;
        while (i3 < length) {
            int a = a(stackTrace[i3], true);
            i3++;
            b = (a + (am.a(4) + am.c(a))) + b;
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return b;
        }
        if (i < 8) {
            i2 = a(cause, i + 1);
            return b + (i2 + (am.a(6) + am.c(i2)));
        }
        while (cause != null) {
            cause = cause.getCause();
            i2++;
        }
        return b + am.d(7, i2);
    }

    private static int s() {
        return ((am.b(1, f) + 0) + am.b(2, f)) + am.b(3, 0);
    }

    final void h() {
        a(new e(this));
    }

    final void a(File[] fileArr) {
        File file = new File(v.a().h(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
            file.delete();
        }
        for (File file2 : fileArr) {
            v.a().b().a(Crashlytics.TAG, "Found invalid session part file: " + file2);
            String a = a(file2);
            FilenameFilter fVar = new f(this, a);
            v.a().b().a(Crashlytics.TAG, "Deleting all part files for invalid session: " + a);
            for (File file3 : a(fVar)) {
                v.a().b().a(Crashlytics.TAG, "Deleting session file: " + file3);
                file3.delete();
            }
        }
    }

    private <T> T a(Callable<T> callable) {
        T t = null;
        try {
            t = this.s.submit(callable).get();
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
        } catch (Throwable e2) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e2);
        }
        return t;
    }

    private Future<?> a(Runnable runnable) {
        try {
            return this.s.submit(new g(this, runnable));
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    private <T> Future<T> b(Callable<T> callable) {
        try {
            return this.s.submit(new i(this, callable));
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
