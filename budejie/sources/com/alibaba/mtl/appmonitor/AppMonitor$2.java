package com.alibaba.mtl.appmonitor;

class AppMonitor$2 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;

    AppMonitor$2(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void run() {
        try {
            AppMonitor.f4a.setStatisticsInterval2(this.a, this.b);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
