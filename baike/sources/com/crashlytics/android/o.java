package com.crashlytics.android;

import java.util.concurrent.Callable;

final class o implements Callable<Boolean> {
    private /* synthetic */ ba a;

    o(ba baVar) {
        this.a = baVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.a.m.get()) {
            return Boolean.valueOf(false);
        }
        this.a.m();
        this.a.l();
        return Boolean.valueOf(true);
    }
}
