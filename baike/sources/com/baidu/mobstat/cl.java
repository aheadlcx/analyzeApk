package com.baidu.mobstat;

import android.content.Context;

class cl implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ ch b;

    cl(ch chVar, Context context) {
        this.b = chVar;
        this.a = context;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b.f > 0 && currentTimeMillis - this.b.f > ((long) this.b.c())) {
            this.b.a(this.a, false);
        }
    }
}
