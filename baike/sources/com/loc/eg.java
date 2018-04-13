package com.loc;

import android.content.Context;

final class eg implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ Throwable c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;

    eg(Context context, int i, Throwable th, String str, String str2) {
        this.a = context;
        this.b = i;
        this.c = th;
        this.d = str;
        this.e = str2;
    }

    public final void run() {
        try {
            Context context = this.a;
            ad d = x.d(this.b);
            if (d != null) {
                d.a(this.a, this.c, this.d, this.e);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
