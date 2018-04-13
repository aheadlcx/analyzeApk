package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import javax.annotation.Nullable;

@TargetApi(17)
class FragmentCompatFramework$FragmentAccessorFrameworkJellyBean extends FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb {
    private FragmentCompatFramework$FragmentAccessorFrameworkJellyBean() {
        super();
    }

    @Nullable
    public FragmentManager getChildFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }
}
