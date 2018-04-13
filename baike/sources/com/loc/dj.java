package com.loc;

import android.content.Context;

final class dj implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ di d;

    dj(di diVar, Context context, String str, String str2) {
        this.d = diVar;
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public final void run() {
        try {
            this.d.a(this.a, this.b, this.c);
        } catch (Throwable th) {
            w.a(th, "dLoader", "run()");
        }
    }
}
