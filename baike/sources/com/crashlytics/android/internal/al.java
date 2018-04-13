package com.crashlytics.android.internal;

import java.util.Collections;

final class al implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ ac b;

    al(ac acVar, String str) {
        this.b = acVar;
        this.a = str;
    }

    public final void run() {
        try {
            this.b.a.a(bf.a(this.b.b, this.b.i, this.b.c, this.b.d, this.b.e, this.b.f, this.b.g, this.b.h, bg.CRASH, Collections.singletonMap("sessionId", this.a)));
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to record crash event");
        }
    }
}
