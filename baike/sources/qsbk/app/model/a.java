package qsbk.app.model;

import qsbk.app.model.media.MediaFormat;

/* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[MediaFormat.values().length];

    static {
        try {
            a[MediaFormat.IMAGE_GIF.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MediaFormat.IMAGE_GIF_VIDEO.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MediaFormat.VIDEO.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
