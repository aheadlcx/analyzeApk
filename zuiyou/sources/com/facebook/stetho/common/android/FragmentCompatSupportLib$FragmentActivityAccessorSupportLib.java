package com.facebook.stetho.common.android;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import javax.annotation.Nullable;

class FragmentCompatSupportLib$FragmentActivityAccessorSupportLib implements FragmentActivityAccessor<FragmentActivity, FragmentManager> {
    private FragmentCompatSupportLib$FragmentActivityAccessorSupportLib() {
    }

    @Nullable
    public FragmentManager getFragmentManager(FragmentActivity fragmentActivity) {
        return fragmentActivity.getSupportFragmentManager();
    }
}
