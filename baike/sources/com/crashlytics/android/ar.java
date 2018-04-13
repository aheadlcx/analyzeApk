package com.crashlytics.android;

import android.app.Activity;
import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.v;

final class ar implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    ar(Crashlytics crashlytics) {
        this.a = crashlytics;
    }

    public final /* synthetic */ Object a(aX aXVar) {
        boolean z = true;
        Activity e = v.a().e();
        if (!(e == null || e.isFinishing() || !this.a.j())) {
            z = Crashlytics.a(this.a, e, aXVar.c);
        }
        return Boolean.valueOf(z);
    }
}
