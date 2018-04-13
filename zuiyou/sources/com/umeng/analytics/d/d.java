package com.umeng.analytics.d;

import android.content.Context;
import com.umeng.a.h;
import com.umeng.a.j;

public final class d implements g {
    private static d c;
    private g a = new c(this.b);
    private Context b;

    private d(Context context) {
        this.b = context;
    }

    public synchronized c a(Context context) {
        return (c) this.a;
    }

    public static synchronized d b(Context context) {
        d dVar;
        synchronized (d.class) {
            if (c == null && context != null) {
                c = new d(context);
            }
            dVar = c;
        }
        return dVar;
    }

    public void a(g gVar) {
        this.a = gVar;
    }

    public void a(final Object obj) {
        h.b(new j(this) {
            final /* synthetic */ d b;

            public void a() {
                this.b.a.a(obj);
            }
        });
    }

    public void a() {
        h.b(new j(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.a();
            }
        });
    }

    public void b() {
        h.c(new j(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.b();
            }
        });
    }
}
