package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

public interface FragmentAccessor<FRAGMENT, FRAGMENT_MANAGER> {
    public static final int NO_ID = 0;

    @Nullable
    FRAGMENT_MANAGER getChildFragmentManager(FRAGMENT fragment);

    @Nullable
    FRAGMENT_MANAGER getFragmentManager(FRAGMENT fragment);

    int getId(FRAGMENT fragment);

    Resources getResources(FRAGMENT fragment);

    @Nullable
    String getTag(FRAGMENT fragment);

    @Nullable
    View getView(FRAGMENT fragment);
}
