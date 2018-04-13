package com.crashlytics.android;

import java.util.concurrent.Callable;

final class c implements Callable<Boolean> {
    private /* synthetic */ ba a;

    c(ba baVar) {
        this.a = baVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf(this.a.l.exists());
    }
}
