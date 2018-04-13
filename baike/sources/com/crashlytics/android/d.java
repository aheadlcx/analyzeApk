package com.crashlytics.android;

import com.crashlytics.android.internal.aa;
import java.io.File;

final class d extends aa {
    private /* synthetic */ File a;

    d(ba baVar, File file) {
        this.a = file;
    }

    public final void a() {
        v q = Crashlytics.getInstance().q();
        if (q != null) {
            new ab(q).a(new z(this.a, ba.e));
        }
    }
}
