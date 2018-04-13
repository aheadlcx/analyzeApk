package com.alibaba.mtl.appmonitor;

class AppMonitor$4 implements Runnable {
    AppMonitor$4() {
    }

    public void run() {
        try {
            AppMonitor.f4a.turnOffRealTimeDebug();
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
