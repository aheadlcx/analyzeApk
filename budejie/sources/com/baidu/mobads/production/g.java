package com.baidu.mobads.production;

import android.content.Context;

class g implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    g(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        new Thread(new h(this)).start();
    }
}
