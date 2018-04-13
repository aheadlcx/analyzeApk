package com.alibaba.mtl.appmonitor;

class AppMonitor$OffLineCounter$2 implements Runnable {
    final /* synthetic */ int c;

    AppMonitor$OffLineCounter$2(int i) {
        this.c = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.offlinecounter_setSampling(this.c);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
