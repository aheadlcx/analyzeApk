package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.MeasureSet;

class AppMonitor$14 implements Runnable {
    final /* synthetic */ MeasureSet a;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$14(String str, String str2, MeasureSet measureSet) {
        this.i = str;
        this.j = str2;
        this.a = measureSet;
    }

    public void run() {
        try {
            AppMonitor.f4a.register1(this.i, this.j, this.a);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
