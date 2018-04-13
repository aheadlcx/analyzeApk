package com.qiushibaike.statsdk;

import android.content.Context;

class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ b b;

    b(b bVar, Context context) {
        this.b = bVar;
        this.a = context;
    }

    public void run() {
        if (this.b.a.a(this.a, this.b.a.isWifiOnly())) {
            DataObjConstructor.getInstance().flush(this.a);
        }
    }
}
