package com.alibaba.mtl.appmonitor;

class AppMonitor$Counter$4 implements Runnable {
    final /* synthetic */ double d;
    final /* synthetic */ String i;
    final /* synthetic */ String j;
    final /* synthetic */ String l;

    AppMonitor$Counter$4(String str, String str2, String str3, double d) {
        this.i = str;
        this.j = str2;
        this.l = str3;
        this.d = d;
    }

    public void run() {
        try {
            AppMonitor.f4a.counter_commit2(this.i, this.j, this.l, this.d);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
