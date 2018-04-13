package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.text.TextUtils;

class x implements ActivityLifecycleCallbacks {
    final /* synthetic */ h a;

    x(h hVar) {
        this.a = hVar;
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityResumed(Activity activity) {
        if (activity == null) {
            return;
        }
        if (this.a.c) {
            this.a.c = false;
            if (TextUtils.isEmpty(h.a)) {
                h.a = activity.getPackageName() + "." + activity.getLocalClassName();
                return;
            } else if (!h.a.equals(activity.getPackageName() + "." + activity.getLocalClassName())) {
                this.a.b(activity);
                return;
            } else {
                return;
            }
        }
        this.a.b(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.a.c(activity);
        this.a.c = false;
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }
}
