package com.handmark.pulltorefresh.library.view;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

final /* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[Mode.values().length];

    static {
        try {
            a[Mode.PULL_FROM_START.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Mode.PULL_FROM_END.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
