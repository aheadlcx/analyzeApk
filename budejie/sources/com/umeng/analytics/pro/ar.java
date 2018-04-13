package com.umeng.analytics.pro;

import android.content.Context;

public final class ar implements au {
    private static ar c;
    private au a = new aq(this.b);
    private Context b;

    private ar(Context context) {
        this.b = context;
    }

    public synchronized aq a(Context context) {
        return (aq) this.a;
    }

    public static synchronized ar b(Context context) {
        ar arVar;
        synchronized (ar.class) {
            if (c == null && context != null) {
                c = new ar(context);
            }
            arVar = c;
        }
        return arVar;
    }

    public void a(au auVar) {
        this.a = auVar;
    }

    public void a(final Object obj) {
        bz.b(new cb(this) {
            final /* synthetic */ ar b;

            public void a() {
                this.b.a.a(obj);
            }
        });
    }

    public void a() {
        bz.b(new cb(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.a();
            }
        });
    }

    public void b() {
        bz.c(new cb(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.b();
            }
        });
    }
}
