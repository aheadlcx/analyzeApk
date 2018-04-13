package com.baidu.mobstat;

import android.content.Context;

class cj implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ Context b;
    final /* synthetic */ ch c;

    cj(ch chVar, long j, Context context) {
        this.c = chVar;
        this.a = j;
        this.b = context;
    }

    public void run() {
        this.c.b(this.a);
        if (bv.a().c()) {
            this.c.c(this.b);
        }
    }
}
