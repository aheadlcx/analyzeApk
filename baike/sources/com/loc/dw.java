package com.loc;

import java.util.TimerTask;

final class dw extends TimerTask {
    final /* synthetic */ int a = 2;
    final /* synthetic */ cg b;

    dw(cg cgVar) {
        this.b = cgVar;
    }

    public final void run() {
        try {
            Thread.currentThread().setPriority(1);
            long b = de.b() - this.b.k;
            if (this.b.j() && this.b.k() == 0) {
                this.b.i();
            }
            if (b >= 10000) {
                if (this.b.i == null || !this.b.i.a(this.b.j)) {
                    this.b.i();
                } else {
                    cg.a(this.b, this.a);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "CollectionManager", "timerTaskU run");
        }
    }
}
