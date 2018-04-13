package com.alibaba.mtl.appmonitor;

class AppMonitor$8 implements Runnable {
    final /* synthetic */ String h;

    AppMonitor$8(String str) {
        this.h = str;
    }

    public void run() {
        try {
            AppMonitor.f4a.setChannel(this.h);
        } catch (Throwable th) {
        }
    }
}
