package com.zxt.download2;

/* synthetic */ class h$1 {
    static final /* synthetic */ int[] a = new int[DownloadState.values().length];

    static {
        try {
            a[DownloadState.PAUSE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[DownloadState.FAILED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[DownloadState.DOWNLOADING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[DownloadState.FINISHED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[DownloadState.INITIALIZE.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
