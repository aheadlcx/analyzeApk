package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

final class i implements Callable<T> {
    private /* synthetic */ Callable a;

    i(ba baVar, Callable callable) {
        this.a = callable;
    }

    public final T call() throws Exception {
        try {
            return this.a.call();
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e);
            return null;
        }
    }
}
