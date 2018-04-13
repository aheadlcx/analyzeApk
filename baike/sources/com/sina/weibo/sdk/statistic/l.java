package com.sina.weibo.sdk.statistic;

import android.content.Context;

class l implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ k b;

    l(k kVar, Context context) {
        this.b = kVar;
        this.a = context;
    }

    public void run() {
        f.uploadAppLogs(this.a, this.b.a());
    }
}
