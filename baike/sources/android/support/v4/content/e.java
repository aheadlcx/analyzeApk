package android.support.v4.content;

import android.support.v4.content.ModernAsyncTask.Status;

/* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[Status.values().length];

    static {
        try {
            a[Status.RUNNING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Status.FINISHED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
