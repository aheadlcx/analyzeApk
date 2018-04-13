package com.crashlytics.android;

import com.crashlytics.android.internal.v;

final class g implements Runnable {
    private /* synthetic */ Runnable a;

    g(ba baVar, Runnable runnable) {
        this.a = runnable;
    }

    public final void run() {
        try {
            this.a.run();
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e);
        }
    }
}
