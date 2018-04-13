package com.facebook.stetho.common.android;

import android.app.Activity;
import android.app.FragmentManager;
import javax.annotation.Nullable;

class FragmentCompatFramework$FragmentActivityAccessorFramework implements FragmentActivityAccessor<Activity, FragmentManager> {
    private FragmentCompatFramework$FragmentActivityAccessorFramework() {
    }

    @Nullable
    public FragmentManager getFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }
}
