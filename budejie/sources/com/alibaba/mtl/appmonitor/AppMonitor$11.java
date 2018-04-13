package com.alibaba.mtl.appmonitor;

class AppMonitor$11 implements Runnable {
    final /* synthetic */ int b;

    AppMonitor$11(int i) {
        this.b = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.setStatisticsInterval1(this.b);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
