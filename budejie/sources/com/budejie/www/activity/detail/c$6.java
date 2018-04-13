package com.budejie.www.activity.detail;

class c$6 extends Thread {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;

    c$6(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public void run() {
        try {
            Thread.sleep(3000);
            c.G(this.b).sendMessage(c.G(this.b).obtainMessage(931, Boolean.valueOf(this.a)));
        } catch (InterruptedException e) {
        }
    }
}
