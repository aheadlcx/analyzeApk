package com.baidu.mobstat;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class bg implements ActivityLifecycleCallbacks {
    final /* synthetic */ bf a;

    bg(bf bfVar) {
        this.a = bfVar;
    }

    public void onActivityResumed(Activity activity) {
        ch.a().a(activity.getApplicationContext(), System.currentTimeMillis());
        if (this.a.c) {
            this.a.a(activity, true);
            this.a.a(activity);
        }
        ch.a().f();
    }

    public void onActivityPaused(Activity activity) {
        ch.a().b(activity.getApplicationContext(), System.currentTimeMillis());
        if (this.a.c) {
            this.a.a(activity, false);
        }
        ch.a().a(activity.getApplicationContext());
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        String name = activity.getClass().getName();
        synchronized (this.a.a) {
            this.a.a.remove(name);
        }
    }
}
