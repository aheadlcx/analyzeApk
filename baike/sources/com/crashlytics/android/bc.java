package com.crashlytics.android;

import java.util.concurrent.Callable;

final class bc implements Callable<Void> {
    private /* synthetic */ long a;
    private /* synthetic */ String b;
    private /* synthetic */ ba c;

    bc(ba baVar, long j, String str) {
        this.c = baVar;
        this.a = j;
        this.b = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!this.c.m.get()) {
            if (this.c.u == null) {
                this.c.k();
            }
            ba baVar = this.c;
            ba.a(this.c.u, 65536, this.a, this.b);
        }
        return null;
    }
}
