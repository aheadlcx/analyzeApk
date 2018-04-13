package com.alibaba.mtl.appmonitor;

class AppMonitor$12 implements Runnable {
    final /* synthetic */ int c;

    AppMonitor$12(int i) {
        this.c = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.setSampling(this.c);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
