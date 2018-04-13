package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.MeasureSet;

class AppMonitor$15 implements Runnable {
    final /* synthetic */ MeasureSet a;
    final /* synthetic */ boolean e;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$15(String str, String str2, MeasureSet measureSet, boolean z) {
        this.i = str;
        this.j = str2;
        this.a = measureSet;
        this.e = z;
    }

    public void run() {
        try {
            AppMonitor.f4a.register2(this.i, this.j, this.a, this.e);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
