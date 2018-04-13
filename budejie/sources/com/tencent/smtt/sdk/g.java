package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;

final class g extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ ai b;
    final /* synthetic */ Handler c;

    g(Context context, ai aiVar, Handler handler) {
        this.a = context;
        this.b = aiVar;
        this.c = handler;
    }

    public void run() {
        if (aj.a().a(true, this.a) == 0) {
            aj.a().b(this.a, true);
        }
        l.a(true).a(this.a, false, false, null);
        bi b = bi.b();
        b.a(this.a, this.b);
        boolean c = b.c();
        this.c.sendEmptyMessage(3);
        if (c) {
            this.c.sendEmptyMessage(1);
        } else {
            this.c.sendEmptyMessage(2);
        }
    }
}
