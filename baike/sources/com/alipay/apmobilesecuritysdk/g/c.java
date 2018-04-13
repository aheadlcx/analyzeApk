package com.alipay.apmobilesecuritysdk.g;

import android.os.Process;

final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.a.c.isEmpty()) {
                Runnable runnable = (Runnable) this.a.c.get(0);
                this.a.c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception e) {
        } finally {
            this.a.b = null;
        }
    }
}
