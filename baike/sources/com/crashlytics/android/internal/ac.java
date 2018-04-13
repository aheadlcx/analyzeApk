package com.crashlytics.android.internal;

import android.app.Activity;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

class ac implements C0004ak {
    be a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final ScheduledExecutorService j;

    public ac(String str, String str2, String str3, String str4, String str5, String str6, String str7, o oVar, C0011av c0011av) {
        this(str, str2, str3, str4, str5, str6, str7, oVar, ah.b("Crashlytics SAM"), c0011av);
    }

    ac(String str, String str2, String str3, String str4, String str5, String str6, String str7, o oVar, ScheduledExecutorService scheduledExecutorService, C0011av c0011av) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = UUID.randomUUID().toString();
        this.j = scheduledExecutorService;
        this.a = new n(scheduledExecutorService, oVar, c0011av);
        oVar.a((C0004ak) this);
    }

    public final void a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        try {
            this.j.submit(new al(this, str)).get();
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to run analytics task");
        }
    }

    public final void b() {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, bg.INSTALL, new HashMap()), true);
    }

    public final void a(Activity activity) {
        a(bg.CREATE, activity, false);
    }

    public final void b(Activity activity) {
        a(bg.DESTROY, activity, false);
    }

    public final void b(String str) {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, bg.ERROR, Collections.singletonMap("sessionId", str)), false);
    }

    public final void c(Activity activity) {
        a(bg.PAUSE, activity, false);
    }

    public final void d(Activity activity) {
        a(bg.RESUME, activity, false);
    }

    public final void e(Activity activity) {
        a(bg.SAVE_INSTANCE_STATE, activity, false);
    }

    public final void f(Activity activity) {
        a(bg.START, activity, false);
    }

    public final void g(Activity activity) {
        a(bg.STOP, activity, false);
    }

    private void a(bg bgVar, Activity activity, boolean z) {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, bgVar, Collections.singletonMap("activity", activity.getClass().getName())), false);
    }

    private void a(bf bfVar, boolean z) {
        a(new at(this, bfVar, z));
    }

    final void a(aK aKVar, String str) {
        a(new bb(this, aKVar, str));
    }

    public final void c() {
        a(new bc(this));
    }

    void a() {
        a(new bd(this));
    }

    private void a(Runnable runnable) {
        try {
            this.j.submit(runnable);
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to submit analytics task");
        }
    }
}
