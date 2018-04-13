package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class h implements ActivityLifecycleCallbacks {
    private /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.a.a(activity);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.a.b(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.a.c(activity);
    }

    public final void onActivityResumed(Activity activity) {
        this.a.d(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.a.e(activity);
    }

    public final void onActivityStarted(Activity activity) {
        this.a.f(activity);
    }

    public final void onActivityStopped(Activity activity) {
        this.a.g(activity);
    }
}
