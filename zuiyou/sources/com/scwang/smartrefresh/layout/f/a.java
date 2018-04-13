package com.scwang.smartrefresh.layout.f;

public class a implements Runnable {
    public long a;
    public Runnable b = null;

    public a(Runnable runnable) {
        this.b = runnable;
    }

    public a(Runnable runnable, long j) {
        this.b = runnable;
        this.a = j;
    }

    public void run() {
        try {
            if (this.b != null) {
                this.b.run();
                this.b = null;
            }
        } catch (Throwable th) {
        }
    }
}
