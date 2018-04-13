package com.alibaba.mtl.appmonitor;

class AppMonitor$Counter$2 implements Runnable {
    final /* synthetic */ int c;

    AppMonitor$Counter$2(int i) {
        this.c = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.counter_setSampling(this.c);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
