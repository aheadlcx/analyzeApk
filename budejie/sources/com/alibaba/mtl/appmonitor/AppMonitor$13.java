package com.alibaba.mtl.appmonitor;

class AppMonitor$13 implements Runnable {
    final /* synthetic */ boolean f;

    AppMonitor$13(boolean z) {
        this.f = z;
    }

    public void run() {
        try {
            AppMonitor.f4a.enableLog(this.f);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
