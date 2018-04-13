package com.microquation.linkedme.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.microquation.linkedme.android.f.b;
import java.lang.ref.WeakReference;

@TargetApi(14)
class a$b implements ActivityLifecycleCallbacks {
    final /* synthetic */ a a;
    private int b;
    private Uri c;
    private boolean d;

    private a$b(a aVar) {
        this.a = aVar;
        this.b = 0;
        this.c = null;
        this.d = false;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        b.a(a.a, "onCreated " + activity.getClass().getSimpleName() + "  activityCnt_ = " + this.b);
        if (this.b < 1 && this.c == null) {
            this.c = activity.getIntent().getData();
        }
        if (this.b < 1 && !this.d) {
            a.d(this.a, a.a(this.a, activity.getIntent()));
            this.d = true;
        }
        if (this.b > 0 && this.d) {
            this.d = false;
        }
    }

    public void onActivityDestroyed(Activity activity) {
        b.a(a.a, "onDestroyed " + activity.getClass().getSimpleName());
        if (a.a(this.a) != null && a.a(this.a).get() == activity) {
            a.a(this.a).clear();
        }
    }

    public void onActivityPaused(Activity activity) {
        b.a(a.a, "onPaused " + activity.getClass().getSimpleName());
    }

    public void onActivityResumed(Activity activity) {
        b.a(a.a, "onResumed " + activity.getClass().getSimpleName() + ",intent=" + activity.getIntent().getDataString());
        a.a(this.a, new WeakReference(activity));
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        b.a(a.a, "onStarted " + activity.getClass().getSimpleName() + "  activityCnt_ = " + this.b + " getIntent() = " + activity.getIntent());
        if (this.b < 1) {
            Uri data;
            a.b(this.a, false);
            if (a.c(this.a) && a.d(this.a) && TextUtils.equals(activity.getClass().getName(), a.e(this.a))) {
                a.c(this.a, true);
            }
            if (activity.getIntent() != null) {
                b.a(a.a, "onStarted--onStarted " + activity.getIntent().getDataString());
                data = activity.getIntent().getData();
                if (data == null) {
                    activity.getIntent().setData(this.c);
                    data = this.c;
                    b.a(a.a, "onStarted--onCreated " + activity.getIntent().getDataString());
                } else if (this.c != null && a.a(this.a, this.c) && this.c.toString().startsWith(activity.getIntent().getDataString())) {
                    activity.getIntent().setData(this.c);
                    data = this.c;
                    b.a(a.a, "Uri Scheme接收页面在onCreate()中调用了finish()方法，同时将Uri Data传递到下一个页面");
                }
                this.c = null;
            } else {
                data = null;
            }
            this.a.a(false);
            this.a.a(data, activity);
        }
        this.b++;
    }

    public void onActivityStopped(Activity activity) {
        b.a(a.a, "onStopped " + activity.getClass().getSimpleName() + "  activityCnt_ = " + this.b);
        this.b--;
        if (this.b < 1) {
            a.c(this.a, false);
            a.a(this.a, activity.getClass().getName());
            if (a.f(this.a)) {
                a.e(this.a, false);
            }
            if (a.g(this.a) != null) {
                a.a(this.a, null);
            }
            if (a.h(this.a) != null) {
                a.b(this.a, null);
            }
            a.i(this.a);
            b.a(a.a, "close session called");
        }
    }
}
