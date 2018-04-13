package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.os.Looper;

final class bc implements Runnable {
    final /* synthetic */ Context a;

    bc(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            Looper.prepare();
            d dVar = new d(this.a);
            dVar.a(new bd(this, dVar));
            Looper.loop();
        } catch (Throwable th) {
            b.a(this.a, th);
        }
    }
}
