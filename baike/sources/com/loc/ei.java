package com.loc;

import android.content.Context;
import android.os.Looper;

final class ei implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ s b;
    final /* synthetic */ boolean c;
    final /* synthetic */ z d;

    ei(z zVar, Context context, s sVar, boolean z) {
        this.d = zVar;
        this.a = context;
        this.b = sVar;
        this.c = z;
    }

    public final void run() {
        try {
            synchronized (Looper.getMainLooper()) {
                new ap(this.a, true).a(this.b);
            }
            if (this.c) {
                synchronized (Looper.getMainLooper()) {
                    aq aqVar = new aq(this.a);
                    ar arVar = new ar();
                    arVar.c(true);
                    arVar.a(true);
                    arVar.b(true);
                    aqVar.a(arVar);
                }
                x.a(this.d.d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
