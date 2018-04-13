package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.e;

final class q extends Thread {
    final /* synthetic */ String[] a;
    final /* synthetic */ Context b;

    q(String[] strArr, Context context) {
        this.a = strArr;
        this.b = context;
    }

    public void run() {
        super.run();
        this.a[0] = l.c();
        this.a[1] = l.a();
        this.a[2] = l.b();
        e.c("diskType = " + this.a[0] + "; ThremalZone = " + this.a[1] + "; GoldFishRc = " + this.a[2]);
        l.b(this.b, this.a);
    }
}
