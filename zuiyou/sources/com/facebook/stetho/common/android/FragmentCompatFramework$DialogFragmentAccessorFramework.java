package com.facebook.stetho.common.android;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

class FragmentCompatFramework$DialogFragmentAccessorFramework implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> {
    private final FragmentAccessor<Fragment, FragmentManager> mFragmentAccessor;

    public FragmentCompatFramework$DialogFragmentAccessorFramework(FragmentAccessor<Fragment, FragmentManager> fragmentAccessor) {
        this.mFragmentAccessor = fragmentAccessor;
    }

    public Dialog getDialog(DialogFragment dialogFragment) {
        return dialogFragment.getDialog();
    }

    @Nullable
    public FragmentManager getFragmentManager(Fragment fragment) {
        return (FragmentManager) this.mFragmentAccessor.getFragmentManager(fragment);
    }

    public Resources getResources(Fragment fragment) {
        return this.mFragmentAccessor.getResources(fragment);
    }

    public int getId(Fragment fragment) {
        return this.mFragmentAccessor.getId(fragment);
    }

    @Nullable
    public String getTag(Fragment fragment) {
        return this.mFragmentAccessor.getTag(fragment);
    }

    @Nullable
    public View getView(Fragment fragment) {
        return this.mFragmentAccessor.getView(fragment);
    }

    @Nullable
    public FragmentManager getChildFragmentManager(Fragment fragment) {
        return (FragmentManager) this.mFragmentAccessor.getChildFragmentManager(fragment);
    }
}
