package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

final class bv implements C0000au {
    private boolean a = true;
    private /* synthetic */ StringBuilder b;

    bv(C0010aq c0010aq, StringBuilder stringBuilder) {
        this.b = stringBuilder;
    }

    public final void a(InputStream inputStream, int i) throws IOException {
        if (this.a) {
            this.a = false;
        } else {
            this.b.append(", ");
        }
        this.b.append(i);
    }
}
