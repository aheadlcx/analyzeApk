package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$2 implements Runnable {
    final /* synthetic */ int c;

    AppMonitor$Alarm$2(int i) {
        this.c = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_setSampling(this.c);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
