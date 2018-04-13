package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.proguard.x;

final class f implements Runnable {
    private /* synthetic */ BuglyBroadcastRecevier a;
    private /* synthetic */ BuglyBroadcastRecevier b;

    f(BuglyBroadcastRecevier buglyBroadcastRecevier, BuglyBroadcastRecevier buglyBroadcastRecevier2) {
        this.b = buglyBroadcastRecevier;
        this.a = buglyBroadcastRecevier2;
    }

    public final void run() {
        try {
            x.a(BuglyBroadcastRecevier.d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
            synchronized (this.a) {
                this.b.b.registerReceiver(BuglyBroadcastRecevier.d, this.b.a);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
