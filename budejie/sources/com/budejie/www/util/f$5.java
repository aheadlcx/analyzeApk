package com.budejie.www.util;

import android.os.Handler;
import java.io.File;

class f$5 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ File b;
    final /* synthetic */ Handler c;
    final /* synthetic */ f d;

    f$5(f fVar, String str, File file, Handler handler) {
        this.d = fVar;
        this.a = str;
        this.b = file;
        this.c = handler;
    }

    public void run() {
        aa.c("AsyncImageLoader", "executorService is running ! ");
        if (this.a == null) {
            Thread.currentThread().interrupt();
            f.a(this.d).shutdown();
            return;
        }
        if (!an.a.contains(this.a)) {
            an.a.add(this.a);
            if (!this.b.exists()) {
                f.c(this.d, this.b, this.a, this.c);
            }
        }
        if (this.b.exists()) {
            f.a(this.d, this.b, this.c, this.a);
        }
        System.gc();
    }
}
