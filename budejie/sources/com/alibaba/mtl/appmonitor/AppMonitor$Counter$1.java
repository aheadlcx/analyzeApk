package com.alibaba.mtl.appmonitor;

class AppMonitor$Counter$1 implements Runnable {
    final /* synthetic */ int b;

    AppMonitor$Counter$1(int i) {
        this.b = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.counter_setStatisticsInterval(this.b);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
