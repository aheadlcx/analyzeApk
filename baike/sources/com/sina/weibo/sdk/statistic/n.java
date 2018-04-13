package com.sina.weibo.sdk.statistic;

import android.content.Context;
import java.util.TimerTask;

class n extends TimerTask {
    final /* synthetic */ Context a;
    final /* synthetic */ k b;

    n(k kVar, Context context) {
        this.b = kVar;
        this.a = context;
    }

    public void run() {
        f.uploadAppLogs(this.a, this.b.a());
    }
}
