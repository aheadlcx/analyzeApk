package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.log.e.i;

class AppMonitor$9 implements Runnable {
    final /* synthetic */ DimensionSet a;
    /* renamed from: a */
    final /* synthetic */ MeasureSet f16a;
    final /* synthetic */ boolean e;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$9(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        this.i = str;
        this.j = str2;
        this.f16a = measureSet;
        this.a = dimensionSet;
        this.e = z;
    }

    public void run() {
        try {
            i.a(AppMonitor.TAG, "register stat event. module: ", this.i, " monitorPoint: ", this.j);
            AppMonitor.f4a.register4(this.i, this.j, this.f16a, this.a, this.e);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
