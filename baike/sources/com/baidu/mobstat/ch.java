package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;

class ch {
    private static final ch a = new ch();
    private static HashMap<String, cm> o = new HashMap();
    private cn b = new cn();
    private cn c = new cn();
    private cn d = new cn();
    private cn e = new cn();
    private long f = 0;
    private boolean g = true;
    private boolean h;
    private cf i = new cf();
    private int j = -1;
    private volatile int k;
    private volatile long l;
    private Handler m;
    private Runnable n = null;

    public static ch a() {
        return a;
    }

    private ch() {
        HandlerThread handlerThread = new HandlerThread("SessionAnalysisThread");
        handlerThread.start();
        handlerThread.setPriority(10);
        this.m = new Handler(handlerThread.getLooper());
    }

    public int b() {
        return this.k;
    }

    public int c() {
        if (this.j == -1) {
            this.j = 30000;
        }
        return this.j;
    }

    public void a(int i) {
        this.j = i * 1000;
    }

    public void d() {
        this.i.a();
    }

    public void a(long j) {
        this.i.a(j);
    }

    public void b(long j) {
        this.i.b(j);
    }

    public void b(int i) {
        this.i.a(i);
    }

    public long e() {
        return this.i.b();
    }

    private boolean g() {
        return this.g;
    }

    private void a(boolean z) {
        this.g = z;
    }

    public void a(Context context, long j) {
        if (this.l == 0) {
            this.m.post(new ci(this, j));
        }
        this.l = j;
    }

    public void b(Context context, long j) {
        this.m.post(new cj(this, j, context));
    }

    public void a(Context context, long j, String str) {
        db.a("AnalysisPageStart");
        if (TextUtils.isEmpty(str)) {
            db.c("自定义页面 pageName 为 null");
            return;
        }
        cm b = b(str);
        if (b == null) {
            db.c("get page info, PageInfo null");
            return;
        }
        if (b.b) {
            db.c("遗漏StatService.onPageEnd() || missing StatService.onPageEnd()");
        }
        b.b = true;
        b.c = j;
        i();
        if (!this.h) {
            this.m.post(new cp(this, this.f, j, this.l, context, null, null, h(), 1));
            this.h = true;
        }
        this.b.b = new WeakReference(context);
        this.b.a = j;
    }

    private int h() {
        Class cls;
        Class cls2;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (ClassNotFoundException e) {
            cls = null;
        }
        try {
            cls2 = Class.forName("android.support.v4.app.Fragment");
        } catch (ClassNotFoundException e2) {
            cls2 = null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            Object className = stackTrace[i].getClassName();
            Object methodName = stackTrace[i].getMethodName();
            if (!(TextUtils.isEmpty(className) || TextUtils.isEmpty(methodName) || !methodName.equals("onResume"))) {
                Class cls3;
                try {
                    cls3 = Class.forName(className);
                } catch (Throwable th) {
                    cls3 = null;
                }
                if (cls3 == null) {
                    continue;
                } else if (Activity.class.isAssignableFrom(cls3)) {
                    return 1;
                } else {
                    if (cls != null && cls.isAssignableFrom(cls3)) {
                        return 2;
                    }
                    if (cls2 != null && cls2.isAssignableFrom(cls3)) {
                        return 2;
                    }
                }
            }
        }
        return 3;
    }

    public void a(Context context, long j, String str, String str2, ExtraInfo extraInfo) {
        db.a("post pause job");
        this.h = false;
        if (TextUtils.isEmpty(str2)) {
            db.c("自定义页面 pageName 无效值");
            return;
        }
        cm b = b(str2);
        if (b == null) {
            db.c("get page info, PageInfo null");
        } else if (b.b) {
            b.b = false;
            b.d = j;
            this.m.post(new co(this, j, context, null, b.c, (Context) this.b.b.get(), null, 1, str2, null, null, str, false, extraInfo, b));
            c(str2);
            this.f = j;
        } else {
            db.c("Please check (1)遗漏StatService.onPageStart() || missing StatService.onPageStart()");
        }
    }

