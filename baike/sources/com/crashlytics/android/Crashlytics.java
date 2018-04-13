package com.crashlytics.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.crashlytics.android.internal.A;
import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.C0006af;
import com.crashlytics.android.internal.C0007ag;
import com.crashlytics.android.internal.C0011av;
import com.crashlytics.android.internal.C0012ax;
import com.crashlytics.android.internal.C0013ay;
import com.crashlytics.android.internal.D;
import com.crashlytics.android.internal.aM;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.aR;
import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.ai;
import com.crashlytics.android.internal.ao;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.u;
import com.crashlytics.android.internal.v;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class Crashlytics extends u {
    public static final String TAG = "Crashlytics";
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static boolean r = false;
    private static PinningInfoProvider s = null;
    private static C0011av t;
    private static float u;
    private static Crashlytics v;
    private final long a = System.currentTimeMillis();
    private final ConcurrentHashMap<String, String> b = new ConcurrentHashMap();
    private CrashlyticsListener c;
    private ba d;
    private ao e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i;

    static /* synthetic */ boolean a(Crashlytics crashlytics, Activity activity, aQ aQVar) {
        x xVar = new x(activity, aQVar);
        az azVar = new az(crashlytics);
        activity.runOnUiThread(new au(crashlytics, activity, azVar, xVar, aQVar));
        v.a().b().a(TAG, "Waiting for user opt-in.");
        azVar.b();
        return azVar.a();
    }

    public static void start(Context context) {
        start(context, 1.0f);
    }

    public static void start(Context context, float f) {
        u = f;
        if (!C0003ab.d(context)) {
            v.a().a(new A());
        }
        v.a(context, getInstance(), new D());
    }

    public static synchronized Crashlytics getInstance() {
        Crashlytics crashlytics;
        synchronized (Crashlytics.class) {
            crashlytics = (Crashlytics) v.a().a(Crashlytics.class);
            if (crashlytics == null) {
                if (v == null) {
                    v = new Crashlytics();
                }
                crashlytics = v;
            }
        }
        return crashlytics;
    }

    public static void logException(Throwable th) {
        Crashlytics instance = getInstance();
        if (instance == null || instance.d == null) {
            v.a().b().a(TAG, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", null);
        } else if (th == null) {
            v.a().b().a(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            instance.d.a(Thread.currentThread(), th);
        }
    }

    static void a(String str) {
        D d = (D) v.a().a(D.class);
        if (d != null) {
            d.a(new C0007ag(str));
        }
    }

    static void b(String str) {
        D d = (D) v.a().a(D.class);
        if (d != null) {
            d.a(new C0006af(str));
        }
    }

    public static void log(String str) {
        a(3, TAG, str);
    }

    private static void a(int i, String str, String str2) {
        Crashlytics instance = getInstance();
        if (instance == null || instance.d == null) {
            v.a().b().a(str, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging messages.", null);
            return;
        }
        instance.d.a(System.currentTimeMillis() - instance.a, C0003ab.b(i) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2);
    }

    public static void log(int i, String str, String str2) {
        a(i, str, str2);
        v.a().b().a(i, str, str2, true);
    }

    public static void setUserIdentifier(String str) {
        getInstance().f = c(str);
    }

    public static void setUserName(String str) {
        getInstance().h = c(str);
    }

    public static void setUserEmail(String str) {
        getInstance().g = c(str);
    }

    public static void setApplicationInstallationIdentifier(String str) {
        v.a().a(c(str));
    }

    public static void setString(String str, String str2) {
        if (str != null) {
            String c = c(str);
            if (getInstance().b.size() < 64 || getInstance().b.containsKey(c)) {
                Object obj;
                if (str2 == null) {
                    obj = "";
                } else {
                    obj = c(str2);
                }
                getInstance().b.put(c, obj);
                return;
            }
            v.a().b().a(TAG, "Exceeded maximum number of custom attributes (64)");
        } else if (j == null || !C0003ab.f(j)) {
            v.a().b().a(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
        } else {
            throw new IllegalArgumentException("Custom attribute key cannot be null.");
        }
    }

    public static void setBool(String str, boolean z) {
        setString(str, Boolean.toString(z));
    }

    public static void setDouble(String str, double d) {
        setString(str, Double.toString(d));
    }

    public static void setFloat(String str, float f) {
        setString(str, Float.toString(f));
    }

    public static void setInt(String str, int i) {
        setString(str, Integer.toString(i));
    }

    public static void setLong(String str, long j) {
        setString(str, Long.toString(j));
    }

    final Map<String, String> a() {
        return Collections.unmodifiableMap(this.b);
    }

    public final void setListener(CrashlyticsListener crashlyticsListener) {
        this.c = crashlyticsListener;
    }

    public final void setDebugMode(boolean z) {
        v.a().a(z);
    }

    public final boolean getDebugMode() {
        return v.a().f();
    }

    public static void setPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        if (s != pinningInfoProvider) {
            s = pinningInfoProvider;
            if (t == null) {
                return;
            }
            if (pinningInfoProvider == null) {
                t.a(null);
            } else {
                t.a(new ap(pinningInfoProvider));
            }
        }
    }

    public static PinningInfoProvider getPinningInfoProvider() {
        return s;
    }

    public final boolean verifyPinning(URL url) {
        try {
            if (getPinningInfoProvider() == null) {
                return false;
            }
            C0013ay a = t.a(C0012ax.GET, url.toString());
            ((HttpsURLConnection) a.a()).setInstanceFollowRedirects(false);
            a.b();
            return true;
        } catch (Throwable e) {
            v.a().b().a(TAG, "Could not verify SSL pinning", e);
            return false;
        }
    }

    @Deprecated
    public static String getCrashlyticsVersion() {
        return getInstance().getVersion();
    }

    public final void crash() {
        new CrashTest().indexOutOfBounds();
    }

    final ao b() {
        return this.e;
    }

    protected final void c() {
        Context context = super.getContext();
        String a = r.a(context, false);
        if (a != null) {
            try {
                a(a, context, u);
            } catch (CrashlyticsMissingDependencyException e) {
                throw e;
            } catch (Throwable e2) {
                v.a().b().a(TAG, "Crashlytics was not started due to an exception during initialization", e2);
            }
        }
    }

    public final String getVersion() {
        return v.a().getVersion();
    }

    static String d() {
        return k;
    }

    static String e() {
        return l;
    }

    static String f() {
        return o;
    }

    static String g() {
        return n;
    }

    static String h() {
        return m;
    }

    static String i() {
        return C0003ab.a(j, "com.crashlytics.ApiEndpoint");
    }

    final boolean j() {
        return ((Boolean) aS.a().a(new aq(this), Boolean.valueOf(false))).booleanValue();
    }

    static boolean k() {
        return C0003ab.a().getBoolean("always_send_reports_opt_in", false);
    }

    static void a(boolean z) {
        C0003ab.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    final ba l() {
        return this.d;
    }

    final String m() {
        return this.e.a() ? this.f : null;
    }

    final String n() {
        return this.e.a() ? this.g : null;
    }

    final String o() {
        return this.e.a() ? this.h : null;
    }

    final boolean p() {
        return ((Boolean) aS.a().a(new ar(this), Boolean.valueOf(true))).booleanValue();
    }

    final v q() {
        return (v) aS.a().a(new as(this), null);
    }

    final aR r() {
        return (aR) aS.a().a(new at(this), null);
    }

    private static String c(String str) {
        if (str == null) {
            return str;
        }
        str = str.trim();
        if (str.length() > 1024) {
            return str.substring(0, 1024);
        }
        return str;
    }

    private synchronized void a(String str, Context context, float f) {
        boolean z = false;
        synchronized (this) {
            if (j != null) {
                v.a().b().a(TAG, "Crashlytics already started, ignoring re-initialization attempt.");
            } else {
                p = str;
                j = new ContextWrapper(context.getApplicationContext());
                t = new C0011av(v.a().b());
                v.a().b().b(TAG, "Initializing Crashlytics " + getCrashlyticsVersion());
                try {
                    k = j.getPackageName();
                    PackageManager packageManager = j.getPackageManager();
                    l = packageManager.getInstallerPackageName(k);
                    v.a().b().a(TAG, "Installer package name is: " + l);
                    PackageInfo packageInfo = packageManager.getPackageInfo(k, 0);
                    n = Integer.toString(packageInfo.versionCode);
                    o = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
                    m = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
                    q = Integer.toString(context.getApplicationInfo().targetSdkVersion);
                    this.i = C0003ab.i(context);
                } catch (Throwable e) {
                    v.a().b().a(TAG, "Error setting up app properties", e);
                }
                this.e = new ao(j);
                this.e.h();
                new ah(this.i, C0003ab.a(j, "com.crashlytics.RequireBuildId", true)).a(str, k);
                try {
                    v.a().b().a(TAG, "Installing exception handler...");
                    this.d = new ba(Thread.getDefaultUncaughtExceptionHandler(), this.c, this.i);
                    z = this.d.f();
                    this.d.d();
                    this.d.c();
                    this.d.h();
                    Thread.setDefaultUncaughtExceptionHandler(this.d);
                    v.a().b().a(TAG, "Successfully installed exception handler.");
                } catch (Throwable e2) {
                    v.a().b().a(TAG, "There was a problem installing the exception handler.", e2);
                }
                CountDownLatch countDownLatch = new CountDownLatch(1);
                new Thread(new ay(this, context, f, countDownLatch), "Crashlytics Initializer").start();
                if (z) {
                    v.a().b().a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
                    try {
                        if (!countDownLatch.await(4000, TimeUnit.MILLISECONDS)) {
                            v.a().b().c(TAG, "Crashlytics initialization was not completed in the allotted time.");
                        }
                    } catch (Throwable e22) {
                        v.a().b().a(TAG, "Crashlytics was interrupted during initialization.", e22);
                    }
                }
            }
        }
    }

    private boolean a(Context context, float f) {
        aX b;
        boolean z;
        boolean z2 = true;
        boolean z3 = false;
        String g = C0003ab.g(getContext());
        try {
            aS.a().a(context, t, n, o, i()).c();
            b = aS.a().b();
        } catch (Throwable e) {
            v.a().b().a(TAG, "Error dealing with settings", e);
            b = null;
        }
        boolean d;
        if (b != null) {
            try {
                aM aMVar = b.a;
                if ("new".equals(aMVar.a)) {
                    if (new t(i(), aMVar.b, t).a(a(y.a(getContext(), g)))) {
                        d = aS.a().d();
                    } else {
                        v.a().b().a(TAG, "Failed to create app with Crashlytics service.", null);
                        d = false;
                    }
                } else if ("configured".equals(aMVar.a)) {
                    d = aS.a().d();
                } else {
                    if (aMVar.d) {
                        v.a().b().a(TAG, "Server says an update is required - forcing a full App update.");
                        new ae(i(), aMVar.b, t).a(a(y.a(getContext(), g)));
                    }
                    d = true;
                }
                z = d;
            } catch (Throwable e2) {
                v.a().b().a(TAG, "Error performing auto configuration.", e2);
                z = false;
            }
            try {
                d = b.d.b;
            } catch (Throwable e22) {
                v.a().b().a(TAG, "Error getting collect reports setting.", e22);
                d = false;
            }
        } else {
            d = false;
            z = false;
        }
        if (z && r0) {
            try {
                z2 = this.d.b() & 1;
                v q = q();
                if (q != null) {
                    new ab(q).a(f);
                }
            } catch (Throwable e222) {
                v.a().b().a(TAG, "Error sending crash report", e222);
            }
        } else {
            z3 = true;
        }
        if (z3) {
            v.a().b().a(TAG, "Crash reporting disabled.");
        }
        return z2;
    }

    private ag a(y yVar) {
        return new ag(p, k, o, n, C0003ab.a(this.i), m, ai.a(l).a(), q, "0", yVar);
    }
}
