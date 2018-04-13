package com.facebook.stetho.common.android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

class FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb implements FragmentAccessor<Fragment, FragmentManager> {
    private FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb() {
    }

    @Nullable
    public FragmentManager getFragmentManager(Fragment fragment) {
        return fragment.getFragmentManager();
    }

    public Resources getResources(Fragment fragment) {
        return fragment.getResources();
    }

    public int getId(Fragment fragment) {
        return fragment.getId();
    }

    @Nullable
    public String getTag(Fragment fragment) {
        return fragment.getTag();
    }

    @Nullable
    public View getView(Fragment fragment) {
        return fragment.getView();
    }

    @Nullable
    public FragmentManager getChildFragmentManager(Fragment fragment) {
        return null;
    }
}
