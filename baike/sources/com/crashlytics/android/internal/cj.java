package com.crashlytics.android.internal;

import android.app.Application;

final class cj {
    final /* synthetic */ v a;

    private cj(v vVar) {
        this.a = vVar;
    }

    static /* synthetic */ void a(cj cjVar, Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(new ck(cjVar));
        }
    }
}
