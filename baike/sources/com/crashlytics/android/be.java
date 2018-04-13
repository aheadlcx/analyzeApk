package com.crashlytics.android;

import java.util.concurrent.Callable;

final class be implements Callable<Void> {
    private /* synthetic */ ba a;

    be(ba baVar) {
        this.a = baVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!this.a.g()) {
            this.a.l();
        }
        return null;
    }
}