    public void a(Fragment fragment, long j) {
        db.a("post resume job");
        if (this.c.c) {
            db.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        this.c.c = true;
        i();
        this.m.post(new cp(this, this.f, j, this.l, null, fragment, null, 2, 2));
        this.c.b = new WeakReference(fragment);
        this.c.a = j;
    }

    @TargetApi(11)
    public void a(android.app.Fragment fragment, long j) {
        db.a("post resume job");
        if (this.d.c) {
            db.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        this.d.c = true;
        i();
        this.m.post(new cp(this, this.f, j, this.l, null, null, fragment, 2, 3));
        this.d.b = new WeakReference(fragment);
        this.d.a = j;
    }

    public void a(Context context, long j, boolean z) {
        if (z) {
            this.e.c = true;
            this.e.b = new WeakReference(context);
            this.e.a = j;
        }
        db.a("AnalysisResume job");
        if (!z && this.b.c) {
            db.c("遗漏StatService.onPause() || missing StatService.onPause()");
        }
        if (!z) {
            this.b.c = true;
        }
        i();
        if (!this.h) {
            this.m.post(new cp(this, this.f, j, this.l, context, null, null, 1, 1));
            this.h = true;
        }
        this.b.b = new WeakReference(context);
        this.b.a = j;
    }

    private void i() {
        boolean g = g();
        db.a("isFirstResume:" + g);
        if (g) {
            a(false);
            this.m.post(new ck(this));
        }
    }

    public void a(Context context, long j, boolean z, ExtraInfo extraInfo) {
        db.a("post pause job");
        this.h = false;
        if (z) {
            this.e.c = false;
            this.m.post(new co(this, j, context, null, this.e.a, (Context) this.e.b.get(), null, 1, null, null, null, null, z, extraInfo, null));
            this.f = j;
        } else if (this.b.c) {
            this.b.c = false;
            this.m.post(new co(this, j, context, null, this.b.a, (Context) this.b.b.get(), null, 1, null, null, null, null, z, extraInfo, null));
            this.f = j;
        } else {
            db.c("遗漏StatService.onResume() || missing StatService.onResume()");
        }
    }

    public void b(Fragment fragment, long j) {
        db.a("post pause job");
        if (this.c.c) {
            this.c.c = false;
            this.m.post(new co(this, j, null, fragment, this.c.a, null, (Fragment) this.c.b.get(), 2, null, null, null, null, false, null, null));
            this.f = j;
            return;
        }
        db.c("遗漏android.support.v4.app.Fragment StatService.onResume() || android.support.v4.app.Fragment missing StatService.onResume()");
    }

    @TargetApi(11)
    public void b(android.app.Fragment fragment, long j) {
        db.a("post pause job");
        if (this.d.c) {
            this.d.c = false;
            this.m.post(new co(this, j, null, null, this.d.a, null, null, 3, null, this.d.b.get(), fragment, null, false, null, null));
            this.f = j;
            return;
        }
        db.c("遗漏android.app.Fragment StatService.onResume() || android.app.Fragment missing StatService.onResume()");
    }

    public void a(Context context) {
        this.n = new cl(this, context);
        this.m.postDelayed(this.n, (long) c());
    }

    public void f() {
        Runnable runnable = this.n;
        this.n = null;
        if (runnable != null) {
            this.m.removeCallbacks(runnable);
        }
    }

    private void a(Context context, boolean z) {
        if (this.i.c() > 0) {
            String jSONObject = this.i.d().toString();
            db.a("new session: " + jSONObject);
            DataCore.instance().putSession(jSONObject);
            DataCore.instance().flush(context);
            this.i.d(0);
        }
        if (z) {
            d();
        }
        DataCore.instance().saveLogDataToSend(context, z, false);
        by.a().a(context);
        b(context);
    }

    private void c(Context context) {
        String jSONObject = this.i.d().toString();
        this.k = jSONObject.getBytes().length;
        db.a("cacheString = " + jSONObject);
        cu.a(context, de.q(context) + Config.LAST_SESSION_FILE_NAME, jSONObject, false);
    }

    public void b(Context context) {
        if (context == null) {
            db.a("clearLastSession context is null, invalid");
            return;
        }
        cu.a(context, de.q(context) + Config.LAST_SESSION_FILE_NAME, new JSONObject().toString(), false);
    }

    static Context a(Object obj) {
        try {
            return (Context) obj.getClass().getMethod("getActivity", new Class[0]).invoke(obj, new Object[0]);
        } catch (Throwable th) {
            db.a(th.getMessage());
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r4) {
        /*
        r3 = this;
        r1 = o;
        monitor-enter(r1);
        if (r4 != 0) goto L_0x000c;
    L_0x0005:
        r0 = "page Object is null";
        com.baidu.mobstat.db.c(r0);	 Catch:{ all -> 0x0020 }
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
    L_0x000b:
        return;
    L_0x000c:
        r0 = new com.baidu.mobstat.cm;	 Catch:{ all -> 0x0020 }
        r0.<init>(r4);	 Catch:{ all -> 0x0020 }
        r2 = o;	 Catch:{ all -> 0x0020 }
        r2 = r2.containsKey(r4);	 Catch:{ all -> 0x0020 }
        if (r2 != 0) goto L_0x001e;
    L_0x0019:
        r2 = o;	 Catch:{ all -> 0x0020 }
        r2.put(r4, r0);	 Catch:{ all -> 0x0020 }
    L_0x001e:
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x000b;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ch.a(java.lang.String):void");
    }

    private cm b(String str) {
        cm cmVar;
        synchronized (o) {
            if (!o.containsKey(str)) {
                a(str);
            }
            cmVar = (cm) o.get(str);
        }
        return cmVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.lang.String r3) {
        /*
        r2 = this;
        r1 = o;
        monitor-enter(r1);
        if (r3 != 0) goto L_0x000c;
    L_0x0005:
        r0 = "pageName is null";
        com.baidu.mobstat.db.c(r0);	 Catch:{ all -> 0x001b }
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x000b:
        return;
    L_0x000c:
        r0 = o;	 Catch:{ all -> 0x001b }
        r0 = r0.containsKey(r3);	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0019;
    L_0x0014:
        r0 = o;	 Catch:{ all -> 0x001b }
        r0.remove(r3);	 Catch:{ all -> 0x001b }
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x000b;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ch.c(java.lang.String):void");
    }
}
