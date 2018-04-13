package com.loc;

import android.content.Context;
import android.text.TextUtils;

final class ef implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ s c;
    final /* synthetic */ String d;

    ef(Context context, String str, s sVar, String str2) {
        this.a = context;
        this.b = str;
        this.c = sVar;
        this.d = str2;
    }

    public final void run() {
        try {
            Context context = this.a;
            ad d = x.d(1);
            if (TextUtils.isEmpty(this.b)) {
                d.a(this.c, this.a, new Throwable("gpsstatistics"), this.d, null, null);
            } else {
                d.a(this.c, this.a, this.b, this.d, null, null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
