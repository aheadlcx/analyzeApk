package com.alibaba.mtl.appmonitor;

class AppMonitor$1 implements Runnable {
    AppMonitor$1() {
    }

    public void run() {
        try {
            AppMonitor.f4a.destroy();
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
