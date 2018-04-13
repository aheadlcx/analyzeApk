package com.tencent.bugly.proguard;

final class au implements Runnable {
    private /* synthetic */ Runnable a;
    private /* synthetic */ u b;

    au(u uVar, Runnable runnable) {
        this.b = uVar;
        this.a = runnable;
    }

    public final void run() {
        this.a.run();
        synchronized (this.b.j) {
            u.b(this.b);
        }
    }
}
