package org.greenrobot.eventbus;

/* synthetic */ class d {
    static final /* synthetic */ int[] a = new int[ThreadMode.values().length];

    static {
        try {
            a[ThreadMode.POSTING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ThreadMode.MAIN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ThreadMode.BACKGROUND.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[ThreadMode.ASYNC.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
