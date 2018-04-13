package com.alibaba.mtl.appmonitor;

class AppMonitor$10 implements Runnable {
    AppMonitor$10() {
    }

    public void run() {
        try {
            AppMonitor.f4a.triggerUpload();
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
