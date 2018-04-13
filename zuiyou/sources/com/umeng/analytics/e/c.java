package com.umeng.analytics.e;

import android.content.Context;
import com.umeng.a.b;
import com.umeng.a.k;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.c.h;
import com.umeng.analytics.c.h.a;
import com.umeng.analytics.d.l;
import com.umeng.analytics.d.q;

public class c implements l {
    private static c l = null;
    private final long a = 1296000000;
    private final long b = 129600000;
    private final int c = 1800000;
    private final int d = 10000;
    private k e;
    private q f;
    private long g = 1296000000;
    private int h = 10000;
    private long i = 0;
    private long j = 0;
    private Context k;

    public static synchronized c a(Context context, q qVar) {
        c cVar;
        synchronized (c.class) {
            if (l == null) {
                l = new c(context, qVar);
                l.a(h.a(context).b());
            }
            cVar = l;
        }
        return cVar;
    }

    private c(Context context, q qVar) {
        this.k = context;
        this.e = k.a(context);
        this.f = qVar;
    }

    public boolean a() {
        if (this.e.g() || this.f.f()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f.m();
        if (currentTimeMillis > this.g) {
            this.i = (long) b.a(this.h, com.umeng.analytics.c.c.a(this.k));
            this.j = currentTimeMillis;
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            this.i = 0;
            this.j = currentTimeMillis;
            return true;
        }
    }

    public long b() {
        return this.i;
    }

    public long c() {
        return this.j;
    }

    public void a(a aVar) {
        this.g = aVar.a(1296000000);
        int b = aVar.b(0);
        if (b != 0) {
            this.h = b;
        } else if (AnalyticsConfig.sLatentWindow <= 0 || AnalyticsConfig.sLatentWindow > 1800000) {
            this.h = 10000;
        } else {
            this.h = AnalyticsConfig.sLatentWindow;
        }
    }
}
