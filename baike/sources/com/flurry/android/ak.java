package com.flurry.android;

import android.content.Context;
import android.os.Handler;

final class ak implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ p c;
    final /* synthetic */ u d;

    ak(u uVar, String str, Context context, p pVar) {
        this.d = uVar;
        this.a = str;
        this.b = context;
        this.c = pVar;
    }

    public final void run() {
        new Handler().post(new m(this, this.d.d(this.a)));
    }
}
