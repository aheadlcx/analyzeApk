package com.baidu.mobstat;

class ci implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ ch b;

    ci(ch chVar, long j) {
        this.b = chVar;
        this.a = j;
    }

    public void run() {
        this.b.a(this.a);
    }
}
