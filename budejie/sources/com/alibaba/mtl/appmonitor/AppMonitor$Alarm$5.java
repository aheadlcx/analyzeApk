package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$5 implements Runnable {
    final /* synthetic */ String i;
    final /* synthetic */ String j;
    final /* synthetic */ String m;
    final /* synthetic */ String n;

    AppMonitor$Alarm$5(String str, String str2, String str3, String str4) {
        this.i = str;
        this.j = str2;
        this.m = str3;
        this.n = str4;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_commitFail1(this.i, this.j, this.m, this.n);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
