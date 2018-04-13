package com.alibaba.mtl.appmonitor;

class AppMonitor$17 implements Runnable {
    final /* synthetic */ double a;
    final /* synthetic */ double b;
    final /* synthetic */ double c;
    final /* synthetic */ String i;
    final /* synthetic */ String j;
    final /* synthetic */ String k;

    AppMonitor$17(String str, String str2, String str3, double d, double d2, double d3) {
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.a = d;
        this.b = d2;
        this.c = d3;
    }

    public void run() {
        try {
            AppMonitor.f4a.updateMeasure(this.i, this.j, this.k, this.a, this.b, this.c);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
