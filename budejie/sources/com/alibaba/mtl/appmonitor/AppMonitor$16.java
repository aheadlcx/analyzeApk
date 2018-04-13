package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.log.e.i;

class AppMonitor$16 implements Runnable {
    final /* synthetic */ DimensionSet a;
    /* renamed from: a */
    final /* synthetic */ MeasureSet f15a;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$16(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) {
        this.i = str;
        this.j = str2;
        this.f15a = measureSet;
        this.a = dimensionSet;
    }

    public void run() {
        try {
            i.a(AppMonitor.TAG, "[register]:", AppMonitor.f4a);
            AppMonitor.f4a.register3(this.i, this.j, this.f15a, this.a);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
