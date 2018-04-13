package com.elves.update;

import android.content.Context;
import android.os.Handler;

class b$2 extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;

    b$2(Context context, String str, Handler handler) {
        this.a = context;
        this.b = str;
        this.c = handler;
    }

    public void run() {
        this.c.sendMessage(this.c.obtainMessage(1, b.a(this.a, this.b)));
    }
}
