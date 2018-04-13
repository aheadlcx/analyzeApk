package com.tencent.bugly.proguard;

class ac$2 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ac b;

    ac$2(ac acVar, int i) {
        this.b = acVar;
        this.a = i;
    }

    public void run() {
        ac.c(this.b).edit().putBoolean(this.a + "_" + ac.a(this.b), !ac.b(this.b, this.a)).commit();
    }
}
