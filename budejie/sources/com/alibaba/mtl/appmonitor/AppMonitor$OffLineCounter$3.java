package com.alibaba.mtl.appmonitor;

class AppMonitor$OffLineCounter$3 implements Runnable {
    final /* synthetic */ double d;
    final /* synthetic */ String i;
    final /* synthetic */ String j;

    AppMonitor$OffLineCounter$3(String str, String str2, double d) {
        this.i = str;
        this.j = str2;
        this.d = d;
    }

    public void run() {
        try {
            AppMonitor.f4a.offlinecounter_commit(this.i, this.j, this.d);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
