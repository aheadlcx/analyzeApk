package com.baidu.mobads;

class k implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ j b;

    k(j jVar, String str) {
        this.b = jVar;
        this.a = str;
    }

    public void run() {
        this.b.d.onUrl(this.a);
    }
}
