package com.budejie.www.util;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import java.io.File;

class f$2 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ Handler c;
    final /* synthetic */ f d;

    f$2(f fVar, String str, Context context, Handler handler) {
        this.d = fVar;
        this.a = str;
        this.b = context;
        this.c = handler;
    }

    public void run() {
        if (this.a == null) {
            Thread.currentThread().interrupt();
            f.a(this.d).shutdown();
            return;
        }
        String str = "";
        if (!TextUtils.isEmpty(this.a)) {
            str = this.a.substring(7).replace("/", "-");
        }
        File file = new File(this.b.getCacheDir(), str);
        if (!file.exists()) {
            f.a(this.d, file, this.a, this.c);
        }
        if (file.exists()) {
            f.b(this.d, file, this.a, this.c);
        }
        System.gc();
    }
}
