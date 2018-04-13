package com.crashlytics.android;

import java.util.Date;
import java.util.concurrent.Callable;

final class n implements Callable<Void> {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ ba d;

    n(ba baVar, Date date, Thread thread, Throwable th) {
        this.d = baVar;
        this.a = date;
        this.b = thread;
        this.c = th;
    }

    public final /* synthetic */ Object call() throws Exception {
        ba.a(this.d, this.a, this.b, this.c);
        return null;
    }
}
