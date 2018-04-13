package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public final class v extends p {
    private b a = new C0001B(m.a);
    private AtomicReference<q> b = new AtomicReference();
    private boolean c;
    private File d;
    private Application e;
    private WeakReference<Activity> f;
    private String g;
    private int h = 4;
    private ConcurrentHashMap<Class<? extends u>, u> i = new ConcurrentHashMap();

    public static v a() {
        return cl.a;
    }

    v() {
    }

    public static synchronized void a(Context context, u... uVarArr) {
        synchronized (v.class) {
            if (!cl.a.isInitialized()) {
                v a = cl.a;
                a.e = r.b(context);
                v a2 = a.a(r.a(context));
                for (Object obj : uVarArr) {
                    if (!a2.i.containsKey(uVarArr)) {
                        a2.i.putIfAbsent(obj.getClass(), obj);
                    }
                }
                a2.a(context);
            }
        }
    }

    public final q b() {
        q qVar = (q) this.b.get();
        if (qVar != null) {
            return qVar;
        }
        qVar = new r();
        if (this.b.compareAndSet(null, qVar)) {
            return qVar;
        }
        return (q) this.b.get();
    }

    public final void a(q qVar) {
        this.b.set(qVar);
    }

    public final Application d() {
        return this.e;
    }

    private v a(Activity activity) {
        this.f = new WeakReference(activity);
        return this;
    }

    public final Activity e() {
        if (this.f != null) {
            return (Activity) this.f.get();
        }
        return null;
    }

    protected final void c() {
        Context context = getContext();
        this.d = new File(context.getFilesDir(), "com.crashlytics.sdk.android");
        if (!this.d.exists()) {
            this.d.mkdirs();
        }
        if (VERSION.SDK_INT >= 14) {
            cj.a(new cj(), this.e);
        }
        if (this.c && Log.isLoggable("CrashlyticsInternal", 3)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (p pVar : this.i.values()) {
                long nanoTime = System.nanoTime();
                pVar.a(context);
                stringBuilder.append("sdkPerfStart.").append(pVar.getClass().getName()).append('=').append(System.nanoTime() - nanoTime).append('\n');
            }
            Log.d("CrashlyticsInternal", stringBuilder.toString());
            return;
        }
        for (p pVar2 : this.i.values()) {
            pVar2.a(context);
        }
    }

    public final String getVersion() {
        return "1.1.13.29";
    }

    public final <T extends u> T a(Class<T> cls) {
        return (u) this.i.get(cls);
    }

    public final void a(boolean z) {
        this.c = z;
        this.h = z ? 3 : 4;
    }

    public final boolean f() {
        return this.c;
    }

    public final int g() {
        return this.h;
    }

    public final File h() {
        return this.d;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final String i() {
        return this.g;
    }
}
