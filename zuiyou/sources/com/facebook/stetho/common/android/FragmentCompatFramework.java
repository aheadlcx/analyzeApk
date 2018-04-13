package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build.VERSION;
import com.facebook.stetho.common.android.FragmentCompat.FragmentManagerAccessorViaReflection;

@TargetApi(11)
final class FragmentCompatFramework extends FragmentCompat<Fragment, DialogFragment, FragmentManager, Activity> {
    private static final FragmentCompatFramework$DialogFragmentAccessorFramework sDialogFragmentAccessor = new FragmentCompatFramework$DialogFragmentAccessorFramework(sFragmentAccessor);
    private static final FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb sFragmentAccessor;
    private static final FragmentCompatFramework$FragmentActivityAccessorFramework sFragmentActivityAccessor = new FragmentCompatFramework$FragmentActivityAccessorFramework(null);
    private static final FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentManagerAccessorViaReflection();

    FragmentCompatFramework() {
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            sFragmentAccessor = new FragmentCompatFramework$FragmentAccessorFrameworkJellyBean(null);
        } else {
            sFragmentAccessor = new FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb(null);
        }
    }

    public Class<Fragment> getFragmentClass() {
        return Fragment.class;
    }

    public Class<DialogFragment> getDialogFragmentClass() {
        return DialogFragment.class;
    }

    public Class<Activity> getFragmentActivityClass() {
        return Activity.class;
    }

    public FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb forFragment() {
        return sFragmentAccessor;
    }

    public FragmentCompatFramework$DialogFragmentAccessorFramework forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    public FragmentManagerAccessorViaReflection<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    public FragmentCompatFramework$FragmentActivityAccessorFramework forFragmentActivity() {
        return sFragmentActivityAccessor;
    }
}
