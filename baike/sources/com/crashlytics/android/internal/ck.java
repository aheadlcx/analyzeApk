package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class ck implements ActivityLifecycleCallbacks {
    private /* synthetic */ cj a;

    ck(cj cjVar) {
        this.a = cjVar;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.a.a.a(activity);
    }

    public final void onActivityStarted(Activity activity) {
        this.a.a.a(activity);
    }

    public final void onActivityResumed(Activity activity) {
        this.a.a.a(activity);
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }
}
