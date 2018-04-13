package com.crashlytics.android;

import com.crashlytics.android.internal.aG;
import java.io.InputStream;

final class ap implements aG {
    private /* synthetic */ PinningInfoProvider a;

    ap(PinningInfoProvider pinningInfoProvider) {
        this.a = pinningInfoProvider;
    }

    public final InputStream a() {
        return this.a.getKeyStoreStream();
    }

    public final String b() {
        return this.a.getKeyStorePassword();
    }

    public final String[] c() {
        return this.a.getPins();
    }
}
