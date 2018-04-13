package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;

final class e extends aa {
    private /* synthetic */ D a;

    e(D d) {
        this.a = d;
    }

    public final void a() {
        try {
            D.a(this.a);
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Problem encountered during Crashlytics initialization.", e);
        }
    }
}
