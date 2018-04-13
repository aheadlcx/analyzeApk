package com.alibaba.mtl.appmonitor;

class AppMonitor$OffLineCounter$1 implements Runnable {
    final /* synthetic */ int b;

    AppMonitor$OffLineCounter$1(int i) {
        this.b = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.offlinecounter_setStatisticsInterval(this.b);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
