package com.budejie.www.util;

import android.os.Handler;
import java.io.File;

class f$4 extends Thread {
    final /* synthetic */ File a;
    final /* synthetic */ Handler b;
    final /* synthetic */ String c;
    final /* synthetic */ f d;

    f$4(f fVar, File file, Handler handler, String str) {
        this.d = fVar;
        this.a = file;
        this.b = handler;
        this.c = str;
    }

    public void run() {
        f.a(this.d, this.a, this.b, this.c);
    }
}
