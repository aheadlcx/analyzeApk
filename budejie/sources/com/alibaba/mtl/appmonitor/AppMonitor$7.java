package com.alibaba.mtl.appmonitor;

class AppMonitor$7 implements Runnable {
    final /* synthetic */ boolean d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;

    AppMonitor$7(boolean z, String str, String str2, String str3) {
        this.d = z;
        this.e = str;
        this.f = str2;
        this.g = str3;
    }

    public void run() {
        try {
            AppMonitor.f4a.setRequestAuthInfo(this.d, this.e, this.f, this.g);
        } catch (Throwable th) {
        }
    }
}
