package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$1 implements Runnable {
    final /* synthetic */ int b;

    AppMonitor$Alarm$1(int i) {
        this.b = i;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_setStatisticsInterval(this.b);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
