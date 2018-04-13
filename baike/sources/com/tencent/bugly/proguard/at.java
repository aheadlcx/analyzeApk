package com.tencent.bugly.proguard;

final class at implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ n b;

    at(n nVar, int i) {
        this.b = nVar;
        this.a = i;
    }

    public final void run() {
        this.b.f.edit().putBoolean(this.a + "_" + this.b.d, !this.b.b(this.a)).commit();
    }
}
