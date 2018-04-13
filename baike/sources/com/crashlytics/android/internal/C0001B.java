package com.crashlytics.android.internal;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.crashlytics.android.internal.B */
public final class C0001B extends b {
    private final Handler b = new Handler(Looper.getMainLooper());

    public C0001B(m mVar) {
        super(mVar);
    }

    public final void c(Object obj) {
        if (obj instanceof t) {
            t tVar = (t) obj;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.c(tVar);
            } else {
                this.b.post(new c(this, tVar));
            }
        } else if ((obj instanceof s) || (obj instanceof f)) {
            super.c(obj);
        } else {
            throw new IllegalArgumentException("Posted argument must implement Event interface");
        }
    }
}
