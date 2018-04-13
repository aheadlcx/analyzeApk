package qsbk.app.utils.gif;

import qsbk.app.utils.gif.GifView.GifImageType;

/* synthetic */ class b {
    static final /* synthetic */ int[] a = new int[GifImageType.values().length];

    static {
        try {
            a[GifImageType.WAIT_FINISH.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[GifImageType.COVER.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[GifImageType.SYNC_DECODER.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
