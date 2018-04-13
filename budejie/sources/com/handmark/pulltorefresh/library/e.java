package com.handmark.pulltorefresh.library;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;

final /* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[Orientation.values().length];
    static final /* synthetic */ int[] b = new int[State.values().length];
    static final /* synthetic */ int[] c = new int[Mode.values().length];

    static {
        try {
            c[Mode.PULL_FROM_END.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            c[Mode.PULL_FROM_START.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[Mode.BOTH.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[State.RESET.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[State.PULL_TO_REFRESH.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[State.RELEASE_TO_REFRESH.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[State.REFRESHING.ordinal()] = 4;
        } catch (NoSuchFieldError e8) {
        }
        try {
            b[State.MANUAL_REFRESHING.ordinal()] = 5;
        } catch (NoSuchFieldError e9) {
        }
        try {
            b[State.OVERSCROLLING.ordinal()] = 6;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[Orientation.HORIZONTAL.ordinal()] = 1;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[Orientation.VERTICAL.ordinal()] = 2;
        } catch (NoSuchFieldError e12) {
        }
    }
}
