package com.alibaba.mtl.appmonitor;

class AppMonitor$Counter$3 implements Runnable {
    final /* synthetic */ double d;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$Counter$3(String str, String str2, double d) {
        this.i = str;
        this.j = str2;
        this.d = d;
    }

    public void run() {
        try {
            AppMonitor.f4a.counter_commit1(this.i, this.j, this.d);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
