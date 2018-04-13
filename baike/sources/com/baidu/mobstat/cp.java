package com.baidu.mobstat;

import android.content.Context;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;

class cp implements Runnable {
    final /* synthetic */ ch a;
    private long b;
    private long c;
    private WeakReference<Context> d;
    private WeakReference<Fragment> e;
    private WeakReference<Object> f;
    private long g;
    private int h;
    private int i = 1;

    public cp(ch chVar, long j, long j2, long j3, Context context, Fragment fragment, Object obj, int i, int i2) {
        this.a = chVar;
        this.b = j;
        this.c = j2;
        this.d = new WeakReference(context);
        this.e = new WeakReference(fragment);
        this.f = new WeakReference(obj);
        this.g = j3;
        this.h = i;
        this.i = i2;
    }

    public void run() {
        Context context = (Context) this.d.get();
        Fragment fragment = (Fragment) this.e.get();
        Object obj = this.f.get();
        if (context != null || fragment != null || obj != null) {
            Context context2;
            if (this.i == 1) {
                context2 = context;
            } else if (this.i == 2) {
                context2 = fragment.getActivity();
            } else if (this.i == 3) {
                context2 = ch.a(obj);
            } else {
                context2 = null;
            }
            if (context2 != null) {
                boolean z;
                if (this.c - this.b >= ((long) this.a.c())) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return;
                }
                if (this.b > 0) {
                    if (this.i == 3 || this.i == 2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        this.a.i.d(this.b);
                    }
                    this.a.a(context2, true);
                    this.a.a(this.g);
                    this.a.b(this.h);
                } else if (this.b == 0) {
                    this.a.b(this.h);
                }
            }
        }
    }
}
