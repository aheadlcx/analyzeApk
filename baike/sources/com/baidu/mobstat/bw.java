package com.baidu.mobstat;

import android.content.Context;

class bw implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ bv b;

    bw(bv bvVar, Context context) {
        this.b = bvVar;
        this.a = context;
    }

    public void run() {
        try {
            if (!ao.b(this.a)) {
                ao.a(2).a(this.a);
            }
        } catch (Throwable th) {
        }
        this.b.e = false;
    }
}
