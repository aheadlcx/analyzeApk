package com.handmark.pulltorefresh.library;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

final /* synthetic */ class f {
    static final /* synthetic */ int[] a = new int[Mode.values().length];

    static {
        try {
            a[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Mode.PULL_FROM_END.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Mode.PULL_FROM_START.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
