package com.scwang.smartrefresh.layout.footer;

import com.scwang.smartrefresh.layout.constant.RefreshState;

/* synthetic */ class ClassicsFooter$1 {
    static final /* synthetic */ int[] a = new int[RefreshState.values().length];

    static {
        try {
            a[RefreshState.None.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[RefreshState.PullToUpLoad.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[RefreshState.Loading.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[RefreshState.LoadReleased.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[RefreshState.ReleaseToLoad.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[RefreshState.Refreshing.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}