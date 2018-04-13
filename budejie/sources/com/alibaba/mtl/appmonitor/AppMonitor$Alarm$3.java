package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$3 implements Runnable {
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$Alarm$3(String str, String str2) {
        this.i = str;
        this.j = str2;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_commitSuccess1(this.i, this.j);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
