package com.crashlytics.android;

import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;

final class aq implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    aq(Crashlytics crashlytics) {
        this.a = crashlytics;
    }

    public final /* synthetic */ Object a(aX aXVar) {
        boolean z = false;
        if (!aXVar.d.a) {
            return Boolean.valueOf(false);
        }
        Crashlytics crashlytics = this.a;
        if (!Crashlytics.k()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
