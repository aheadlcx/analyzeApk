package com.flurry.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.view.View;
import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.WeakHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class FlurryAgent implements LocationListener {
    static String a;
    private static final String[] b = new String[]{"9774d56d682e549c", "dead00beef"};
    private static volatile String c = null;
    private static volatile String d = null;
    private static volatile String e = "http://ad.flurry.com/getCanvas.do";
    private static volatile String f = null;
    private static volatile String g = "http://ad.flurry.com/getAndroidApp.do";
    private static final FlurryAgent h = new FlurryAgent();
    private static long i = 10000;
    private static boolean j = true;
    private static boolean k = false;
    private static volatile String kInsecureReportUrl = "http://data.flurry.com/aap.do";
    private static volatile String kSecureReportUrl = "https://data.flurry.com/aap.do";
    private static boolean l = false;
    private static boolean m = true;
    private static Criteria n = null;
    private static boolean o = false;
    private static AppCircle p = new AppCircle();
    private boolean A = true;
    private List B;
    private LocationManager C;
    private String D;
    private boolean E;
    private long F;
    private List G = new ArrayList();
    private long H;
    private long I;
    private long J;
    private String K = "";
    private String L = "";
    private byte M = (byte) -1;
    private String N = "";
    private byte O = (byte) -1;
    private Long P;
    private int Q;
    private Location R;
    private Map S = new HashMap();
    private List T = new ArrayList();
    private boolean U;
    private int V;
    private List W = new ArrayList();
    private int X;
    private u Y = new u();
    private final Handler q;
    private File r;
    private File s = null;
    private volatile boolean t = false;
    private volatile boolean u = false;
    private long v;
    private Map w = new WeakHashMap();
    private String x;
    private String y;
    private String z;

    public class FlurryDefaultExceptionHandler implements UncaughtExceptionHandler {
        private UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        FlurryDefaultExceptionHandler() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            try {
                FlurryAgent.h.a(th);
            } catch (Throwable th2) {
                ah.b("FlurryAgent", "", th2);
            }
            if (this.a != null) {
                this.a.uncaughtException(thread, th);
            }
        }
    }

    static /* synthetic */ void a(FlurryAgent flurryAgent, Context context, boolean z) {
        Location location = null;
        if (z) {
            try {
                location = flurryAgent.d(context);
            } catch (Throwable th) {
                ah.b("FlurryAgent", "", th);
                return;
            }
        }
        synchronized (flurryAgent) {
            flurryAgent.R = location;
        }
        if (o) {
            flurryAgent.Y.c();
        }
        flurryAgent.c(true);
    }

    static /* synthetic */ void b(FlurryAgent flurryAgent, Context context) {
        Object obj = null;
        try {
            synchronized (flurryAgent) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - flurryAgent.v;
                if (!flurryAgent.t && elapsedRealtime > i && flurryAgent.G.size() > 0) {
                    obj = 1;
                }
            }
            if (obj != null) {
                flurryAgent.c(false);
            }
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    final void a(Throwable th) {
        th.printStackTrace();
        String str = "";
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            StackTraceElement stackTraceElement = stackTrace[0];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append(Config.TRACE_TODAY_VISIT_SPLIT).append(stackTraceElement.getLineNumber());
            if (th.getMessage() != null) {
                stringBuilder.append(" (" + th.getMessage() + ")");
            }
            str = stringBuilder.toString();
        } else if (th.getMessage() != null) {
            str = th.getMessage();
        }
        onError("uncaught", str, th.getClass().toString());
        this.w.clear();
        a(null, true);
    }

    private FlurryAgent() {
        HandlerThread handlerThread = new HandlerThread("FlurryAgent");
        handlerThread.start();
        this.q = new Handler(handlerThread.getLooper());
    }

    public static void setCatalogIntentName(String str) {
        a = str;
    }

    public static AppCircle getAppCircle() {
        return p;
    }

    static View a(Context context, String str, int i) {
        View view = null;
        if (o) {
            try {
                view = h.Y.a(context, str, i);
            } catch (Throwable th) {
                ah.b("FlurryAgent", "", th);
            }
        }
        return view;
    }

    static void a(Context context, String str) {
        if (o) {
            h.Y.a(context, str);
        }
    }

    static Offer a(String str) {
        if (o) {
            return h.Y.b(str);
        }
        return null;
    }

    static List b(String str) {
        if (o) {
            return h.Y.c(str);
        }
        return null;
    }

    static void a(Context context, long j) {
        if (!o) {
            ah.d("FlurryAgent", "Cannot accept Offer. AppCircle is not enabled");
        }
        h.Y.a(context, j);
    }

    static void a(List list) {
        if (o) {
            h.Y.a(list);
        }
    }

    static void a(boolean z) {
        if (o) {
            h.Y.a(z);
        }
    }

    static boolean a() {
        return h.Y.i();
    }

    public static void enableAppCircle() {
        o = true;
    }

    public static void setDefaultNoAdsMessage(String str) {
        if (o) {
            if (str == null) {
                str = "";
            }
            u.b = str;
        }
    }

    static void a(AppCircleCallback appCircleCallback) {
        h.Y.a(appCircleCallback);
    }

    public static void addUserCookie(String str, String str2) {
        if (o) {
            h.Y.a(str, str2);
        }
    }

    public static void clearUserCookies() {
        if (o) {
            h.Y.l();
        }
    }

    public static void setUseHttps(boolean z) {
        k = z;
    }

    public static void onStartSession(Context context, String str) {
        if (context == null) {
            throw new NullPointerException("Null context");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Api key not specified");
        } else {
            try {
                h.b(context, str);
            } catch (Throwable th) {
                ah.b("FlurryAgent", "", th);
            }
        }
    }

    public static void onEndSession(Context context) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        try {
            h.a(context, false);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    public static void onPageView() {
        try {
            h.j();
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    public static void logEvent(String str) {
        try {
            h.a(str, null, false);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map map) {
        try {
            h.a(str, map, false);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, boolean z) {
        try {
            h.a(str, null, z);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map map, boolean z) {
        try {
            h.a(str, map, z);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void endTimedEvent(String str) {
        try {
            h.c(str);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "Failed to signify the end of event: " + str, th);
        }
    }

    public static void onError(String str, String str2, String str3) {
        try {
            h.a(str, str2, str3);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    public static void setReportUrl(String str) {
        c = str;
    }

    public static void setCanvasUrl(String str) {
        d = str;
    }

    public static void setGetAppUrl(String str) {
        f = str;
    }

    public static void setVersionName(String str) {
        synchronized (h) {
            h.z = str;
        }
    }

    public static void setReportLocation(boolean z) {
        synchronized (h) {
            h.A = z;
        }
    }

    public static void setLocationCriteria(Criteria criteria) {
        synchronized (h) {
            n = criteria;
        }
    }

    public static void setAge(int i) {
        if (i > 0 && i < 110) {
            Date date = new Date(new Date(System.currentTimeMillis() - (((long) i) * 31449600000L)).getYear(), 1, 1);
            h.P = Long.valueOf(date.getTime());
        }
    }

    public static void setGender(byte b) {
        switch (b) {
            case (byte) 0:
            case (byte) 1:
                h.O = b;
                return;
            default:
                h.O = (byte) -1;
                return;
        }
    }

    public static int getAgentVersion() {
        return 117;
    }

    public static boolean getForbidPlaintextFallback() {
        return false;
    }

    public static void setLogEnabled(boolean z) {
        synchronized (h) {
            if (z) {
                ah.b();
            } else {
                ah.a();
            }
        }
    }

    public static void setLogLevel(int i) {
        synchronized (h) {
            ah.a(i);
        }
    }

    public static void setContinueSessionMillis(long j) {
        if (j < Config.BPLUS_DELAY_TIME) {
            ah.b("FlurryAgent", "Invalid time set for session resumption: " + j);
            return;
        }
        synchronized (h) {
            i = j;
        }
    }

    public static void setLogEvents(boolean z) {
        synchronized (h) {
            j = z;
        }
    }

    public static void setUserId(String str) {
        synchronized (h) {
            h.N = r.a(str, 255);
        }
    }

    public static void setCaptureUncaughtExceptions(boolean z) {
        synchronized (h) {
            if (h.t) {
                ah.b("FlurryAgent", "Cannot setCaptureUncaughtExceptions after onSessionStart");
                return;
            }
            m = z;
        }
    }

    protected static boolean isCaptureUncaughtExceptions() {
        return m;
    }

    public static void onEvent(String str) {
        try {
            h.a(str, null, false);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    public static void onEvent(String str, Map map) {
        try {
            h.a(str, map, false);
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    static u b() {
        return h.Y;
    }

    private synchronized void b(Context context, String str) {
        if (!(this.x == null || this.x.equals(str))) {
            ah.b("FlurryAgent", "onStartSession called with different api keys: " + this.x + " and " + str);
        }
        if (((Context) this.w.put(context, context)) != null) {
            ah.d("FlurryAgent", "onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context");
        }
        if (!this.t) {
            ah.a("FlurryAgent", "Initializing Flurry session");
            this.x = str;
            this.s = context.getFileStreamPath(".flurryagent." + Integer.toString(this.x.hashCode(), 16));
            this.r = context.getFileStreamPath(".flurryb.");
            if (m) {
                Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
            }
            Context applicationContext = context.getApplicationContext();
            if (this.z == null) {
                this.z = c(applicationContext);
            }
            String packageName = applicationContext.getPackageName();
            if (!(this.y == null || this.y.equals(packageName))) {
                ah.b("FlurryAgent", "onStartSession called from different application packages: " + this.y + " and " + packageName);
            }
            this.y = packageName;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.v > i) {
                ah.a("FlurryAgent", "New session");
                this.H = System.currentTimeMillis();
                this.I = elapsedRealtime;
                this.J = -1;
                this.N = "";
                this.Q = 0;
                this.R = null;
                this.L = TimeZone.getDefault().getID();
                this.K = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                this.S = new HashMap();
                this.T = new ArrayList();
                this.U = true;
                this.W = new ArrayList();
                this.V = 0;
                this.X = 0;
                if (o) {
                    if (!this.Y.b()) {
                        ah.a("FlurryAgent", "Initializing AppCircle");
                        a aVar = new a();
                        aVar.a = this.x;
                        aVar.b = this.F;
                        aVar.c = this.H;
                        aVar.d = this.I;
                        aVar.e = d != null ? d : e;
                        aVar.f = c();
                        aVar.g = this.q;
                        this.Y.a(context, aVar);
                        ah.a("FlurryAgent", "AppCircle initialized");
                    }
                    this.Y.a();
                }
                a(new d(this, applicationContext, this.A));
            } else {
                ah.a("FlurryAgent", "Continuing previous session");
                if (!this.G.isEmpty()) {
                    this.G.remove(this.G.size() - 1);
                }
            }
            this.t = true;
        }
    }

    private synchronized void a(Context context, boolean z) {
        if (context != null) {
            if (((Context) this.w.remove(context)) == null) {
                ah.d("FlurryAgent", "onEndSession called without context from corresponding onStartSession");
            }
        }
        if (this.t && this.w.isEmpty()) {
            ah.a("FlurryAgent", "Ending session");
            m();
            Context applicationContext = context == null ? null : context.getApplicationContext();
            if (context != null) {
                String packageName = applicationContext.getPackageName();
                if (!this.y.equals(packageName)) {
                    ah.b("FlurryAgent", "onEndSession called from different application package, expected: " + this.y + " actual: " + packageName);
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.v = elapsedRealtime;
            this.J = elapsedRealtime - this.I;
            if (this.D == null) {
                ah.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
            }
            a(new b(this, z, applicationContext));
            this.t = false;
        }
    }

    private synchronized void i() {
        Throwable e;
        Closeable closeable = null;
        Closeable dataOutputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(1);
                dataOutputStream.writeUTF(this.z);
                dataOutputStream.writeLong(this.H);
                dataOutputStream.writeLong(this.J);
                dataOutputStream.writeLong(0);
                dataOutputStream.writeUTF(this.K);
                dataOutputStream.writeUTF(this.L);
                dataOutputStream.writeByte(this.M);
                dataOutputStream.writeUTF(this.N == null ? "" : this.N);
                if (this.R == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeDouble(a(this.R.getLatitude()));
                    dataOutputStream.writeDouble(a(this.R.getLongitude()));
                    dataOutputStream.writeFloat(this.R.getAccuracy());
                }
                dataOutputStream.writeInt(this.X);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(this.O);
                if (this.P == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeLong(this.P.longValue());
                }
                dataOutputStream.writeShort(this.S.size());
                for (Entry entry : this.S.entrySet()) {
                    dataOutputStream.writeUTF((String) entry.getKey());
                    dataOutputStream.writeInt(((g) entry.getValue()).a);
                }
                dataOutputStream.writeShort(this.T.size());
                for (i b : this.T) {
                    dataOutputStream.write(b.b());
                }
                dataOutputStream.writeBoolean(this.U);
                dataOutputStream.writeInt(this.Q);
                dataOutputStream.writeShort(this.W.size());
                for (aa aaVar : this.W) {
                    dataOutputStream.writeLong(aaVar.a);
                    dataOutputStream.writeUTF(aaVar.b);
                    dataOutputStream.writeUTF(aaVar.c);
                    dataOutputStream.writeUTF(aaVar.d);
                }
                if (o) {
                    List<p> g = this.Y.g();
                    dataOutputStream.writeShort(g.size());
                    for (p a : g) {
                        a.a((DataOutput) dataOutputStream);
                    }
                } else {
                    dataOutputStream.writeShort(0);
                }
                this.G.add(byteArrayOutputStream.toByteArray());
                r.a(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                closeable = dataOutputStream;
                try {
                    ah.b("FlurryAgent", "", e);
                    r.a(closeable);
                } catch (Throwable th) {
                    e = th;
                    dataOutputStream = closeable;
                    r.a(dataOutputStream);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                r.a(dataOutputStream);
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            ah.b("FlurryAgent", "", e);
            r.a(closeable);
        } catch (Throwable th3) {
            e = th3;
            dataOutputStream = null;
            r.a(dataOutputStream);
            throw e;
        }
    }

    private static double a(double d) {
        return ((double) Math.round(d * 1000.0d)) / 1000.0d;
    }

    private void a(Runnable runnable) {
        this.q.post(runnable);
    }

    private synchronized void j() {
        this.X++;
    }

    private synchronized void a(String str, Map map, boolean z) {
        if (this.T == null) {
            ah.b("FlurryAgent", "onEvent called before onStartSession.  Event: " + str);
        } else {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.I;
            String a = r.a(str, 255);
            if (a.length() != 0) {
                g gVar = (g) this.S.get(a);
                if (gVar != null) {
                    gVar.a++;
                } else if (this.S.size() < 100) {
                    gVar = new g();
                    gVar.a = 1;
                    this.S.put(a, gVar);
                } else if (ah.a("FlurryAgent")) {
                    ah.a("FlurryAgent", "MaxEventIds exceeded: " + a);
                }
                if (!j || this.T.size() >= 200 || this.V >= 16000) {
                    this.U = false;
                } else {
                    Map emptyMap;
                    if (map == null) {
                        emptyMap = Collections.emptyMap();
                    } else {
                        emptyMap = map;
                    }
                    if (emptyMap.size() <= 10) {
                        i iVar = new i(a, emptyMap, elapsedRealtime, z);
                        if (iVar.b().length + this.V <= 16000) {
                            this.T.add(iVar);
                            this.V += iVar.b().length;
                        } else {
                            this.V = 16000;
                            this.U = false;
                        }
                    } else if (ah.a("FlurryAgent")) {
                        ah.a("FlurryAgent", "MaxEventParams exceeded: " + emptyMap.size());
                    }
                }
            }
        }
    }

    private synchronized void c(String str) {
        for (i iVar : this.T) {
            if (iVar.a(str)) {
                iVar.a();
                break;
            }
        }
    }

    private synchronized void a(String str, String str2, String str3) {
        if (this.W == null) {
            ah.b("FlurryAgent", "onError called before onStartSession.  Error: " + str);
        } else {
            this.Q++;
            if (this.W.size() < 10) {
                aa aaVar = new aa();
                aaVar.a = System.currentTimeMillis();
                aaVar.b = r.a(str, 255);
                aaVar.c = r.a(str2, 512);
                aaVar.d = r.a(str3, 255);
                this.W.add(aaVar);
            }
        }
    }

    private synchronized byte[] b(boolean z) {
        byte[] toByteArray;
        Throwable th;
        synchronized (this) {
            Closeable dataOutputStream;
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(15);
                    if (o && z) {
                        dataOutputStream.writeShort(1);
                    } else {
                        dataOutputStream.writeShort(0);
                    }
                    if (o) {
                        dataOutputStream.writeLong(this.Y.e());
                        Set<Long> f = this.Y.f();
                        dataOutputStream.writeShort(f.size());
                        for (Long longValue : f) {
                            long longValue2 = longValue.longValue();
                            dataOutputStream.writeByte(1);
                            dataOutputStream.writeLong(longValue2);
                        }
                    } else {
                        dataOutputStream.writeLong(0);
                        dataOutputStream.writeShort(0);
                    }
                    dataOutputStream.writeShort(3);
                    dataOutputStream.writeShort(117);
                    dataOutputStream.writeLong(System.currentTimeMillis());
                    dataOutputStream.writeUTF(this.x);
                    dataOutputStream.writeUTF(this.z);
                    dataOutputStream.writeShort(0);
                    dataOutputStream.writeUTF(this.D);
                    dataOutputStream.writeLong(this.F);
                    dataOutputStream.writeLong(this.H);
                    dataOutputStream.writeShort(6);
                    dataOutputStream.writeUTF("device.model");
                    dataOutputStream.writeUTF(Build.MODEL);
                    dataOutputStream.writeUTF("build.brand");
                    dataOutputStream.writeUTF(Build.BRAND);
                    dataOutputStream.writeUTF("build.id");
                    dataOutputStream.writeUTF(Build.ID);
                    dataOutputStream.writeUTF("version.release");
                    dataOutputStream.writeUTF(VERSION.RELEASE);
                    dataOutputStream.writeUTF("build.device");
                    dataOutputStream.writeUTF(Build.DEVICE);
                    dataOutputStream.writeUTF("build.product");
                    dataOutputStream.writeUTF(Build.PRODUCT);
                    int size = this.G.size();
                    dataOutputStream.writeShort(size);
                    for (int i = 0; i < size; i++) {
                        dataOutputStream.write((byte[]) this.G.get(i));
                    }
                    this.B = new ArrayList(this.G);
                    dataOutputStream.close();
                    toByteArray = byteArrayOutputStream.toByteArray();
                    r.a(dataOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ah.b("FlurryAgent", "Error when generating report", th);
                        r.a(dataOutputStream);
                        toByteArray = null;
                        return toByteArray;
                    } catch (Throwable th3) {
                        th = th3;
                        r.a(dataOutputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                dataOutputStream = null;
                r.a(dataOutputStream);
                throw th;
            }
        }
        return toByteArray;
    }

    private static String k() {
        if (c != null) {
            return c;
        }
        if (l) {
            return kInsecureReportUrl;
        }
        if (k) {
            return kSecureReportUrl;
        }
        return kInsecureReportUrl;
    }

    static String c() {
        return f != null ? f : g;
    }

    static boolean d() {
        if (o) {
            return h.Y.n();
        }
        return false;
    }

    private boolean a(byte[] bArr) {
        String k = k();
        if (k == null) {
            return false;
        }
        boolean a;
        try {
            a = a(bArr, k);
        } catch (Exception e) {
            ah.a("FlurryAgent", "Sending report exception: " + e.getMessage());
            a = false;
        }
        if (a || c != null || !k || l) {
            return a;
        }
        synchronized (h) {
            l = true;
            String k2 = k();
            if (k2 == null) {
                return false;
            }
            try {
                return a(bArr, k2);
            } catch (Exception e2) {
                return a;
            }
        }
    }

    private boolean a(byte[] bArr, String str) {
        boolean z = true;
        if (!"local".equals(str)) {
            ah.a("FlurryAgent", "Sending report to: " + str);
            HttpEntity byteArrayEntity = new ByteArrayEntity(bArr);
            byteArrayEntity.setContentType("application/octet-stream");
            HttpUriRequest httpPost = new HttpPost(str);
            httpPost.setEntity(byteArrayEntity);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
            httpPost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            HttpResponse execute = a(basicHttpParams).execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            synchronized (this) {
                if (statusCode == 200) {
                    ah.a("FlurryAgent", "Report successful");
                    this.E = true;
                    this.G.removeAll(this.B);
                    HttpEntity entity = execute.getEntity();
                    ah.a("FlurryAgent", "Processing report response");
                    if (!(entity == null || entity.getContentLength() == 0)) {
                        try {
                            a(new DataInputStream(entity.getContent()));
                        } finally {
                            entity.consumeContent();
                        }
                    }
                } else {
                    ah.a("FlurryAgent", "Report failed. HTTP response: " + statusCode);
                    z = false;
                }
                this.B = null;
            }
        }
        return z;
    }

    private void a(DataInputStream dataInputStream) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Map hashMap3 = new HashMap();
        Map hashMap4 = new HashMap();
        Map hashMap5 = new HashMap();
        Map hashMap6 = new HashMap();
        int readUnsignedShort;
        do {
            readUnsignedShort = dataInputStream.readUnsignedShort();
            int readInt = dataInputStream.readInt();
            byte readByte;
            int readUnsignedShort2;
            switch (readUnsignedShort) {
                case 258:
                    dataInputStream.readInt();
                    break;
                case 259:
                    readByte = dataInputStream.readByte();
                    int readUnsignedShort3 = dataInputStream.readUnsignedShort();
                    Object obj = new v[readUnsignedShort3];
                    for (readInt = 0; readInt < readUnsignedShort3; readInt++) {
                        obj[readInt] = new v(dataInputStream);
                    }
                    hashMap.put(Byte.valueOf(readByte), obj);
                    break;
                case 262:
                    readUnsignedShort2 = dataInputStream.readUnsignedShort();
                    for (readInt = 0; readInt < readUnsignedShort2; readInt++) {
                        AdImage adImage = new AdImage(dataInputStream);
                        hashMap2.put(Long.valueOf(adImage.a), adImage);
                        ah.a("FlurryAgent", "Parsed image: " + adImage.a);
                    }
                    break;
                case 263:
                    readUnsignedShort2 = dataInputStream.readInt();
                    for (readInt = 0; readInt < readUnsignedShort2; readInt++) {
                        e eVar = new e(dataInputStream);
                        hashMap4.put(eVar.a, eVar);
                    }
                    break;
                case 264:
                    break;
                case 266:
                    readByte = dataInputStream.readByte();
                    for (byte b = (byte) 0; b < readByte; b++) {
                        c cVar = new c(dataInputStream);
                        hashMap5.put(Byte.valueOf(cVar.a), cVar);
                    }
                    break;
                case 268:
                    readUnsignedShort2 = dataInputStream.readInt();
                    for (readInt = 0; readInt < readUnsignedShort2; readInt++) {
                        hashMap6.put(Short.valueOf(dataInputStream.readShort()), Long.valueOf(dataInputStream.readLong()));
                    }
                    break;
                case 269:
                    dataInputStream.skipBytes(readInt);
                    break;
                case 270:
                    dataInputStream.skipBytes(readInt);
                    break;
                case 271:
                    byte readByte2 = dataInputStream.readByte();
                    for (readByte = (byte) 0; readByte < readByte2; readByte++) {
                        c cVar2 = (c) hashMap5.get(Byte.valueOf(dataInputStream.readByte()));
                        if (cVar2 != null) {
                            cVar2.a((DataInput) dataInputStream);
                        }
                    }
                    break;
                case 272:
                    long readLong = dataInputStream.readLong();
                    al alVar = (al) hashMap3.get(Long.valueOf(readLong));
                    if (alVar == null) {
                        alVar = new al();
                    }
                    alVar.a = dataInputStream.readUTF();
                    alVar.c = dataInputStream.readInt();
                    hashMap3.put(Long.valueOf(readLong), alVar);
                    break;
                case 273:
                    dataInputStream.skipBytes(readInt);
                    break;
                default:
                    ah.a("FlurryAgent", "Unknown chunkType: " + readUnsignedShort);
                    dataInputStream.skipBytes(readInt);
                    break;
            }
        } while (readUnsignedShort != 264);
        if (o) {
            if (hashMap.isEmpty()) {
                ah.a("FlurryAgent", "No ads from server");
            }
            this.Y.a(hashMap, hashMap4, hashMap5, hashMap2, hashMap3, hashMap6);
        }
    }

    private void c(boolean z) {
        try {
            ah.a("FlurryAgent", "generating report");
            byte[] b = b(z);
            if (b == null) {
                ah.a("FlurryAgent", "Error generating report");
            } else if (a(b)) {
                ah.a("FlurryAgent", "Done sending " + (this.t ? "initial " : "") + "agent report");
                l();
            }
        } catch (Throwable e) {
            ah.a("FlurryAgent", "", e);
        } catch (Throwable e2) {
            ah.b("FlurryAgent", "", e2);
        }
    }

    private synchronized void a(Context context) {
        Throwable th;
        this.D = b(context);
        if (this.s.exists()) {
            ah.c("FlurryAgent", "loading persistent data: " + this.s.getAbsolutePath());
            Closeable dataInputStream;
            try {
                dataInputStream = new DataInputStream(new FileInputStream(this.s));
                try {
                    int readUnsignedShort = dataInputStream.readUnsignedShort();
                    ah.c("FlurryAgent", "Magic: " + readUnsignedShort);
                    if (readUnsignedShort == 46586) {
                        b((DataInputStream) dataInputStream);
                    } else {
                        ah.a("FlurryAgent", "Unexpected file type");
                    }
                    r.a(dataInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ah.b("FlurryAgent", "Error when loading persistent file", th);
                        r.a(dataInputStream);
                        if (!this.u) {
                            if (this.s.delete()) {
                                ah.b("FlurryAgent", "Cannot delete persistence file");
                            } else {
                                ah.a("FlurryAgent", "Deleted persistence file");
                            }
                        }
                        if (!this.u) {
                            this.E = false;
                            this.F = this.H;
                            this.u = true;
                        }
                        if (this.D == null) {
                            this.D = "ID" + Long.toString(Double.doubleToLongBits(Math.random()) + (37 * (System.nanoTime() + ((long) (this.x.hashCode() * 37)))), 16);
                            ah.c("FlurryAgent", "Generated phoneId: " + this.D);
                        }
                        this.Y.a(this.D);
                        c(context, this.D);
                    } catch (Throwable th3) {
                        th = th3;
                        r.a(dataInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                dataInputStream = null;
                r.a(dataInputStream);
                throw th;
            }
            try {
                if (this.u) {
                    if (this.s.delete()) {
                        ah.a("FlurryAgent", "Deleted persistence file");
                    } else {
                        ah.b("FlurryAgent", "Cannot delete persistence file");
                    }
                }
            } catch (Throwable th5) {
                ah.b("FlurryAgent", "", th5);
            }
        } else {
            ah.c("FlurryAgent", "Agent cache file doesn't exist.");
        }
        if (this.u) {
            this.E = false;
            this.F = this.H;
            this.u = true;
        }
        if (this.D == null) {
            this.D = "ID" + Long.toString(Double.doubleToLongBits(Math.random()) + (37 * (System.nanoTime() + ((long) (this.x.hashCode() * 37)))), 16);
            ah.c("FlurryAgent", "Generated phoneId: " + this.D);
        }
        this.Y.a(this.D);
        if (!(this.D.startsWith("AND") || this.r.exists())) {
            c(context, this.D);
        }
    }

    private synchronized void b(DataInputStream dataInputStream) {
        int i = 0;
        synchronized (this) {
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            ah.a("FlurryAgent", "File version: " + readUnsignedShort);
            if (readUnsignedShort > 2) {
                ah.b("FlurryAgent", "Unknown agent file version: " + readUnsignedShort);
                throw new IOException("Unknown agent file version: " + readUnsignedShort);
            }
            if (readUnsignedShort >= 2) {
                String readUTF = dataInputStream.readUTF();
                ah.a("FlurryAgent", "Loading API key: " + this.x);
                if (readUTF.equals(this.x)) {
                    readUTF = dataInputStream.readUTF();
                    if (this.D == null) {
                        ah.a("FlurryAgent", "Loading phoneId: " + readUTF);
                    }
                    this.D = readUTF;
                    this.E = dataInputStream.readBoolean();
                    this.F = dataInputStream.readLong();
                    ah.a("FlurryAgent", "Loading session reports");
                    while (true) {
                        readUnsignedShort = dataInputStream.readUnsignedShort();
                        if (readUnsignedShort == 0) {
                            break;
                        }
                        Object obj = new byte[readUnsignedShort];
                        dataInputStream.readFully(obj);
                        this.G.add(0, obj);
                        i++;
                        ah.a("FlurryAgent", "Session report added: " + i);
                    }
                    ah.a("FlurryAgent", "Persistent file loaded");
                    this.u = true;
                } else {
                    ah.a("FlurryAgent", "Api keys do not match, old: " + readUTF + ", new: " + this.x);
                }
            } else {
                ah.d("FlurryAgent", "Deleting old file version: " + readUnsignedShort);
            }
        }
    }

    private synchronized void l() {
        Closeable dataOutputStream;
        Throwable th;
        try {
            if (a(this.s)) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(this.s));
                try {
                    dataOutputStream.writeShort(46586);
                    dataOutputStream.writeShort(2);
                    dataOutputStream.writeUTF(this.x);
                    dataOutputStream.writeUTF(this.D);
                    dataOutputStream.writeBoolean(this.E);
                    dataOutputStream.writeLong(this.F);
                    for (int size = this.G.size() - 1; size >= 0; size--) {
                        byte[] bArr = (byte[]) this.G.get(size);
                        int length = bArr.length;
                        if ((length + 2) + dataOutputStream.size() > 50000) {
                            ah.a("FlurryAgent", "discarded sessions: " + size);
                            break;
                        }
                        dataOutputStream.writeShort(length);
                        dataOutputStream.write(bArr);
                    }
                    dataOutputStream.writeShort(0);
                    r.a(dataOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                r.a(null);
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            r.a(dataOutputStream);
            throw th;
        }
    }

    private static boolean a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.mkdirs() || parentFile.exists()) {
            return true;
        }
        ah.b("FlurryAgent", "Unable to create persistent dir: " + parentFile);
        return false;
    }

    private synchronized void c(Context context, String str) {
        Closeable dataOutputStream;
        Throwable th;
        this.r = context.getFileStreamPath(".flurryb.");
        if (a(this.r)) {
            try {
                dataOutputStream = new DataOutputStream(new FileOutputStream(this.r));
                try {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.writeUTF(str);
                    r.a(dataOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ah.b("FlurryAgent", "Error when saving b file", th);
                        r.a(dataOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        r.a(dataOutputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                dataOutputStream = null;
                r.a(dataOutputStream);
                throw th;
            }
        }
    }

    private String b(Context context) {
        Closeable dataInputStream;
        Throwable th;
        Throwable th2;
        String str = null;
        Object obj = null;
        if (this.D != null) {
            return this.D;
        }
        String string = System.getString(context.getContentResolver(), "android_id");
        if (string != null && string.length() > 0 && !string.equals("null")) {
            for (Object equals : b) {
                if (string.equals(equals)) {
                    break;
                }
            }
            obj = 1;
        }
        if (obj != null) {
            return "AND" + string;
        }
        File fileStreamPath = context.getFileStreamPath(".flurryb.");
        if (!fileStreamPath.exists()) {
            return str;
        }
        try {
            dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
            try {
                dataInputStream.readInt();
                str = dataInputStream.readUTF();
                r.a(dataInputStream);
                return str;
            } catch (Throwable th3) {
                th = th3;
                try {
                    ah.b("FlurryAgent", "Error when loading b file", th);
                    r.a(dataInputStream);
                    return str;
                } catch (Throwable th4) {
                    th2 = th4;
                    r.a(dataInputStream);
                    throw th2;
                }
            }
        } catch (Throwable th5) {
            dataInputStream = str;
            th2 = th5;
            r.a(dataInputStream);
            throw th2;
        }
    }

    private static String c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            if (packageInfo.versionCode != 0) {
                return Integer.toString(packageInfo.versionCode);
            }
            return "Unknown";
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    private Location d(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            synchronized (this) {
                if (this.C == null) {
                    this.C = locationManager;
                } else {
                    locationManager = this.C;
                }
            }
            Criteria criteria = n;
            if (criteria == null) {
                criteria = new Criteria();
            }
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (bestProvider != null) {
                locationManager.requestLocationUpdates(bestProvider, 0, 0.0f, this, Looper.getMainLooper());
                return locationManager.getLastKnownLocation(bestProvider);
            }
        }
        return null;
    }

    private synchronized void m() {
        if (this.C != null) {
            this.C.removeUpdates(this);
        }
    }

    static String e() {
        return h.x;
    }

    private synchronized String n() {
        return this.D;
    }

    public static String getPhoneId() {
        return h.n();
    }

    public final synchronized void onLocationChanged(Location location) {
        try {
            this.R = location;
            m();
        } catch (Throwable th) {
            ah.b("FlurryAgent", "", th);
        }
    }

    public final void onProviderDisabled(String str) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    private HttpClient a(HttpParams httpParams) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory aiVar = new ai(this, instance);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", aiVar, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(httpParams, schemeRegistry), httpParams);
        } catch (Exception e) {
            return new DefaultHttpClient(httpParams);
        }
    }
}
