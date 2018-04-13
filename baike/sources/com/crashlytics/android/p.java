package com.crashlytics.android;

import java.util.Date;

final class p implements Runnable {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ ba d;

    p(ba baVar, Date date, Thread thread, Throwable th) {
        this.d = baVar;
        this.a = date;
        this.b = thread;
        this.c = th;
    }

    public final void run() {
        if (!this.d.m.get()) {
            ba.b(this.d, this.a, this.b, this.c);
        }
    }
}
