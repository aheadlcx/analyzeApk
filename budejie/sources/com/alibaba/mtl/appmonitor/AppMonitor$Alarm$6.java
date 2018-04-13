package com.alibaba.mtl.appmonitor;

class AppMonitor$Alarm$6 implements Runnable {
    final /* synthetic */ String i;
    final /* synthetic */ String j;
    final /* synthetic */ String l;
    final /* synthetic */ String m;
    final /* synthetic */ String n;

    AppMonitor$Alarm$6(String str, String str2, String str3, String str4, String str5) {
        this.i = str;
        this.j = str2;
        this.l = str3;
        this.m = str4;
        this.n = str5;
    }

    public void run() {
        try {
            AppMonitor.f4a.alarm_commitFail2(this.i, this.j, this.l, this.m, this.n);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
