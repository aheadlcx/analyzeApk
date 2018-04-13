package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$4 implements Runnable {
    final /* synthetic */ String i;
    final /* synthetic */ String j;
    final /* synthetic */ String l;

    AppMonitor$Alarm$4(String str, String str2, String str3) {
        this.i = str;
        this.j = str2;
        this.l = str3;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_commitSuccess2(this.i, this.j, this.l);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
