package com.crashlytics.android.internal;

import android.os.Looper;

final class ci implements m {
    ci() {
    }

    public final void a(b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Event bus " + bVar + " accessed from non-main thread " + Looper.myLooper());
        }
    }
}
