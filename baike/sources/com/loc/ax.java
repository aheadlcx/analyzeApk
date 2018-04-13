package com.loc;

import android.content.Context;

final class ax implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    ax(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void run() {
        try {
            ay.a(new af(this.a, bb.b()), this.a, this.b);
        } catch (Throwable th) {
            w.a(th, "InstanceFactory", "rollBack");
        }
    }
}
