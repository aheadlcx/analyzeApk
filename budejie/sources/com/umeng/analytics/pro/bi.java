package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.af.a;

public class bi implements az {
    private static bi l = null;
    private final long a = 1296000000;
    private final long b = 129600000;
    private final int c = 1800000;
    private final int d = 10000;
    private cc e;
    private be f;
    private long g = 1296000000;
    private int h = 10000;
    private long i = 0;
    private long j = 0;
    private Context k;

    public static synchronized bi a(Context context, be beVar) {
        bi biVar;
        synchronized (bi.class) {
            if (l == null) {
                l = new bi(context, beVar);
                l.a(af.a(context).b());
            }
            biVar = l;
        }
        return biVar;
    }

    private bi(Context context, be beVar) {
        this.k = context;
        this.e = cc.a(context);
        this.f = beVar;
    }

    public boolean a() {
        if (this.e.h() || this.f.f()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f.m();
        if (currentTimeMillis > this.g) {
            this.i = (long) bt.a(this.h, aa.a(this.k));
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
