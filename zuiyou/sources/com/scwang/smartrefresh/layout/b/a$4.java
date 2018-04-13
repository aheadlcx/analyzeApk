package com.scwang.smartrefresh.layout.b;

import com.scwang.smartrefresh.layout.constant.RefreshState;

/* synthetic */ class a$4 {
    static final /* synthetic */ int[] a = new int[RefreshState.values().length];

    static {
        try {
            a[RefreshState.None.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[RefreshState.PullDownToRefresh.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[RefreshState.PullToUpLoad.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[RefreshState.Refreshing.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[RefreshState.Loading.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
