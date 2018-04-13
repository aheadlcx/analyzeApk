package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.facebook.stetho.inspector.elements.android.ActivityTracker.AutomaticTracker;

@TargetApi(14)
class ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond extends AutomaticTracker {
    private final Application mApplication;
    private final ActivityLifecycleCallbacks mLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond.this.mTracker.add(activity);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
            ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond.this.mTracker.remove(activity);
        }
    };
    private final ActivityTracker mTracker;

    public ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond(Application application, ActivityTracker activityTracker) {
        super(null);
        this.mApplication = application;
        this.mTracker = activityTracker;
    }

    public void register() {
        this.mApplication.registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
    }

    public void unregister() {
        this.mApplication.unregisterActivityLifecycleCallbacks(this.mLifecycleCallbacks);
    }
}
